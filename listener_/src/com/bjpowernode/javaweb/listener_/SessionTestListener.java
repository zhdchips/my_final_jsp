package com.bjpowernode.javaweb.listener_;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionTestListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session 创建~");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session 销毁~");
    }
}
