package bookmanager.web.page;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.BorrowInfoService;
import bookmanager.model.po.BorrowInfoPO;
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

    @Autowired
    public BorrowInfoPageController(BorrowInfoService borrowInfoService) {
        this.borrowInfoService = borrowInfoService;
    }

    @RequestMapping(value = "/borrowinfo/{page}", method = POST)
    public void getPageInfo(@PathVariable int page) {

        int total = borrowInfoService.getBorrowInfoCount();
        // TODO 拿到借阅信息的数量, 接下来要拿到一页的借阅信息,
        // 拼接成字符串, 再加上当前页码和总页数, 然后通过ajax发送给前端.

//   TODO     List<BorrowInfoPO> borrowInfoPOS =
    }
}
