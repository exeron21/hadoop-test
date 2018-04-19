package win.bojack.bigdata.mybatis_test.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Util {
    private static String resource = "mybatis-conf.xml";
    private static SqlSessionFactory sf;

    static {
        try {
            InputStream is = Resources.getResourceAsStream(resource);
            sf = new SqlSessionFactoryBuilder().build(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSession openSession() {
        return sf.openSession();
    }

    public static void closeSession(SqlSession s) {
        if (s != null) {
            s.close();
        }
    }

    public static void rollBack(SqlSession s) {
        if (s != null) {
            s.rollback();
        }
    }
}
