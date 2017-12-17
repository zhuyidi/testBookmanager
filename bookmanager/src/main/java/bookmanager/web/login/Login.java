package bookmanager.web.login;

import bookmanager.dao.dbservice.UserService;
import bookmanager.model.vo.login.UserLoginVO;
import bookmanager.utilclass.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * Created by dela on 11/27/17.
 */

@Controller
@RequestMapping(value = "/login")
public class Login {
    private UserService userService;

    @Autowired
    public Login(UserService userService) {
        this.userService = userService;
    }

    // 当URL为/login且请求类型为GET的时候, 默认返回index.jsp页面(即未登录主页面)
    @RequestMapping(method = RequestMethod.GET)
    public String showMainPage() {
        return "index";
    }

    // 当URL为/login且请求类型为POST的时候, 处理用户登录表单
    @RequestMapping(method = RequestMethod.POST)
    public String userLogin(UserLoginVO user, HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("check之前");
        String username = new String(user.getName().getBytes("iso-8859-1"), "utf-8");
        System.out.println(user.getUid());
        if (checkPassword(username, user.getPassword())) {
            // 登录检测成功之后, 返回main.jsp视图(已登录首页)

            request.getSession().setAttribute("uid", user.getUid());
            System.out.println("check之后");

            return "main";
        } else {
            // 若不成功, 则返回index.jsp视图(未登录前首页)
            return "index";
        }

    }

    private boolean checkPassword(String username, String password) {
        System.out.println(1);
        UserLoginVO userLoginVO = userService.getPasswordAndUidByName(username);
        System.out.println(2);
        String codePasswd = MD5.codeByMD5(password);
        System.out.println(3);
        return userLoginVO.getPassword().equals(codePasswd);
    }

}
