package bookmanager.dao.dbservice;

import bookmanager.model.po.BorrowInfoPO;

/**
 * Created by dela on 11/23/17.
 */
public interface BorrowInfoService {
    // 向借阅表中插入一条信息
    void save(BorrowInfoPO borrowInfo);

    // 查询有多少条借阅记录
    Integer getBorrowInfoCount();
}
