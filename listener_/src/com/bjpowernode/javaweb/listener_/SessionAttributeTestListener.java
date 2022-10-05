package com.bjpowernode.javaweb.listener_;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionAttributeTestListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("session 中添加了数据~");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("session 中移除了数据~");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        System.out.println("session 中替换了数据~");
    }
}
