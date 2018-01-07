package bookmanager.dao.dbservice;

import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.PagePO;

import java.util.List;

/**
 * Created by dela on 11/23/17.
 */
public interface BookInfoService {
    // 得到某一级分类下的所有书
    List<BookInfoPO> getBookInfoByBookLabelParentId(int bookParentId);

    // 得到书籍总记录数
    Integer getBookCount();

    // 得到一页的图书信息
    List<BookInfoPO> getBookByPage(PagePO page);

    // 得到一页的图书信息的uid
    List<Integer> getBookInfoUidByPage(PagePO pagePO);

    // 得到某一分类下的一页图书
    List<BookInfoPO> getBookByLabelAndPage(PagePO pagePO, int labelid);

    // 得到某一分类下的图书的总本数
    Integer getBookCountByLabel(int labelId);

    // 得到借阅表里一页的借阅信息里的书名
    List<String> getBookNameByBorrowInfoPage(PagePO pagePO);
}
