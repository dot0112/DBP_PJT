package DBP_equipmentRentalService.main.repository.repairRequest;

import DBP_equipmentRentalService.main.domain.RepairRequest;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcTemplateGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateRepairRequestRepository extends JdbcTemplateGenericRepository<RepairRequest> implements RepairRequestRepository {
    @Autowired
    public JdbcTemplateRepairRequestRepository(DataSource dataSource) {
        super(dataSource, RepairRequest.class);
    }

    @Override
    public Optional<RepairRequest> findById(String id) {
        List<RepairRequest> result = jdbcTemplate.query("SELECT * FROM REPAIRREQUEST WHERE REPAIRREQUESTID = ?", rowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    protected RowMapper<RepairRequest> rowMapper() {
        return (rs, rowNum) -> {
            RepairRequest repairRequest = new RepairRequest();
            repairRequest.setItemId(rs.getString("ITEMID"));
            repairRequest.setUserId(rs.getString("USERID"));
            repairRequest.setRepairRequestId(rs.getString("REPAIRREQUESTID"));
            repairRequest.setItemName(rs.getString("ITEMNAME"));
            repairRequest.setIsRepaired(rs.getInt("ISREPAIRED"));
            java.sql.Date requestDate = rs.getDate("REQUESTDATE");
            if (requestDate != null) {
                repairRequest.setRequestDate(requestDate.toLocalDate());
            }
            return repairRequest;
        };
    }
}
