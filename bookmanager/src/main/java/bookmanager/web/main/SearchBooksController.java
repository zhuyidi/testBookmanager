package bookmanager.web.main;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

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

//    public String search(HttpServletRequest httpServletRequest) {
//
//
//    }
}
