import bookmanager.config.database.DataConfig;
import bookmanager.dao.dbimpl.BookInfoServiceImpl;
import bookmanager.dao.dbimpl.BookLabelServiceImpl;
import bookmanager.dao.dbimpl.BorrowInfoServiceImpl;
import bookmanager.dao.dbimpl.UserServiceImpl;
import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.BookLabelService;
import bookmanager.dao.dbservice.BorrowInfoService;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.BookLabelPO;
import bookmanager.model.po.PagePO;
import bookmanager.model.po.UserPO;
import bookmanager.model.vo.login.UserLoginVO;
import bookmanager.utilclass.DateToString;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
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

        UserService userService = new UserServiceImpl(jdbcOperations);
//        System.out.println(userService.test("祝一迪"));
//        user = userService.getPasswordAndUidByName("祝一迪");
//        System.out.println(user.toString());

//        BookLabelService bookLabelService = new BookLabelServiceImpl(jdbcOperations);
//        List<BookLabelPO> bookLabelPOS = bookLabelService.getBookLabelById(0);
//        System.out.println(bookLabelPOS.toString());
//        Date date = new Date();
//        System.out.println(DateToString.getStringDate(date));
        BookInfoService bookInfoService = new BookInfoServiceImpl(jdbcOperations);
        BorrowInfoService borrowInfoService = new BorrowInfoServiceImpl(jdbcOperations);
        PagePO pagePO = new PagePO(1, 2);


        List<String> borrowTimes = borrowInfoService.getBorrowDateByPage(pagePO);
        // 得到借阅的书名
        List<String> bookNames = bookInfoService.getBookNameByBorrowInfoPage(pagePO);
        // 得到借阅的书的所属者
        List<String> owners = userService.getOwnerNameByBorrowInfoPage(pagePO);
        // 得到借阅书的借阅者
        List<String> users = userService.getUserNameByBorrowInfoPage(pagePO);

        System.out.println(borrowTimes);
        System.out.println(bookNames);
        System.out.println(users);
        System.out.println(owners);
    }
 }
