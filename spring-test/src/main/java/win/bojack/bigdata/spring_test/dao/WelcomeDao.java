package win.bojack.bigdata.spring_test.dao;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("wd")
public class WelcomeDao {
    private String message = "hello world";

    public void setMessage(String message) {
        this.message = message;
    }
    public void sayHello() {
        System.out.println("hello = " + this.message);
    }
}
