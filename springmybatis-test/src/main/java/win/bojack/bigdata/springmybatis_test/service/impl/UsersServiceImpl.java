package win.bojack.bigdata.springmybatis_test.service.impl;

import org.springframework.stereotype.Service;
import win.bojack.bigdata.springmybatis_test.dao.BaseDao;
import win.bojack.bigdata.springmybatis_test.entity.Users;
import win.bojack.bigdata.springmybatis_test.service.UsersService;

import javax.annotation.Resource;

@Service("usersService")
public class UsersServiceImpl extends BaseServiceImpl<Users> implements UsersService {

    @Resource(name="userDao")
    public void setDao(BaseDao<Users> dao) {
        super.setDao(dao);
    }

}
