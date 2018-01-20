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

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
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

    @RequestMapping(value = "/search", method = POST)
    public String search(HttpServletRequest httpServletRequest, Model model) throws UnsupportedEncodingException {

        PagePO pagePO = new PagePO(1);

//        String keyword = httpServletRequest.getParameter("keyword");
        String keyword = new String(httpServletRequest.getParameter("keyword").getBytes("iso-8859-1"), "utf-8");

        System.out.println("keyword:" + keyword);
        model.addAttribute("keyword", keyword);
        keyword = "%" + keyword + "%";
        List<BookInfoPO> bookInfoPOList = bookInfoService.getBookInfoByNAOByPage(keyword, pagePO);


        System.out.println(bookInfoPOList);

        int bookCount = bookInfoService.getBookCountByNAO(keyword);
        pagePO.setTotalPage((bookCount % 5 == 0) ? bookCount / 5 : bookCount / 5 + 1);

        Map<BookInfoPO, String> bookInfoPOStringMap = BookUserMapUtil.getBookInfo(bookInfoPOList, userService);
        model.addAttribute("books", bookInfoPOStringMap);
        model.addAttribute("pageInfo", pagePO);

        return "showresult";
    }

    @RequestMapping(value = "/search/{keyword}/{page}", method = GET)
    public String searchOnePage(@PathVariable String keyword, @PathVariable int page, HttpServletRequest httpServletRequest, Model model) throws UnsupportedEncodingException {
        PagePO pagePO = new PagePO(page);

//        String keyword = httpServletRequest.getParameter("keyword");
        keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
        model.addAttribute("keyword", keyword);

        keyword = "%" + keyword + "%";
        List<BookInfoPO> bookInfoPOList = bookInfoService.getBookInfoByNAOByPage(keyword, pagePO);

        System.out.println(bookInfoPOList);

        int bookCount = bookInfoService.getBookCountByNAO(keyword);
        pagePO.setTotalPage((bookCount % 5 == 0) ? bookCount / 5 : bookCount / 5 + 1);

        Map<BookInfoPO, String> bookInfoPOStringMap = BookUserMapUtil.getBookInfo(bookInfoPOList, userService);
        model.addAttribute("books", bookInfoPOStringMap);
        model.addAttribute("pageInfo", pagePO);

        return "showresult";
    }
}
