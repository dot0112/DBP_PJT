package DBP_equipmentRentalService.main.repository.users;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.domain.Users;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class JdbcUsersRepository extends JdbcGenericRepository<Users> implements UsersRepository {
    @Autowired
    public JdbcUsersRepository(DataSource dataSource) {
        super(dataSource, Users.class);
    }

    @Override
    public Optional<Users> findById(String id) {
        String sql = "SELECT * FROM USERS WHERE USERID = ?";
        Connection conn = null;
        PreparedStatement pstmt =null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                Users users = new Users();
                users.setUserId(rs.getString("USERID"));
                users.setPassword(rs.getString("PASSWORD"));
                users.setName(rs.getString("NAME"));
                users.setDateOfBirth(rs.getDate("DATEOFBIRTH").toLocalDate());
                users.setEmail(rs.getString("EMAIL"));
                users.setPhoneNumber(rs.getString("PHONENUMBER"));
                users.setRentalAvailability(rs.getInt("RENTALAVAILABILITY"));
                users.setRentalAvailabilityDate(rs.getDate("RENTALAVAILABILITYDATE").toLocalDate());
                return Optional.of(users);
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
