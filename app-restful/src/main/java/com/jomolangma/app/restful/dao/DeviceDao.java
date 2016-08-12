package com.jomolangma.app.restful.dao;

import com.jomolangma.app.restful.domain.Device;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ZhangLijun on 4/20/16.
 */
public class DeviceDao {
    ConcurrentHashMap<String,Device> fakeDB = new ConcurrentHashMap<>();

    public DeviceDao(){
        fakeDB.put("10.11.58.163",new Device("10.11.58.163"));
        fakeDB.put("10.11.58.184",new Device("10.11.58.184"));
    }

    public Device getDevice(String ip){
        return fakeDB.get(ip);
    }

    public Device updateDevice(Device device) {
        String ip = device.getDeviceIp();
        fakeDB.put(ip,device);
        return fakeDB.get(ip);
    }
}
