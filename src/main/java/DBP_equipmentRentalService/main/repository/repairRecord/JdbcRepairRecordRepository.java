package DBP_equipmentRentalService.main.repository.repairRecord;

import DBP_equipmentRentalService.main.domain.LectureRoom;
import DBP_equipmentRentalService.main.domain.RepairRecord;
import DBP_equipmentRentalService.main.domain.RepairRequest;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public class JdbcRepairRecordRepository extends JdbcGenericRepository<RepairRecord> implements RepairRecordRepository {
    @Autowired
    public JdbcRepairRecordRepository(DataSource dataSource){
        super(dataSource, RepairRecord.class);
    }


    @Override
    public Optional<RepairRecord> findByKey(String itemId, LocalDate repairDate) {
        String sql = "SELECT * FROM REPAIRRECORD WHERE ITEMID = ? AND LOCALDATE = ?";
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,itemId);
            pstmt.setDate(2, Date.valueOf(repairDate));
            rs = pstmt.executeQuery();
            if(rs.next()) {
                RepairRecord repairRecord = new RepairRecord();
                repairRecord.setItemId(rs.getString("ITEMID"));
                repairRecord.setRepairDate(rs.getDate("REPAIRDATE").toLocalDate());
                repairRecord.setRepairDesc(rs.getString("REPAIRDESC"));
                repairRecord.setRepairCost(rs.getInt("REPAIRCOST"));
                return Optional.of(repairRecord);
            } else {
                return Optional.empty();
            }
        }catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
}
