package win.bojack.bigdata.mybatis_test.dao;

import org.apache.ibatis.session.SqlSession;
import win.bojack.bigdata.mybatis_test.entity.Users;

import java.util.List;

public class UserDAO {

    public void insert(final Users user) {
        DAOTemplate.execute(new MyBatisCallBack() {
            public Object doInMyBatis(SqlSession s) {
                return s.insert("users.insert", user);
            }
        });
    }

    public void update(final Users user) {
        DAOTemplate.execute(new MyBatisCallBack() {
            public Object doInMyBatis(SqlSession s) {
                return s.update("users.update", user);
            }
        });
    }

    public Users selectOne(final Integer i) {
        return (Users) DAOTemplate.execute(new MyBatisCallBack() {
            public Object doInMyBatis(SqlSession s) {
                return s.selectOne("users.selectOne", i);
            }
        });
    }

    public List<Users> selectAll() {
        return (List<Users>) DAOTemplate.execute(new MyBatisCallBack() {
            public Object doInMyBatis(SqlSession s) {
                return s.selectList("users.selectAll");
            }
        });
    }
}
