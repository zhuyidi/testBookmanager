package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dela on 8/10/17.
 */

@Controller
@RequestMapping(value = "/main")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String welcome(){
        return "hello Spring";
    }

}
