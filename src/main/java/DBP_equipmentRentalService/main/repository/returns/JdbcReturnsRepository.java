package DBP_equipmentRentalService.main.repository.returns;

import DBP_equipmentRentalService.main.domain.Returns;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class JdbcReturnsRepository extends JdbcGenericRepository<Returns> implements ReturnsRepository {
    @Autowired
    public JdbcReturnsRepository(DataSource dataSource) {
        super(dataSource, Returns.class);
    }

    @Override
    public Optional<Returns> findById(String id) {
        String sql = "SELECT * FROM RETURNS WHERE RETURNID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Returns returns = new Returns();
                returns.setUserId(rs.getString("USERID"));
                returns.setItemId(rs.getString("ITEMID"));
                returns.setRentalId(rs.getString("RENTALID"));
                returns.setReturnId(rs.getString("RETURNID"));
                returns.setActualReturnDate(rs.getDate("ACTUALRETURNDATE").toLocalDate());
                returns.setRepairRequest(rs.getString("REPAIR_REQUEST"));
                return Optional.of(returns);
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
