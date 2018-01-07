package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.BorrowInfoService;
import bookmanager.dao.rowmapper.JdbcRowMapper;
import bookmanager.model.po.BorrowInfoPO;
import bookmanager.model.po.PagePO;
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
    private static final String GET_BORROWDATE_BY_PAGE = "SELECT borrow_date FROM borrow_info LIMIT ?, ?";


    @Autowired
    public BorrowInfoServiceImpl(JdbcOperations jdbc) {
        this.jdbcOperations = jdbc;
    }

    public void save(BorrowInfoPO borrowInfo) {
    }

    public Integer getBorrowInfoCount() {
        return jdbcOperations.queryForObject(GET_BORROWINFO_COUNT, Integer.class);
    }

    public List<String> getBorrowDateByPage(PagePO pagePO) {
        return jdbcOperations.query(GET_BORROWDATE_BY_PAGE, JdbcRowMapper.newInstance(BorrowInfoPO.class),
                pagePO.getBeginIndex(), pagePO.getEveryPage());
    }

}
