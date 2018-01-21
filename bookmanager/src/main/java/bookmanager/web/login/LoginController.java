package bookmanager.web.login;

import bookmanager.dao.dbservice.BookLabelService;
import bookmanager.dao.dbservice.BorrowInfoService;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.PagePO;
import bookmanager.model.vo.borrowinfo.BorrowInfoVO;
import bookmanager.model.vo.login.UserLoginVO;
import bookmanager.utilclass.BorrowInfoMapUtil;
import bookmanager.utilclass.MD5;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by dela on 11/27/17.
 */

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    private UserService userService;
    private BookLabelService bookLabelService;
    private BorrowInfoService borrowInfoService;

    @Autowired
    public LoginController(UserService userService, BookLabelService bookLabelService,
                            BorrowInfoService borrowInfoService) {
        this.userService = userService;
        this.bookLabelService = bookLabelService;
        this.borrowInfoService = borrowInfoService;
    }

    // 当URL为/login且请求类型为GET的时候, 默认返回index.jsp页面(即未登录主页面)
    @RequestMapping(method = RequestMethod.GET)
    public String showMainPage() {
        return "index";
    }

    // 当URL为/login且请求类型为POST的时候, 处理用户登录表单
    @RequestMapping(method = RequestMethod.POST)
    public String userLogin(@Valid UserLoginVO user, HttpSession session, Model model,
                            HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        String username = new String(user.getName().getBytes("iso-8859-1"), "utf-8");

        if (!username.equals("")) {
            // 登录成功, 初始化main页面的借阅信息, 然后跳转到main.jsp页面
            if (checkPassword(username, user.getPassword(), user)) {
                // 将uid设置到session里
                session.setAttribute("uid", user.getUid());

//                // 初始化main页面的借阅信息(获取10条)
//                PagePO borrowPagePO = new PagePO(1, 10);
//                List<BorrowInfoVO> borrowInfoVOList = borrowInfoService.getBorrowInfoVOByPage(borrowPagePO);
//                List<String> ownerList = borrowInfoService.getBorrowInfoOwnerByPage(borrowPagePO);
//                Map<BorrowInfoVO, String> borrowInfoMap = BorrowInfoMapUtil.getBorrowInfo(borrowInfoVOList, ownerList);
//
//                int bookCount = borrowInfoService.getBorrowInfoCount();
//                borrowPagePO.setTotalCount(bookCount);
//                borrowPagePO.setTotalPage((bookCount % 10 == 0) ? bookCount / 10 : bookCount / 10 + 1);
//
//                session.setAttribute("borrowPage", borrowPagePO);
//                session.setAttribute("borrowInfo", borrowInfoMap);
////                model.addAttribute("borrowPage", borrowPagePO);
////                model.addAttribute("borrowInfo", borrowInfoMap);

                return "redirect:/main";
            } else {
                // 若不成功, 则带上错误参数返回index.jsp页面(未登录前首页)
                return "redirect:index.jsp?error=yes";
            }
        } else {
            return "redirect:index.jsp?error=yes";
        }

    }

    private boolean checkPassword(String username, String password, UserLoginVO user) {
        UserLoginVO userLoginVO = userService.getPasswordAndUidByName(username);
        String codePasswd = MD5.codeByMD5(password);
        if (userLoginVO.getPassword().equals(codePasswd)) {
            user.setUid(userLoginVO.getUid());
            return true;
        }
        return false;
    }

}
