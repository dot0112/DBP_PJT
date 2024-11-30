package DBP_equipmentRentalService.main.repository.returns;

import DBP_equipmentRentalService.main.domain.Returns;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcTemplateGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateReturnsRepository extends JdbcTemplateGenericRepository<Returns> implements ReturnsRepository {
    @Autowired
    public JdbcTemplateReturnsRepository(DataSource dataSource) {
        super(dataSource, Returns.class);
    }

    @Override
    public Optional<Returns> findById(String id) {
        List<Returns> result = jdbcTemplate.query("SELECT * FROM RETURNS WHERE RETURNID = ?", rowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    protected RowMapper<Returns> rowMapper() {
        return (rs, rowNum) -> {
            Returns returns = new Returns();
            returns.setUserId(rs.getString("USERID"));
            returns.setItemId(rs.getString("ITEMID"));
            returns.setRentalId(rs.getString("RENTALID"));
            returns.setReturnId(rs.getString("RETURNID"));
            returns.setActualReturnDate(rs.getDate("ACTUALRETURNDATE").toLocalDate());
            returns.setRepairRequest(rs.getString("REPAIR_REQUEST"));
            return returns;
        };
    }
}
