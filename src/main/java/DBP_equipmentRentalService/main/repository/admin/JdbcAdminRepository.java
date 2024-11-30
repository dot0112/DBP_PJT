package DBP_equipmentRentalService.main.repository.admin;

import DBP_equipmentRentalService.main.domain.Admin;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class JdbcAdminRepository extends JdbcGenericRepository<Admin> implements AdminRepository {
    @Autowired
    public JdbcAdminRepository(DataSource dataSource) {
        super(dataSource, Admin.class);
    }

    @Override
    public Optional<Admin> findById(String id) {
        String sql = "SELECT * FROM ADMIN WHERE ADMINID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin();
                admin.setAdminId(rs.getString("ID"));
                admin.setPassword(rs.getString("PASSWORD"));
                admin.setName(rs.getString("NAME"));
                admin.setDateOfBirth(rs.getDate("DATEOFBIRTH").toLocalDate());
                admin.setEmail(rs.getString("EMAIL"));
                admin.setPhoneNumber(rs.getString("PHONENUMBER"));
                return Optional.of(admin);
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
