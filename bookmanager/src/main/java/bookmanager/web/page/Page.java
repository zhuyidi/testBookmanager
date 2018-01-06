package bookmanager.web.page;

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

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by dela on 1/2/18.
 */

@Controller
@RequestMapping(value = "/bookmanager")
public class Page {
    private BookInfoService bookInfoService;
    private UserService userService;

    @Autowired
    public Page(BookInfoService bookInfoService, UserService userService) {
        this.bookInfoService = bookInfoService;
        this.userService = userService;
    }

    @RequestMapping(value = "/{page}", method = GET)
    public String getPageInfo(@PathVariable int page, Model model,
                              HttpServletRequest httpServletRequest) {

        List<BookInfoPO> bookInfoPOS = null;
        PagePO pagePO = new PagePO(page);

        // 分页功能按有分类和没有分类两种情况处理
        if (httpServletRequest.getParameter("id") != null && (!httpServletRequest.getParameter("id").equals(""))) {
            // 取到labelID
            Integer labelID = Integer.valueOf(httpServletRequest.getParameter("id"));

            // 将取到的labelid再重新设置为请求范围的属性
            model.addAttribute("labelid", labelID);

            // 按照labelID查找一页的数据, 并查询到该分类下的书籍信息总记录数, 并设置给pagePO的totalPage
            bookInfoPOS = bookInfoService.getBookByLabelAndPage(pagePO, labelID);
            int bookCount = bookInfoService.getBookCountByLabel(labelID);
            pagePO.setTotalPage((bookCount  % 5 == 0) ? bookCount / 5 : bookCount / 5 + 1);

        } else {
            int bookCount = bookInfoService.getBookCount();
            pagePO.setTotalPage((bookCount  % 5 == 0) ? bookCount / 5 : bookCount / 5 + 1);
            bookInfoPOS = bookInfoService.getBookByPage(pagePO);
        }

        Map<BookInfoPO, String> bookInfoPOStringMap =
                BookUserMapUtil.getBookInfo(bookInfoPOS, userService);

        httpServletRequest.setAttribute("books", bookInfoPOStringMap);
        httpServletRequest.setAttribute("pageInfo", pagePO);

        if(httpServletRequest.getSession().getAttribute("uid") != null) {
            return "main";
        }
        return "index";
    }
}
