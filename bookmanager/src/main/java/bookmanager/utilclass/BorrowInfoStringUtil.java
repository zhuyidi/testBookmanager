package bookmanager.utilclass;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.BorrowInfoPO;
import bookmanager.model.po.PagePO;

import java.util.List;

/**
 * Created by dela on 1/7/18.
 */

public class BorrowInfoStringUtil {
    public static String getOnePageBorrowInfo(PagePO pagePO, List<String> borrowTimes, List<String> bookNames,
                                              List<String> userNames, List<String> owners) {

        StringBuffer result = null;

        result.append(pagePO.getCurrentPage());
        result.append(",");
        result.append(pagePO.getTotalPage());
        result.append(";");

        for(int i = 0; i < borrowTimes.size(); i++) {
            result.append(borrowTimes.get(i));
            result.append(",");
            result.append(bookNames.get(i));
            result.append(",");
            result.append(userNames.get(i));
            result.append(",");
            result.append(owners.get(i));
            result.append(";");
        }

        return result.toString();
    }

}
