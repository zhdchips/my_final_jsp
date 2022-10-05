package com.bjpowernode.javaweb.listener_;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class RequestTestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request 被销毁~");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("request 被创建~");
    }
}
