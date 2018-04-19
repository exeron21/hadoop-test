package win.bojack.bigdata.springmybatis_test.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import win.bojack.bigdata.springmybatis_test.entity.Users;
import win.bojack.bigdata.springmybatis_test.service.UsersService;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Test {

    @org.junit.Test
    public void testConn() throws SQLException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        DataSource ds = (DataSource) ac.getBean("dataSource");
        System.out.println(ds.getConnection());
    }
    @org.junit.Test
    public void testUserService() throws SQLException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        UsersService us = (UsersService) ac.getBean("usersService");
        Users u = new Users();
        u.setAge(33);
        u.setName("jerrrrrrr");
        us.insert(u);
    }
}
