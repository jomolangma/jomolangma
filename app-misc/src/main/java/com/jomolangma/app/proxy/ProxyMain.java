package com.jomolangma.app.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by ZhangLijun on 2015/6/8.
 */
public class ProxyMain {
    public static void main(String[] args) {
        ProxyInterface impl = new ProxyInterfaceImpl();
        ProxyInterface proxy = (ProxyInterface) Proxy.newProxyInstance(ProxyMain.class.getClassLoader(),
                impl.getClass().getInterfaces(), new ProxyInvocationHandler(impl));

        proxy.method1();
    }
}
