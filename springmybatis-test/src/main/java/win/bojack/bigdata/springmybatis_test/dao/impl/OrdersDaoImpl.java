package win.bojack.bigdata.springmybatis_test.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import win.bojack.bigdata.springmybatis_test.dao.BaseDao;
import win.bojack.bigdata.springmybatis_test.entity.Orders;

import java.util.List;

@Repository
public class OrdersDaoImpl extends SqlSessionDaoSupport implements BaseDao<Orders> {
    public void insert(Orders orders) {

    }

    public void update(Orders orders) {

    }

    public void delete(Integer id) {

    }

    public Orders selectOne(Integer id) {
        return null;
    }

    public List<Orders> selectAll() {
        return null;
    }
}
