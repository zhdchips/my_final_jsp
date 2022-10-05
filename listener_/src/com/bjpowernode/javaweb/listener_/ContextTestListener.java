package com.bjpowernode.javaweb.listener_;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextTestListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("context 创建~");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("context 销毁~");
    }
}
