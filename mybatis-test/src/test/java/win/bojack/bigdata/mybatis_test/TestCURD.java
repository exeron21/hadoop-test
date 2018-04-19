package win.bojack.bigdata.mybatis_test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import win.bojack.bigdata.mybatis_test.entity.Orders;
import win.bojack.bigdata.mybatis_test.entity.Users;
import win.bojack.bigdata.mybatis_test.util.Util;

import java.io.InputStream;
import java.util.List;

public class TestCURD {

    @Test
    public void testCURD() throws Exception {
        TestCURD curd = new TestCURD();
        Users user = new Users();
        user.setAge(31);
        user.setName("jerryC");
        curd.testInsert(user);
    }

    @Test
    public void testUpdate() throws Exception {
        String resource = "mybatis-conf.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);

        SqlSession ss = sf.openSession();
        Users u = new Users();
        u.setId(1);
        u.setName("tomasLee");
        u.setAge(33);
        ss.update("users.update", u);
        ss.commit();
        ss.clearCache();
        ss.close();
    }

    public void testInsert(Users user) throws Exception {
        SqlSession s = null;
        try {
            s = Util.openSession();
            s.insert("users.insert", user);
            s.commit();
        } catch (Exception e) {
            Util.rollBack(s);
        } finally {
            Util.closeSession(s);
        }
    }

    @Test
    public void testSelectOne() throws Exception {
        String resource = "mybatis-conf.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);

        SqlSession ss = sf.openSession();

        Users u = ss.selectOne("users.selectOne", 1);
        System.out.println(u.getName());
        List<Orders> orders = u.getOrders();
        for (Orders o : orders) {
            System.out.println(o.getId() + " : " + o.getOrderno());
        }
        ss.close();
    }

    @Test
    public void testSelectAll() throws Exception {
        String resource = "mybatis-conf.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);

        SqlSession ss = sf.openSession();

        List<Users> o = ss.selectList("users.selectAll");
        for (Users u : o) {
            System.out.println(u.getName());
        }
        ss.close();
    }

    @Test
    public void testDelete() throws Exception {
        String resource = "mybatis-conf.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);

        SqlSession ss = sf.openSession();

        ss.delete("users.delete", 2);
        ss.commit();
        ss.close();
    }
}
