package bean;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class User1 implements HttpSessionBindingListener {

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("向 session 中绑定数据~");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("向 session 中解绑数据~");
    }

    private String id;
    private String name;
    private String pwd;

    public User1() {
    }

    public User1(String id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
