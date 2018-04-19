package win.bojack.bigdata.mybatis_test.dao;


import org.apache.ibatis.session.SqlSession;

/**
 * 回调接口
 */
public interface MyBatisCallBack {

    public Object doInMyBatis(SqlSession s);
}
