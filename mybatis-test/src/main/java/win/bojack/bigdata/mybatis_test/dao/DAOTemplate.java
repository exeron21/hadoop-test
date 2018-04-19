package win.bojack.bigdata.mybatis_test.dao;

import org.apache.ibatis.session.SqlSession;
import win.bojack.bigdata.mybatis_test.util.Util;

public class DAOTemplate {

    public static Object execute(MyBatisCallBack callBack) {
        SqlSession s = null;
        try {
            s = Util.openSession();
            Object ret = callBack.doInMyBatis(s);
            s.commit();
            return ret;
        } catch (Exception e) {
            Util.rollBack(s);
        } finally {
            Util.closeSession(s);
        }
        return null;
    }
}
