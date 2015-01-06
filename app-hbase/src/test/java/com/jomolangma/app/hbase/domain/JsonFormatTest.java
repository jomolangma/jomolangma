package com.jomolangma.app.hbase.domain;

import net.sf.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JsonFormatTest {

    @Test
    public void testJsonFormat() {
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupName("group-A");

        Common common = new Common();
        common.setGender("F");
        common.setAge("#25,30$");
        common.setSalary("$15,30#");
        userGroup.setCommon(common);

        Set<String> taglibs = new HashSet<String>();
        taglibs.add("韩剧");
        taglibs.add("汽车");
        userGroup.setTaglibs(taglibs);

        Set<String> hotspots = new HashSet<String>();
        hotspots.add("野马GT350");
        userGroup.setHotspots(hotspots);

        Set<String> regions = new HashSet<String>();
        regions.add("上班族");
        regions.add("周末宅");
        userGroup.setRegions(regions);

        JSONObject json = JSONObject.fromObject(userGroup);
        System.out.println(json.toString());

        UserGroup pojo;
        JSONObject jsonObject = JSONObject.fromObject(json.toString());
        pojo = (UserGroup) JSONObject.toBean(jsonObject, UserGroup.class);
        System.out.println(pojo.getCommon().getAge());

        System.out.println(pojo.invalidAge(25));
        System.out.println(pojo.invalidSalary(25));

    }

    @Test
    public void generateUserGroupTest() {
        Map<String, UserGroup> userGroupMap = new HashMap<String, UserGroup>();
        String fileName = "src/main/resources/user-group.txt";
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                UserGroup pojo;
                JSONObject jsonObject = JSONObject.fromObject(tempString);
                System.out.println(jsonObject.toString());
                pojo = (UserGroup) JSONObject.toBean(jsonObject, UserGroup.class);
                userGroupMap.put(pojo.getGroupName(), pojo);
            }
            reader.close();

            for (String userGroup : userGroupMap.keySet()) {
                System.out.println(userGroupMap.get(userGroup).getGroupName());
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

}
