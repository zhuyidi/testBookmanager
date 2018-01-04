package bookmanager.web.page;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.BookLabelService;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.PagePO;
import bookmanager.model.po.UserPO;
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



//        System.out.println("pageController中");
//        System.out.println("pageCurrent:" + pagePO.getCurrentPage());
//        System.out.println("totalPage" + pagePO.getTotalPage());


        List<BookInfoPO> bookInfoPOS = null;
        PagePO pagePO = new PagePO(page);

//        if (httpServletRequest.getSession().getAttribute("labelid") != null) {
        if (httpServletRequest.getParameter("id") != null && (!httpServletRequest.getParameter("id").equals(""))) {
            // 取到labelID
            Integer labelID = Integer.valueOf(httpServletRequest.getParameter("id"));
//            Integer idIndex = Integer.valueOf(httpServletRequest.getParameter("id"));
//            System.out.println("parameter:" + httpServletRequest.getParameter("id"));
//            pagePO.setBeginIndex(idIndex);

//            System.out.println("page beginindex :" + pagePO.getBeginIndex());
//            System.out.println("page everypage: " + pagePO.getEveryPage());
//            System.out.println("labelid : " + labelID);

            // 将取到的labelid再重新设置为属性
            model.addAttribute("labelid", labelID);

            // 按照labelID查找一页的数据
            bookInfoPOS = bookInfoService.getBookByLabelAndPage(pagePO, labelID);

            int bookCount = bookInfoService.getBookCountByLabel(labelID);
            System.out.println("label count:" + bookCount);

            pagePO.setTotalPage((bookCount  % 5 == 0) ? bookCount / 5 : bookCount / 5 + 1);

//            httpServletRequest.setAttribute("nextindex", bookInfoPOS.get(bookInfoPOS.size()-1).getPkId());
        } else {
            int bookCount = bookInfoService.getBookCount();
            pagePO.setTotalPage((bookCount  % 5 == 0) ? bookCount / 5 : bookCount / 5 + 1);
            bookInfoPOS = bookInfoService.getBookByPage(pagePO);
        }

        Map<BookInfoPO, String> bookInfoPOStringMap =
                BookUserMapUtil.getBookInfo(bookInfoPOS, userService);

        httpServletRequest.setAttribute("books", bookInfoPOStringMap);
        httpServletRequest.setAttribute("pageInfo", pagePO);

        return "index";
    }
}
