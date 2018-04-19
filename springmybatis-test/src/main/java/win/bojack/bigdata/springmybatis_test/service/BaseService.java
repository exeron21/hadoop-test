package win.bojack.bigdata.springmybatis_test.service;

import java.util.List;

public interface BaseService<T> {
    public void insert (T t);
    public void update (T t);
    public void delete (Integer id);
    public T selectOne (Integer id);
    public List<T> selectAll();
}
