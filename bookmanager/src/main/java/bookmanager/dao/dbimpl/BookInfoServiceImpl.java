package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.rowmapper.JdbcRowMapper;
import bookmanager.model.po.BookInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import sun.text.resources.cldr.bn.FormatData_bn_IN;

import java.util.Date;
import java.util.List;

/**
 * Created by dela on 11/23/17.
 */

@Repository
public class BookInfoServiceImpl implements BookInfoService {
    private JdbcOperations jdbcOperations;

    private final static String GET_BOOKINFO_BY_BOOKLABEL_PARENT_ID =
                    "SELECT * FROM book_info where pk_id IN (" +
                            "SELECT book_info_pk_id FROM book_relation_label WHERE book_label_pk_id IN (" +
                            "SELECT pk_id FROM book_label WHERE parent_id = ?))";

    @Autowired
    public BookInfoServiceImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

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

    public Date test(int id) {
        return (Date) jdbcOperations.queryForObject("select upload_date from book_info where pk_id = ?",
                JdbcRowMapper.newInstance(Date.class), id);
    }



    // 通过一级分类的ID查询该一级分类下的所有的书
    public List<BookInfoPO> getBookInfoByBookLabelParentId(int bookParentId) {
        return jdbcOperations.query(GET_BOOKINFO_BY_BOOKLABEL_PARENT_ID,
                JdbcRowMapper.newInstance(BookInfoPO.class), bookParentId);
    }
}
