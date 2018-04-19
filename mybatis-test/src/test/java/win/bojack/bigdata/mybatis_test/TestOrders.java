package win.bojack.bigdata.mybatis_test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import win.bojack.bigdata.mybatis_test.entity.Items;
import win.bojack.bigdata.mybatis_test.entity.Orders;
import win.bojack.bigdata.mybatis_test.entity.Users;

import java.io.InputStream;
import java.util.List;

public class TestOrders {

    @Test
    public void testInsert() throws Exception {
        String resource = "mybatis-conf.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);

        SqlSession ss = sf.openSession();
        Orders order = new Orders();
        order.setOrderno("No0333");

        Users u = new Users();
        u.setId(1);
        order.setUser(u);
        ss.insert("orders.insert", order);

        ss.commit();
        ss.close();
    }

    @Test
    public void testSelectOne() throws Exception {
        String resource = "mybatis-conf.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);

        SqlSession ss = sf.openSession();
        Orders order = ss.selectOne("orders.selectOne", 1);

        System.out.println(order.getOrderno());
        System.out.println(order.getUser().getName());
        for (Items i : order.getItems()) {
            System.out.println(i.getId() + "," + i.getItemname());
        }
        ss.close();
    }
    @Test
    public void testSelectAll() throws Exception {
        String resource = "mybatis-conf.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);

        SqlSession ss = sf.openSession();
        List<Orders> ordersList = ss.selectList("orders.selectAll");

        for (Orders o : ordersList) {
            System.out.println(o.getOrderno() + "," + o.getUser().getName());
        }
        ss.close();
    }
}
