package com.jomolangma.app.hbase.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Used by the book examples to generate tables and fill them with test data.
 */
public class HBaseHelper {

    private static final String[] taglibs = {"tag0", "tag1", "tag2", "tag3", "tag4",
            "tag5", "tag6", "tag7", "tag8", "tag9", "tag10", "tag11", "tag12", "tag13", "tag14"};

    private static final String[] hotspots = {"hotspot0", "hotspot1", "hotspot2",
            "hotspot3", "hotspot4", "hotspot5", "hotspot6", "hotspot7", "hotspot8", "hotspot9"};

    private static final String[] regions = {"region0", "region1", "region2", "region3", "region4"};

    private Configuration conf = null;
    private HBaseAdmin admin = null;

    protected HBaseHelper(Configuration conf) throws IOException {
        this.conf = conf;
        this.admin = new HBaseAdmin(conf);
    }

    public static HBaseHelper getHelper(Configuration conf) throws IOException {
        return new HBaseHelper(conf);
    }

    public boolean existsTable(String table) throws IOException {
        return admin.tableExists(table);
    }

    public void createTable(String table, String... colfams) throws IOException {
        createTable(table, null, colfams);
    }

    public void createTable(String table, byte[][] splitKeys, String... colfams)
            throws IOException {
        HTableDescriptor desc = new HTableDescriptor(table);
        for (String cf : colfams) {
            HColumnDescriptor coldef = new HColumnDescriptor(cf);
            desc.addFamily(coldef);
        }
        if (splitKeys != null) {
            admin.createTable(desc, splitKeys);
        } else {
            admin.createTable(desc);
        }
    }

    public void disableTable(String table) throws IOException {
        admin.disableTable(table);
    }

    public void dropTable(String table) throws IOException {
        if (existsTable(table)) {
            disableTable(table);
            admin.deleteTable(table);
        }
    }

    public void fillTable(String table, int startRow, int endRow, int numCols,
                          String... colfams) throws IOException {
        fillTable(table, startRow, endRow, numCols, -1, false, colfams);
    }

    public void fillTable(String table, int startRow, int endRow, int numCols,
                          boolean setTimestamp, String... colfams) throws IOException {
        fillTable(table, startRow, endRow, numCols, -1, setTimestamp, colfams);
    }

    public void fillTable(String table, int startRow, int endRow, int numCols,
                          int pad, boolean setTimestamp, String... colfams)
            throws IOException {
        fillTable(table, startRow, endRow, numCols, pad, setTimestamp, false,
                colfams);
    }

    public void fillTable(String table, int startRow, int endRow, int numCols,
                          int pad, boolean setTimestamp, boolean random, String... colfams)
            throws IOException {
        HTable tbl = new HTable(conf, table);
        Random rnd = new Random();
        for (int row = startRow; row <= endRow; row++) {
            for (int col = 0; col < numCols; col++) {
                Put put = new Put(Bytes.toBytes("row-" + padNum(row, pad)));
                for (String cf : colfams) {
                    String colName = "col-" + padNum(col, pad);
                    String val = "val-"
                            + (random ? Integer.toString(rnd.nextInt(numCols))
                            : padNum(row, pad) + "." + padNum(col, pad));
                    if (setTimestamp) {
                        put.add(Bytes.toBytes(cf), Bytes.toBytes(colName), col,
                                Bytes.toBytes(val));
                    } else {
                        put.add(Bytes.toBytes(cf), Bytes.toBytes(colName),
                                Bytes.toBytes(val));
                    }
                }
                tbl.put(put);
            }
        }
        tbl.close();
    }

    public void fillUserGroupTable(String table, int startRow, int endRow, int pad)
            throws IOException {
        HTable tbl = new HTable(conf, table);
        Random rnd = new Random();
        for (int row = startRow; row <= endRow; row++) {
            Put put = new Put(Bytes.toBytes("user-" + padNum(row, pad)));

            //helper.createTable("user_group_table", "user", "taglibs", "hotspots", "regions");
            GenderEnum[] gender = GenderEnum.values();
            String genderStr = gender[rnd.nextInt(3)].name();
            put.add(Bytes.toBytes("user"), Bytes.toBytes("age"), Bytes.toBytes(rnd.nextInt(100)));
            put.add(Bytes.toBytes("user"), Bytes.toBytes("gender"), Bytes.toBytes(genderStr));
            put.add(Bytes.toBytes("user"), Bytes.toBytes("salary"), Bytes.toBytes(rnd.nextInt(1000000)));

            String taglibsStr = "";
            for (int i = 0; i < 3; i++) {
                if (taglibsStr.length() == 0) {
                    taglibsStr = taglibsStr + taglibs[rnd.nextInt(15)] + ":" + rnd.nextInt(5);
                } else {
                    taglibsStr = taglibsStr + ";" + taglibs[rnd.nextInt(15)] + ":" + rnd.nextInt(5);
                }
            }
            put.add(Bytes.toBytes("taglibs"), Bytes.toBytes("taglibs"), Bytes.toBytes(taglibsStr));

            String hotspotsStr = "";
            for (int i = 0; i < 2; i++) {
                if (hotspotsStr.length() == 0) {
                    hotspotsStr = hotspotsStr + hotspots[rnd.nextInt(10)];
                } else {
                    hotspotsStr = hotspotsStr + ";" + hotspots[rnd.nextInt(10)];
                }
            }
            put.add(Bytes.toBytes("hotspots"), Bytes.toBytes("hotspots"), Bytes.toBytes(hotspotsStr));

            String regionsStr = "";
            for (int i = 0; i < 2; i++) {
                if (regionsStr.length() == 0) {
                    regionsStr = regionsStr + regions[rnd.nextInt(5)];
                } else {
                    regionsStr = regionsStr + ";" + regions[rnd.nextInt(5)];
                }
            }
            put.add(Bytes.toBytes("regions"), Bytes.toBytes("regions"), Bytes.toBytes(regionsStr));

            tbl.put(put);
        }
        tbl.close();
    }

    public String padNum(int num, int pad) {
        String res = Integer.toString(num);
        if (pad > 0) {
            while (res.length() < pad) {
                res = "0" + res;
            }
        }
        return res;
    }

    public void put(String table, String row, String fam, String qual, long ts,
                    String val) throws IOException {
        HTable tbl = new HTable(conf, table);
        Put put = new Put(Bytes.toBytes(row));
        put.add(Bytes.toBytes(fam), Bytes.toBytes(qual), ts, Bytes.toBytes(val));
        tbl.put(put);
        tbl.close();
    }

    public void put(String table, String[] rows, String[] fams, String[] quals,
                    long[] ts, String[] vals) throws IOException {
        HTable tbl = new HTable(conf, table);
        for (String row : rows) {
            Put put = new Put(Bytes.toBytes(row));
            for (String fam : fams) {
                int v = 0;
                for (String qual : quals) {
                    String val = vals[v < vals.length ? v : vals.length - 1];
                    long t = ts[v < ts.length ? v : ts.length - 1];
                    put.add(Bytes.toBytes(fam), Bytes.toBytes(qual), t,
                            Bytes.toBytes(val));
                    v++;
                }
            }
            tbl.put(put);
        }
        tbl.close();
    }

    public void dump(String table, String[] rows, String[] fams, String[] quals)
            throws IOException {
        HTable tbl = new HTable(conf, table);
        List<Get> gets = new ArrayList<Get>();
        for (String row : rows) {
            Get get = new Get(Bytes.toBytes(row));
            get.setMaxVersions();
            if (fams != null) {
                for (String fam : fams) {
                    for (String qual : quals) {
                        get.addColumn(Bytes.toBytes(fam), Bytes.toBytes(qual));
                    }
                }
            }
            gets.add(get);
        }
        Result[] results = tbl.get(gets);
        for (Result result : results) {
            for (KeyValue kv : result.raw()) {
                System.out.println("KV: " + kv + ", Value: "
                        + Bytes.toString(kv.getValue()));
            }
        }
    }
}
