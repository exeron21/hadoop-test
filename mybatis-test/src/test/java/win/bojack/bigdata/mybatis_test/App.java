package win.bojack.bigdata.mybatis_test;

import org.w3c.dom.UserDataHandler;
import win.bojack.bigdata.mybatis_test.dao.UserDAO;
import win.bojack.bigdata.mybatis_test.entity.Users;

import java.util.List;

public class App {
    public static void main(String[] args) throws  Exception {
        UserDAO dao = new UserDAO();
        Users u = dao.selectOne(1);
        System.out.println(u.getName());

        List<Users> users  = dao.selectAll();
        for(Users u1 : users) {
            System.out.println("name = " + u1.getName());
        }
    }
}
