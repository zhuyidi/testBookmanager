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

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by dela on 1/6/18.
 */

@Controller
public class SearchBooksController {
    BookInfoService bookInfoService;
    UserService userService;

    @Autowired
    public SearchBooksController(BookInfoService bookInfoService, UserService userService) {
        this.bookInfoService = bookInfoService;
        this.userService = userService;
    }

    @RequestMapping(value = "/search/{page}", method = POST)
    public String search(@PathVariable int page, HttpServletRequest httpServletRequest, Model model) {
        PagePO pagePO = new PagePO(page);

        String keyword = httpServletRequest.getParameter("keyword");
        keyword = "%" + keyword + "%";
        List<BookInfoPO> bookInfoPOList = bookInfoService.getBookInfoByNAO(keyword);

        int bookCount = bookInfoPOList.size();
        pagePO.setTotalPage((bookCount % 5 == 0) ? bookCount / 5 : bookCount / 5 + 1);

        Map<BookInfoPO, String> bookInfoPOStringMap = BookUserMapUtil.getBookInfo(bookInfoPOList, userService);
        model.addAttribute("books", bookInfoPOStringMap);
        model.addAttribute("pageInfo", pagePO);

        return "showresult";
    }
}
