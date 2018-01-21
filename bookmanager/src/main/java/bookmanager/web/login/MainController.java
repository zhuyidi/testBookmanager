package bookmanager.web.login;

import bookmanager.dao.dbservice.BorrowInfoService;
import bookmanager.model.po.PagePO;
import bookmanager.model.vo.borrowinfo.BorrowInfoVO;
import bookmanager.utilclass.BorrowInfoMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by dela on 1/21/18.
 */

@Controller
public class MainController {
    private BorrowInfoService borrowInfoService;

    @Autowired
    public MainController(BorrowInfoService borrowInfoService) {
        this.borrowInfoService = borrowInfoService;
    }

    @RequestMapping("mainorindex")
    public String mainOrindex(HttpSession session) {

        Object sessionUid = null;

        try {
            sessionUid = session.getAttribute("uid");
        } catch (NullPointerException e) {
        } finally {
            if(sessionUid == null) {
                return "index";
            }

            return "main";
        }
    }

    @RequestMapping("/main")
    public String getMainPage(HttpSession session) {
        // 初始化main页面的借阅信息(获取10条)
        PagePO borrowPagePO = new PagePO(1, 10);
        List<BorrowInfoVO> borrowInfoVOList = borrowInfoService.getBorrowInfoVOByPage(borrowPagePO);
        List<String> ownerList = borrowInfoService.getBorrowInfoOwnerByPage(borrowPagePO);
        Map<BorrowInfoVO, String> borrowInfoMap = BorrowInfoMapUtil.getBorrowInfo(borrowInfoVOList, ownerList);

        int bookCount = borrowInfoService.getBorrowInfoCount();
        borrowPagePO.setTotalCount(bookCount);
        borrowPagePO.setTotalPage((bookCount % 10 == 0) ? bookCount / 10 : bookCount / 10 + 1);

        session.setAttribute("borrowPage", borrowPagePO);
        session.setAttribute("borrowInfo", borrowInfoMap);
//                model.addAttribute("borrowPage", borrowPagePO);
//                model.addAttribute("borrowInfo", borrowInfoMap);

        return "main";
    }
}
