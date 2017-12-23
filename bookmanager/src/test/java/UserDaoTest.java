import bookmanager.config.database.DataConfig;
import bookmanager.dao.dbimpl.BookInfoServiceImpl;
import bookmanager.dao.dbimpl.BookLabelServiceImpl;
import bookmanager.dao.dbimpl.UserServiceImpl;
import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.BookLabelService;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.BookLabelPO;
import bookmanager.model.po.UserPO;
import bookmanager.model.vo.login.UserLoginVO;
import bookmanager.utilclass.DateToString;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

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
//
//        UserService userService = new UserServiceImpl(jdbcOperations);
//        user = userService.getPasswordAndUidByName("祝一迪");
//        System.out.println(user.toString());
//
        BookLabelService bookLabelService = new BookLabelServiceImpl(jdbcOperations);
        List<BookLabelPO> bookLabelPOS = bookLabelService.getBookLabelById(0);
//        System.out.println(bookLabelPOS.toString());
//        Date date = new Date();
//        System.out.println(DateToString.getStringDate(date));
        BookInfoService bookInfoService = new BookInfoServiceImpl(jdbcOperations);
        System.out.println(DateToString.getStringDate(bookInfoService.test(1)));

    }
 }
