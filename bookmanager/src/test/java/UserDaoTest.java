import bookmanager.config.database.DataConfig;
import bookmanager.dao.dbimpl.UserServiceImpl;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.UserPO;
import bookmanager.model.vo.login.UserLoginVO;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by dela on 12/12/17.
 */
public class UserDaoTest {
    public static void main(String[] args) {
        DataConfig dataConfig = new DataConfig();
        DataSource dataSource = dataConfig.dataSource();
        JdbcOperations jdbcOperations = dataConfig.jdbcTemplate(dataSource);
        UserLoginVO user = null;
        UserPO userPO = null;

        UserService userService = new UserServiceImpl(jdbcOperations);
        user = userService.getPasswordAndUidByName("祝一迪");
        System.out.println(user.toString());
    }
 }
