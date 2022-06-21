package com.bilibili.juc;

import org.apache.tomcat.util.http.fileupload.impl.IOFileUploadException;

/**
 * @author PhilChan
 * @date 2022/6/20 下午 4:52
 * Description:
 */
public class SafeDoubleCheckSingleton {
    private static SafeDoubleCheckSingleton singleton;

    // 私有化构造方法
    private SafeDoubleCheckSingleton() {
    }

    // 双重锁设计
    public static SafeDoubleCheckSingleton getInstance() {
        // 多线程并发创建对象时，会通过加锁保证只有一个线程能创建对象
        if (singleton == null) {
            synchronized (SafeDoubleCheckSingleton.class) {
                if (singleton == null) {
                    // 隐患：多线程情况下， 该对象可能还未完成初始化就其他线程读取
                    singleton = new SafeDoubleCheckSingleton();
                }
            }
        }
        // 对象创建挖鼻，执行getInstance()将不需要获取锁，直接返回创建对象
        return singleton;
    }
}
