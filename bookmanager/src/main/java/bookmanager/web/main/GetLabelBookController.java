package bookmanager.web.main;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.PagePO;
import bookmanager.utilclass.BookUserMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


/**
 * Created by dela on 12/25/17.
 */

@Controller
@RequestMapping(value = "/bookmanager/label")
public class GetLabelBookController {
    private BookInfoService bookInfoService;
    private UserService userService;

    @Autowired
    public GetLabelBookController(BookInfoService bookInfoService, UserService userService) {
        this.userService = userService;
        this.bookInfoService = bookInfoService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getBookByLabelId(@PathVariable int id, Model model,
            HttpServletRequest httpServletRequest, HttpSession httpSession) {
        // 构造pagePO对象来保存页信息
        PagePO page = new PagePO(1);
        List<BookInfoPO> bookInfoPOS = bookInfoService.getBookByLabelAndPage(page, id);
        int bookCount = bookInfoService.getBookCountByLabel(id);
        page.setTotalPage((bookCount % 5 == 0) ? bookCount / 5 : bookCount / 5 + 1);


        boolean loginMark = httpServletRequest.getSession().getAttribute("uid") != null;
        if (bookInfoPOS == null) {
            if (loginMark) {
               return "main";
            }
            return "index";
        }

        Map<BookInfoPO, String> bookInfoPOStringMap = BookUserMapUtil.getBookInfo(bookInfoPOS, userService);
        model.addAttribute("books", bookInfoPOStringMap);
        model.addAttribute("pageInfo", page);
        model.addAttribute("labelid", id);

        if (loginMark) {
            return "main";
        }
        return "index";
    }

}
