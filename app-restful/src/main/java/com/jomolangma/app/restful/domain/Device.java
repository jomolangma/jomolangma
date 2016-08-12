package com.jomolangma.app.restful.domain;

import com.sun.xml.internal.txw2.annotation.XmlAttribute;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by ZhangLijun on 4/20/16.
 */
@XmlRootElement
public class Device {
    private String deviceIp;
    private int deviceStatus;

    public Device(){}

    public Device(String deviceIp){
        super();
        this.deviceIp = deviceIp;
    }

    @XmlAttribute
    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    @XmlAttribute
    public int getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.deviceStatus = deviceStatus;
    }
}
