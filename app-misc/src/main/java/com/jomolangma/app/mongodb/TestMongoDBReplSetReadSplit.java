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
            ServerAddress address1 = new ServerAddress("192.168.16.2", 27017);
//            ServerAddress address2 = new ServerAddress("192.168.16.4", 20000);
//            ServerAddress address3 = new ServerAddress("192.168.16.5", 20000);
//            addresses.add(address1);
//            addresses.add(address2);
//            addresses.add(address3);

            addresses.add(address1);

            MongoClient client = new MongoClient(addresses);
            DB db = client.getDB("cmcc");
            DBCollection coll = db.getCollection("test");

            //long setHistoryReslut = jedisCluster.hsetnx(HISTORY_ACTIVE_USERS + appID, cid, STR_ONE);
            List<BasicDBObject> documents = new ArrayList<BasicDBObject>(2000000);
            for (long i = 10001001000l;i<=20109000000l;i++){

                for (int j=1;j<=10000;j++){
                    BasicDBObject object = new BasicDBObject();
                    object.append("_id", "app"+j+i);
                    object.append("value", 1);

                    documents.add(object);
                }

                if (i%100 == 0){
                    coll.insert(documents);
                    documents.clear();
                    System.out.println("total insert documents count: " + (i - 10000000000l)*10000);
                }
            }

//                BasicDBObject objectFind = new BasicDBObject();
//                objectFind.append("_id", 10000000001l);
//
//                BasicDBObject objectUpdate = new BasicDBObject("$set",new BasicDBObject().append("cid1", 1));
//
//                WriteResult result = coll.update(objectFind,objectUpdate,true,false);
//
//                System.out.println(result.isUpdateOfExisting());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}