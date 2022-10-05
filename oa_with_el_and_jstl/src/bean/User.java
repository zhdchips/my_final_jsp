package bean;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener {

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        ServletContext application = event.getSession().getServletContext();
        Object onlineCount = application.getAttribute("onlineCount");
        if (onlineCount == null) {
            application.setAttribute("onlineCount", "1");
        } else {
            Integer count = Integer.parseInt(onlineCount.toString());
            count++;
            application.setAttribute("onlineCount", count);
        }
        System.out.println(application.getAttribute("onlineCount"));

    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        ServletContext application = event.getSession().getServletContext();
        Object onlineCount = application.getAttribute("onlineCount");
        Integer count = Integer.parseInt(onlineCount.toString());
        count--;
        application.setAttribute("onlineCount", count);
        System.out.println(application.getAttribute("onlineCount"));
    }

    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
