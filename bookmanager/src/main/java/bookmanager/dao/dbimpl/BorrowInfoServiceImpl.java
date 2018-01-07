package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BorrowInfoService;
import bookmanager.dao.rowmapper.JdbcRowMapper;
import bookmanager.model.po.BorrowInfoPO;
import bookmanager.model.po.PagePO;
import bookmanager.model.vo.borrowinfo.BorrowInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dela on 11/23/17.
 */

@Repository
public class BorrowInfoServiceImpl implements BorrowInfoService {
    private JdbcOperations jdbcOperations;

    private static final String GET_BORROWINFO_COUNT = "SELECT COUNT(*) AS COUNT FROM borrow_info";
    private static final String GET_BORROWDATE_BY_PAGE = "SELECT name, borrow_date, ugk_name FROM " +
            "cs_user,book_info AS b,borrow_info WHERE cs_user_uid=uid AND  book_info_pk_id=b.pk_id " +
            "AND b.pk_id IN (SELECT book_info_pk_id FROM (" +
            "SELECT * FROM borrow_info LIMIT ?, ?) AS tp)";
    private static final String GET_BORROWINFO_OWNER_BY_PAGE = "SELECT name FROM cs_user, borrow_info, book_info AS b " +
            "WHERE book_info_pk_id=b.pk_id AND ugk_uid=uid LIMIT ?, ?";

    @Autowired
    public BorrowInfoServiceImpl(JdbcOperations jdbc) {
        this.jdbcOperations = jdbc;
    }

    public void save(BorrowInfoPO borrowInfo) {
    }

    public Integer getBorrowInfoCount() {
        return jdbcOperations.queryForObject(GET_BORROWINFO_COUNT, Integer.class);
    }

    public List<BorrowInfoVO> getBorrowInfoVOByPage(PagePO pagePO) {
        return jdbcOperations.query(GET_BORROWDATE_BY_PAGE, JdbcRowMapper.newInstance(BorrowInfoVO.class),
                pagePO.getBeginIndex(), pagePO.getEveryPage());
    }

    public List<String> getBorrowInfoOwnerByPage(PagePO pagePO) {
        return jdbcOperations.queryForList(GET_BORROWINFO_OWNER_BY_PAGE, String.class,
                pagePO.getBeginIndex(), pagePO.getEveryPage());
    }
}
