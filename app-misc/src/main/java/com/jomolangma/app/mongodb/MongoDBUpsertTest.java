package com.jomolangma.app.mongodb;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanglijun on 5/18/15.
 */
public class MongoDBUpsertTest {

    public static void main(String[] args) {

        try {
            List<ServerAddress> addresses = new ArrayList<ServerAddress>();
            ServerAddress address1 = new ServerAddress("192.168.16.2", 27017);
            addresses.add(address1);

            MongoClient client = new MongoClient(addresses);
            DB db = client.getDB("cmcc");
            DBCollection coll = db.getCollection("test");

            //先查一次，去除网络建立等等开销
            coll.findOne();

            String appkey = "app1";
            String cid = "10000000001";
            BasicDBObject query = new BasicDBObject();
            query.append("_id", appkey+cid);

            BasicDBObject update = new BasicDBObject("$set",new BasicDBObject().append("value", 1));

            long startTime = System.currentTimeMillis();
            WriteResult result = coll.update(query,update,true,false);
            long time = System.currentTimeMillis() - startTime;

            if (result.isUpdateOfExisting()){
                System.out.println("app + cid 已经存在，查询花费了 " + time + "ms");
            }else{
                System.out.println("app + cid 不存在，查询并更新花费了 " + time + "ms");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}