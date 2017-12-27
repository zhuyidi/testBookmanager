package bookmanager.dao.dbservice;

import bookmanager.model.po.UserPO;
import bookmanager.model.vo.login.UserLoginVO;

import java.util.List;

/**
 * Created by dela on 11/23/17.
 */
public interface UserService {
    // 按用户名查询用户
    UserPO getUserByName(String name);

    // 按用户id查询用户
    UserPO getUserById(int id);

    // 按用户的名字查询密码和uid(将密码和uid放进UserLoginVO对象中)
    UserLoginVO getPasswordAndUidByName(String name);

    // 通过id得到User的名字
    String getUsernameById(int id);

}
