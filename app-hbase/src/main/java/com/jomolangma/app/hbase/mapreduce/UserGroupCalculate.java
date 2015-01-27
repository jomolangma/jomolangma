package com.jomolangma.app.hbase.mapreduce;

import com.jomolangma.app.hbase.domain.UserGroup;
import net.sf.json.JSONObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.IdentityTableReducer;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserGroupCalculate {

    private static final Logger logger = LoggerFactory
            .getLogger(UserGroupCalculate.class);

    public static final String NAME = "UserGroupCalculate";
    public static final Set<UserGroup> userGroupSet = new HashSet<UserGroup>();
    //helper.createTable("user_group_table", "user", "taglibs", "hotspots", "regions", "groups");
    private static final byte[] CF_USER = Bytes.toBytes("user");
    private static final byte[] CF_TAGLIBS = Bytes.toBytes("taglibs");
    private static final byte[] CF_HOTSPOTS = Bytes.toBytes("hotspots");
    private static final byte[] CF_REGIONS = Bytes.toBytes("regions");
    private static final byte[] CF_GROUPS = Bytes.toBytes("groups");

    private static final byte[] QL_AGE = Bytes.toBytes("age");
    private static final byte[] QL_SALARY = Bytes.toBytes("salary");
    private static final byte[] QL_GENDER = Bytes.toBytes("gender");
    private static final byte[] QL_TAGLIBS = Bytes.toBytes("taglibs");
    private static final byte[] QL_HOTSPOTS = Bytes.toBytes("hotspots");
    private static final byte[] QL_REGIONS = Bytes.toBytes("regions");
    private static final byte[] QL_GROUPS = Bytes.toBytes("groups");

    /**
     * Implements the <code>Mapper</code> that reads the data and extracts the
     * required information.
     */
    static class ParseMapper extends TableMapper<ImmutableBytesWritable, Put> {
        //在这里导入UserGroup的定义，生成HashMap供map函数计算用
        @Override
        protected void setup(Context context) throws IOException,
                InterruptedException {

            logger.info("start to init user group set");
            String fileName = "user-group.txt";
            //File file = new File(ParseMapper.class.getResourceAsStream("/" + fileName));
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(ParseMapper.class.getResourceAsStream("/" + fileName)));
                String tempString = null;
                UserGroup pojo;
                JSONObject jsonObject;
                while ((tempString = reader.readLine()) != null) {
                    jsonObject = JSONObject.fromObject(tempString);
                    System.out.println(jsonObject.toString());
                    pojo = (UserGroup) JSONObject.toBean(jsonObject, UserGroup.class);
                    userGroupSet.add(pojo);
                }
                reader.close();

                for (UserGroup userGroup : userGroupSet) {
                    logger.info("user group name" + userGroup.getGroupName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }

        }

        /**
         * Maps the input.
         *
         * @param row     The row key.
         * @param columns The columns of the row.
         * @param context The task context.
         * @throws java.io.IOException When mapping the input fails.
         */
        @Override
        public void map(ImmutableBytesWritable row, Result columns,
                        Context context) throws IOException {
            String value = null;
            try {
                Put put = new Put(row.get());
                int age = 0;
                int salary = 0;
                String gender = "";
                String taglibs = "";
                String hotspots = "";
                String regions = "";

                if (columns.containsColumn(CF_USER, QL_AGE)) {
                    age = Bytes.toInt(columns.getValue(CF_USER, QL_AGE));
                }

                if (columns.containsColumn(CF_USER, QL_SALARY)) {
                    salary = Bytes.toInt(columns.getValue(CF_USER, QL_SALARY));
                }

                if (columns.containsColumn(CF_USER, QL_GENDER)) {
                    gender = Bytes.toString(columns.getValue(CF_USER, QL_GENDER));
                }

                if (columns.containsColumn(CF_TAGLIBS, QL_TAGLIBS)) {
                    taglibs = Bytes.toString(columns.getValue(CF_TAGLIBS, QL_TAGLIBS));
                }

                if (columns.containsColumn(CF_HOTSPOTS, QL_HOTSPOTS)) {
                    hotspots = Bytes.toString(columns.getValue(CF_HOTSPOTS, QL_HOTSPOTS));
                }

                if (columns.containsColumn(CF_REGIONS, CF_REGIONS)) {
                    regions = Bytes.toString(columns.getValue(CF_REGIONS, CF_REGIONS));
                }

                logger.info("user and tags info {},{},{},{}", age, salary, gender, taglibs);

                Map<String, Double> userGroupMap = new HashMap<String, Double>();

                logger.info("user group set length", userGroupSet.size());

                for (UserGroup userGroup : userGroupSet) {

                    logger.info("UserGroup info {},{},{},{}", userGroup.getTaglibs().toString(), userGroup.getCommon().getAge(), userGroup.getCommon().getGender(), userGroup.getCommon().getSalary());
                    //验证基本属性，如果不匹配，返回
                    if (!(userGroup.invalidAge(age) && userGroup.invalidGender(gender) && userGroup.invalidSalary(salary))) {
                        continue;
                    }

                    logger.info("**********passed the user validation********");

                    //验证热点
                    //验证区域
                    //验证标签，计算权重
                    Double weight = 0.0;
                    if (taglibs != null && taglibs.length() > 0) {
                        logger.info("**********taglibs********" + taglibs);
                        String[] tags = taglibs.split(";");

                        for (String tag : tags) {
                            String[] temp = tag.split(":");

                            if (userGroup.hasTag(temp[0])) {
                                weight = weight + Double.valueOf(temp[1]);
                            }
                        }
                    }

                    userGroupMap.put(userGroup.getGroupName(), weight);
                }

                String result = "";
                for (String groupName : userGroupMap.keySet()) {
                    if (userGroupMap.get(groupName) < 1) {
                        continue;
                    }
                    if (result.length() == 0) {
                        result = result + groupName + ":" + userGroupMap.get(groupName);
                    } else {
                        result = result + ";" + groupName + ":" + userGroupMap.get(groupName);
                    }
                }

                if (result.length() == 0) {
                    return;
                }
                put.add(CF_GROUPS, QL_GROUPS, Bytes.toBytes(result));
                context.write(row, put);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error: " + e.getMessage() + ", Row: "
                        + Bytes.toStringBinary(row.get()) + ", JSON: " + value);
            }
        }
    }


    /**
     * Main entry point.
     *
     * @param args The command line parameters.
     * @throws Exception When running the job fails.
     */
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        Configuration conf = HBaseConfiguration.create();

        String tableName = "user_group_table";
        Scan scan = new Scan();
        Job job = new Job(conf, "Parse data in " + tableName + ", write to "
                + tableName + "(map only)");
        job.setJarByClass(UserGroupCalculate.class);
        TableMapReduceUtil.initTableMapperJob(tableName, scan, ParseMapper.class,
                ImmutableBytesWritable.class, Put.class, job);
        TableMapReduceUtil.initTableReducerJob(tableName,
                IdentityTableReducer.class, job);
        job.setNumReduceTasks(0);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
