package com.jomolangma.app.proxy;

/**
 * Created by ZhangLijun on 3/31/16.
 */
public class MyClassLoader extends ClassLoader {
    public Class<?> defineMyClass( byte[] b, int off, int len)
    {
        return super.defineClass(b, off, len);
    }
}
