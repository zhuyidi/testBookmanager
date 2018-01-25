package bookmanager.web.page;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.BorrowInfoService;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.BorrowInfoPO;
import bookmanager.model.po.PagePO;
import bookmanager.model.vo.borrowinfo.BorrowInfoVO;
import bookmanager.utilclass.BorrowInfoMapUtil;
import bookmanager.utilclass.BorrowInfoStringUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by dela on 1/6/18.
 */

@Controller
public class BorrowInfoPageController {
    BorrowInfoService borrowInfoService;
    UserService userService;
    BookInfoService bookInfoService;

    @Autowired
    public BorrowInfoPageController(BorrowInfoService borrowInfoService,
            UserService userService, BookInfoService bookInfoService) {
        this.borrowInfoService = borrowInfoService;
        this.userService = userService;
        this.bookInfoService = bookInfoService;
    }

    @RequestMapping(value = "/borrowinfo/{page}", method = POST)
    public void getPageInfo(@PathVariable int page, HttpServletResponse httpServletResponse, Model model) throws IOException, JSONException {
        PagePO pagePO = new PagePO(page, 10);

        System.out.println("mapping到该控制器了");

        // 拿到借阅信息的数量, 接下来要拿到一页的借阅信息,
        // 拼接成字符串, 再加上当前页码和总页数, 然后通过ajax发送给前端.

        int total = borrowInfoService.getBorrowInfoCount();
        pagePO.setTotalPage((total  % 5 == 0) ? total / 5 : total / 5 + 1);

        // 得到一页的借阅信息
        // 得到一页的书名/借书者/借阅时间
        List<BorrowInfoVO> borrowInfoVOList = borrowInfoService.getBorrowInfoVOByPage(pagePO);
        // 得到一页的所属者
        List<String> ownerList = borrowInfoService.getBorrowInfoOwnerByPage(pagePO);

        JSONObject borrowJson = new JSONObject();
//        JSONArray borrowJson = new JSONArray();
        Map<BorrowInfoVO, String> borrowInfoMap = BorrowInfoMapUtil.getBorrowInfo(borrowInfoVOList, ownerList);
        System.out.println("map: " + borrowInfoMap);

        int i = 0;
        for(Map.Entry<BorrowInfoVO, String> key : borrowInfoMap.entrySet()) {
            borrowJson.put("time" + i, key.getKey().getBorrow_date());
            borrowJson.put("message" + i, key.getValue());
//            System.out.println("getkey:" + borrowInfoMap.get(key), borrowInfoMap.);
//            System.out.println(temp);
//            borrowJson.put(temp);
            i++;
        }

//        String result = BorrowInfoStringUtil.getOnePageBorrowInfo(pagePO, borrowInfoVOList, ownerList);

        System.out.println("json：" + borrowJson.toString());

        model.addAttribute("borrowPage", pagePO);

        httpServletResponse.setContentType("text/json;charset=UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.append(borrowJson.toString());

        System.out.println("发送json之前");
        out.flush();



        // 应该使用ajax返回给前端的字符串

    }
}
