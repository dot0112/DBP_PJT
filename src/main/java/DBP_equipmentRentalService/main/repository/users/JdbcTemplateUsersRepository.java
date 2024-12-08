package DBP_equipmentRentalService.main.repository.users;

import DBP_equipmentRentalService.main.domain.Users;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcTemplateGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateUsersRepository extends JdbcTemplateGenericRepository<Users> implements UsersRepository {
    @Autowired
    public JdbcTemplateUsersRepository(DataSource dataSource) {
        super(dataSource, Users.class);
    }

    @Override
    public Optional<Users> findById(String id) {
        List<Users> result = jdbcTemplate.query("SELECT * FROM USERS WHERE USERID = ?", rowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    protected RowMapper<Users> rowMapper() {
        return (rs, rowNum) -> {
            Users users = new Users();
            users.setUserId(rs.getString("USERID"));
            users.setPassword(rs.getString("PASSWORD"));
            users.setName(rs.getString("NAME"));

            users.setEmail(rs.getString("EMAIL"));
            users.setPhoneNumber(rs.getString("PHONENUMBER"));
            users.setRentalAvailability(rs.getInt("RENTALAVAILABILITY"));

            java.sql.Date dateOfBirth = rs.getDate("DATEOFBIRTH");
            if (dateOfBirth != null) {
                users.setDateOfBirth(dateOfBirth.toLocalDate());
            }
            java.sql.Date rentalAvailabilityDate = rs.getDate("RENTALAVAILABILITYDATE");
            if (rentalAvailabilityDate != null) {
                users.setRentalAvailabilityDate(rentalAvailabilityDate.toLocalDate());
            }
            return users;
        };
    }
}
