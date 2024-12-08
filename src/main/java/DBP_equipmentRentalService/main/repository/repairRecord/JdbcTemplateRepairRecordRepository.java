package DBP_equipmentRentalService.main.repository.repairRecord;

import DBP_equipmentRentalService.main.domain.RepairRecord;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcTemplateGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateRepairRecordRepository extends JdbcTemplateGenericRepository<RepairRecord> implements RepairRecordRepository {
    @Autowired
    public JdbcTemplateRepairRecordRepository(DataSource dataSource) {
        super(dataSource, RepairRecord.class);
    }

    @Override
    public Optional<RepairRecord> findById(String itemId, LocalDate repairDate) {
        List<RepairRecord> result = jdbcTemplate.query("SELECT * FROM REPAIRRECORD WHERE ITEMID = ? AND REPAIRDATE = ?", rowMapper(), Arrays.asList(itemId, Date.valueOf(repairDate)));
        return result.stream().findAny();
    }

    @Override
    protected RowMapper<RepairRecord> rowMapper() {
        return (rs, rowNum) -> {
            RepairRecord repairRecord = new RepairRecord();
            repairRecord.setItemId(rs.getString("ITEMID"));
            repairRecord.setRepairDesc(rs.getString("REPAIRDESC"));
            repairRecord.setRepairCost(rs.getInt("COST"));
            java.sql.Date repairDate = rs.getDate("REPAIRDATE");
            if (repairDate != null) {
                repairRecord.setRepairDate(repairDate.toLocalDate());
            }
            return repairRecord;
        };
    }
}
