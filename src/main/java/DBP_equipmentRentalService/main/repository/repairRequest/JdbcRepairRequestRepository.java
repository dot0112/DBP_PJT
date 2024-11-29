package DBP_equipmentRentalService.main.repository.repairRequest;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.domain.RepairRequest;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcGenericRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class JdbcRepairRequestRepository extends JdbcGenericRepository<RepairRequest> implements RepairRequestRepository {
    public JdbcRepairRequestRepository(DataSource dataSource){
        super(dataSource, RepairRequest.class);
    }

    @Override
    public Optional<RepairRequest> findById(String id) {
        String sql = "SELECT * FROM REPAIRREQUEST WHERE REPAIRREQUESTID = ?";
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                RepairRequest repairRequest = new RepairRequest();
                repairRequest.setItemId(rs.getString("ITEMID"));
                repairRequest.setUserId(rs.getString("USERID"));
                repairRequest.setRepairRequestId(rs.getString("REPAIRREQUESTID"));
                repairRequest.setItemName(rs.getString("ITEMNAME"));
                return Optional.of(repairRequest);
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
