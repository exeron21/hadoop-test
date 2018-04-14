package win.bojack.bigdata.mybatis_test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class App1 {

    public static void main(String[] args ) throws IOException {
        String  resource = "mybatis-conf.xml";
        InputStream is = Resources.getResourceAsStream(resource);

        SqlSessionFactory sf= new SqlSessionFactoryBuilder().build(is);

        SqlSession ss = sf.openSession();

        System.out.println(ss);
    }
}
