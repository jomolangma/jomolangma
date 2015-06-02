package com.jomolangma.app.mongodb;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanglijun on 5/18/15.
 */
public class TestMongoDBShards {

    public static void main(String[] args) {

        try {
            List<ServerAddress> addresses = new ArrayList<ServerAddress>();
            ServerAddress address1 = new ServerAddress("192.168.16.3", 20000);
            ServerAddress address2 = new ServerAddress("192.168.16.4", 20000);
            ServerAddress address3 = new ServerAddress("192.168.16.5", 20000);
            addresses.add(address1);
            addresses.add(address2);
            addresses.add(address3);

            MongoClient client = new MongoClient(addresses);
            DB db = client.getDB("testdb");
            DBCollection coll = db.getCollection("table1");

            BasicDBObject object = new BasicDBObject();
            object.append("id", 1);

            DBObject dbObject = coll.findOne(object);

            System.out.println(dbObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
