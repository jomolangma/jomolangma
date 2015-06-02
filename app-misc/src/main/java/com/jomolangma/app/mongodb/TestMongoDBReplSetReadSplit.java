package com.jomolangma.app.mongodb;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanglijun on 5/18/15.
 */
public class TestMongoDBReplSetReadSplit {

    public static void main(String[] args) {

        try {
            List<ServerAddress> addresses = new ArrayList<ServerAddress>();
            ServerAddress address1 = new ServerAddress("192.168.16.3", 27017);
            ServerAddress address2 = new ServerAddress("192.168.16.4", 27017);
            ServerAddress address3 = new ServerAddress("192.168.16.5", 27017);
            addresses.add(address1);
            addresses.add(address2);
            addresses.add(address3);

            MongoClient client = new MongoClient(addresses);
            DB db = client.getDB("test");
            DBCollection coll = db.getCollection("testdb");


            BasicDBObject object = new BasicDBObject();
            object.append("test2", "testvalue2");

            //读操作从副本节点读取
            ReadPreference preference = ReadPreference.secondary();
            DBObject dbObject = coll.findOne(object, null, preference);

            System.out.println(dbObject);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}