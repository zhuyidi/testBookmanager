package bookmanager.web.page;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.BorrowInfoService;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.BorrowInfoPO;
import bookmanager.model.po.PagePO;
import bookmanager.utilclass.BorrowInfoStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by dela on 1/6/18.
 */

@Controller
public class BorrowInfoPageController {
    BorrowInfoService borrowInfoService;
    UserService userService;
    BookInfoService bookInfoService;

    @Autowired
    public BorrowInfoPageController(BorrowInfoService borrowInfoService,
            UserService userService, BookInfoService bookInfoService) {
        this.borrowInfoService = borrowInfoService;
        this.userService = userService;
        this.bookInfoService = bookInfoService;
    }

    @RequestMapping(value = "/borrowinfo/{page}", method = POST)
    public void getPageInfo(@PathVariable int page) {
        PagePO pagePO = new PagePO(page, 10);

        // 拿到借阅信息的数量, 接下来要拿到一页的借阅信息,
        // 拼接成字符串, 再加上当前页码和总页数, 然后通过ajax发送给前端.

        int total = borrowInfoService.getBorrowInfoCount();
        pagePO.setTotalPage((total  % 5 == 0) ? total / 5 : total / 5 + 1);

        // 得到一页的借阅信息

        // 得到借阅时间
        List<String> borrowTimes = borrowInfoService.getBorrowDateByPage(pagePO);
        // 得到借阅的书名
        List<String> bookNames = bookInfoService.getBookNameByBorrowInfoPage(pagePO);
        // 得到借阅的书的所属者
        List<String> owners = userService.getOwnerNameByBorrowInfoPage(pagePO);
        // 得到借阅书的借阅者
        List<String> users = userService.getUserNameByBorrowInfoPage(pagePO);

        BorrowInfoStringUtil.getOnePageBorrowInfo(pagePO, borrowTimes, bookNames, owners, users);

    }
}
