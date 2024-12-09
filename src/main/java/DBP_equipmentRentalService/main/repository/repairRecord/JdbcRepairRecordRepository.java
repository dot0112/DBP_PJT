package DBP_equipmentRentalService.main.repository.repairRecord;

import DBP_equipmentRentalService.main.domain.RepairRecord;
import DBP_equipmentRentalService.main.domain.RepairRecordId;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class JdbcRepairRecordRepository extends JdbcGenericRepository<RepairRecord> implements RepairRecordRepository {
    @Autowired
    public JdbcRepairRecordRepository(DataSource dataSource) {
        super(dataSource, RepairRecord.class);
    }


    @Override
    public Optional<RepairRecord> findById(RepairRecordId repairRecordId) {
        String sql = "SELECT * FROM REPAIRRECORD WHERE ITEMID = ? AND REPAIRDATE = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, repairRecordId.getItemId());
            pstmt.setDate(2, Date.valueOf(repairRecordId.getRepairDate()));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                RepairRecord repairRecord = new RepairRecord();
                repairRecord.setItemId(rs.getString("ITEMID"));
                repairRecord.setRepairDesc(rs.getString("REPAIRDESC"));
                repairRecord.setRepairCost(rs.getInt("REPAIRCOST"));
                repairRecord.setRepairDate(rs.getDate("REPAIRDATE").toLocalDate());
                return Optional.of(repairRecord);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
}
