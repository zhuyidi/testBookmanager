package bookmanager.web.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by dela on 1/21/18.
 */
@Controller
public class UnLogin {
    private static final String LOGIN_PAGE = "index";

    @RequestMapping("/unlogin")
    public String unLogin(HttpSession session) throws IOException {
        session.invalidate();

        return "redirect:/bookmanager";
    }
}
