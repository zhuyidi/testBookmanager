package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BookInfoService;
import bookmanager.dao.rowmapper.JdbcRowMapper;
import bookmanager.model.po.BookInfoPO;
import bookmanager.model.po.PagePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

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
            "SELECT pk_id FROM book_label WHERE parent_id = ?)) AND amount > 0";
    private final static String GET_BOOK_COUNT = "SELECT COUNT(*) AS COUNT FROM book_info WHERE amount > 0";
    private final static String GET_ONE_PAGE_BOOKINFO = "SELECT * FROM book_info WHERE amount >0 LIMIT ? , ?";
    private final static String GET_ONE_PAGE_BOOKINFO_UID = "SELECT pk_id FROM book_info where amount > 0 LIMIT ?, ?";
    private final static String GET_BOOK_BY_LABEL_AND_PAGE = "SELECT * FROM book_info where pk_id IN (" +
            "SELECT book_info_pk_id FROM book_relation_label WHERE book_label_pk_id IN (" +
            "SELECT pk_id FROM book_label WHERE parent_id = ?)) AND amount > 0 LIMIT ?, ?";
    private final static String GET_BOOK_COUNT_BY_LABEL = "SELECT COUNT(*) AS COUNT FROM book_info where pk_id IN (" +
            "SELECT book_info_pk_id FROM book_relation_label WHERE book_label_pk_id IN (" +
            "SELECT pk_id FROM book_label WHERE parent_id = ?)) AND amount > 0";
    private final static String GET_BOOKNAME_BY_BORROWINFO_PAGE =
            "SELECT ugk_name FROM book_info WHERE pk_id in(SELECT book_info_pk_id FROM(" +
                    "SELECT book_info_pk_id FROM borrow_info limit ?, ?) AS temp)";


    @Autowired
    public BookInfoServiceImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    // 通过一级分类的ID查询该一级分类下的所有的书
    public List<BookInfoPO> getBookInfoByBookLabelParentId(int bookParentId) {
        return jdbcOperations.query(GET_BOOKINFO_BY_BOOKLABEL_PARENT_ID,
                JdbcRowMapper.newInstance(BookInfoPO.class), bookParentId);
    }

    public Integer getBookCount() {
        return jdbcOperations.queryForObject(GET_BOOK_COUNT, Integer.class);
    }

    public List<BookInfoPO> getBookByPage(PagePO page) {
        return jdbcOperations.query(GET_ONE_PAGE_BOOKINFO,
                JdbcRowMapper.newInstance(BookInfoPO.class), page.getBeginIndex(), page.getEveryPage());
    }

    public List<Integer> getBookInfoUidByPage(PagePO pagePO) {
        return jdbcOperations.queryForList(GET_ONE_PAGE_BOOKINFO_UID,
                Integer.class, pagePO.getBeginIndex(), pagePO.getEveryPage());
    }

    public List<BookInfoPO> getBookByLabelAndPage(PagePO pagePO, int labelid) {
        System.out.println("everypage: " + pagePO.getEveryPage());
        return jdbcOperations.query(GET_BOOK_BY_LABEL_AND_PAGE,
                JdbcRowMapper.newInstance(BookInfoPO.class), labelid, pagePO.getBeginIndex(), pagePO.getEveryPage());
    }

    public Integer getBookCountByLabel(int labelId) {
        return jdbcOperations.queryForObject(GET_BOOK_COUNT_BY_LABEL, Integer.class, labelId);
    }


    public List<String> getBookNameByBorrowInfoPage(PagePO pagePO) {
        return jdbcOperations.queryForList(GET_BOOKNAME_BY_BORROWINFO_PAGE,
                String.class, pagePO.getBeginIndex(), pagePO.getEveryPage());
    }

}
