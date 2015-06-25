package com.jomolangma.app.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by ZhangLijun on 2015/6/8.
 */
public class ProxyInvocationHandler implements InvocationHandler {
    Object target;

    public ProxyInvocationHandler(Object target) {
        this.target = target;
    }

    //打印出每个方法的执行时间
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object ret = method.invoke(target, args);
        long spentTime = System.currentTimeMillis() - start;
        System.out.print("Call method '" + method.getName() + "' spent " + spentTime + "ms");
        return ret;
    }
}
