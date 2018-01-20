package bookmanager.utilclass;

import bookmanager.model.po.BookInfoPO;
import bookmanager.model.vo.borrowinfo.BorrowInfoVO;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by dela on 1/19/18.
 */

//key为一个BorrowInfoVO(包括借书者名字, 借的书名字, 借书时间)
//value为一个String(书的所属者)
public class BorrowInfoMapUtil {
    public static Map<BorrowInfoVO, String> getBorrowInfo(
                    List<BorrowInfoVO> borrowInfoVOList, List<String> ownerList) {
        Map<BorrowInfoVO, String> borrowInfoVOStringMap = new TreeMap<BorrowInfoVO, String>();

//        for ()


        return borrowInfoVOStringMap;
    }
}
