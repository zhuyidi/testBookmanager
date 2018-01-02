package bookmanager.web.page;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.BookLabelService;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.PagePO;
import bookmanager.model.po.UserPO;
import bookmanager.utilclass.BookUserMapUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by dela on 1/2/18.
 */

@Controller
@RequestMapping(value = "/bookmanager")
public class Page {
    private BookInfoService bookInfoService;
    private UserService userService;

    public Page(BookInfoService bookInfoService, UserService userService) {
        this.bookInfoService = bookInfoService;
        this.userService = userService;
    }

    @RequestMapping(value = "/{page}", method = GET)
    public String getPageInfo(@PathVariable int page, HttpServletRequest httpServletRequest) {
        PagePO pagePO = new PagePO(page);
        pagePO.setTotalPage(bookInfoService.getBookCount());

        Map<BookInfoPO, String> bookInfoPOStringMap =
                BookUserMapUtil.getOnePageBookInfo(pagePO, bookInfoService, userService);

        httpServletRequest.setAttribute("books", bookInfoPOStringMap);
        httpServletRequest.setAttribute("pageInfo", pagePO);

        return "index";
    }
}
