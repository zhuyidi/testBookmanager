package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.model.po.BookInfoPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dela on 11/23/17.
 */

@Repository
public class BookInfoServiceImpl implements BookInfoService {
    // 该数据库操作方法还没有实现
    public void save(BookInfoPO bookInfo) {

    }

    // 该数据库操作方法还没有实现
    public BookInfoPO getBookInfoByBookId(int bookId) {
        return null;
    }

    // 该数据库操作方法还没有实现
    public List<BookInfoPO> getListBookInfoByNAO(String keywords) {
        return null;
    }

    // 该数据库操作方法还没有实现
    public void updateBookInfo(BookInfoPO bookInfo) {

    }
}
