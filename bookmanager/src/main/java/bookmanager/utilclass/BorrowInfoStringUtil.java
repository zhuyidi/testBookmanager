package bookmanager.utilclass;

import bookmanager.model.po.PagePO;
import bookmanager.model.vo.borrowinfo.BorrowInfoVO;

import java.util.List;

/**
 * Created by dela on 1/7/18.
 */

public class BorrowInfoStringUtil {
    public static String getOnePageBorrowInfo(PagePO pagePO,
          List<BorrowInfoVO> borrowInfoVOList, List<String> ownerList) {

        StringBuffer result = null;

        result.append(pagePO.getCurrentPage());
        result.append(",");
        result.append(pagePO.getTotalPage());
        result.append(";");

        for(int i = 0; i < ownerList.size(); i++) {
            result.append(borrowInfoVOList.get(i).getBorrow_date());
            result.append(",");
            result.append(borrowInfoVOList.get(i).getUgk_name());
            result.append(",");
            result.append(borrowInfoVOList.get(i).getName());
            result.append(",");
            result.append(ownerList.get(i));
            result.append(";");
        }

        return result.toString();
    }

}
