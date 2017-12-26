package bookmanager.web.login;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.BookLabelService;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.BookLabelPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dela on 12/26/17.
 */

@Controller
public class InitServlet {
    private UserService userService;
    private BookInfoService bookInfoService;
    private BookLabelService bookLabelService;

    @Autowired
    public InitServlet(UserService userService,
            BookInfoService bookInfoService, BookLabelService bookLabelService) {
        this.bookInfoService = bookInfoService;
        this.userService = userService;
        this.bookLabelService = bookLabelService;
    }

    @RequestMapping(value = "/bookmanager", method = RequestMethod.GET)
    public String init(HttpServletRequest httpServletRequest) {
        List<BookLabelPO> bookLabelPOS = bookLabelService.getBookLabelById(0);
        List<BookInfoPO> bookInfoPOS = bookInfoService.getTenBookInfo();
//        List<Integer> uidList = bookInfoService.getTenBookInfoUid();
//        List<String> userNames = userService.getUsernamesByIds(uidList);
//        Map<BookInfoPO, String> bookMap = new HashMap<BookInfoPO, String>();

        System.out.println(bookLabelPOS);
        System.out.println(bookInfoPOS);
//        System.out.println(userNames);
//        System.out.println(uidList);
//
//        for (int i = 0; i < bookInfoPOS.size(); i++) {
//            bookMap.put(bookInfoPOS.get(i), userNames.get(i));
//        }
//
        httpServletRequest.setAttribute("labels", bookLabelPOS);
//        httpServletRequest.setAttribute("books", bookMap);
        
        return "index";
    }
}
