package bookmanager.dao.dbservice;

import bookmanager.model.po.BorrowInfoPO;
import bookmanager.model.po.PagePO;
import bookmanager.model.vo.borrowinfo.BorrowInfoVO;

import java.util.List;

/**
 * Created by dela on 11/23/17.
 */
public interface BorrowInfoService {
    // 向借阅表中插入一条信息
    void save(BorrowInfoPO borrowInfo);

    // 查询有多少条借阅记录
    Integer getBorrowInfoCount();

    // 得到一页(10条)的借阅表里的时间/借书者/书名
    List<BorrowInfoVO> getBorrowInfoVOByPage(PagePO pagePO);

    // 得到一页(10条)的借阅表里的书籍所属者
    List<String> getBorrowInfoOwnerByPage(PagePO pagePO);
}
