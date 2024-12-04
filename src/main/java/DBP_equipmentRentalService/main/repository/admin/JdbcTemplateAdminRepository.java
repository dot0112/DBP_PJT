package DBP_equipmentRentalService.main.repository.admin;

import DBP_equipmentRentalService.main.domain.Admin;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcTemplateGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateAdminRepository extends JdbcTemplateGenericRepository<Admin> implements AdminRepository {
    @Autowired
    public JdbcTemplateAdminRepository(DataSource dataSource) {
        super(dataSource, Admin.class);
    }

    @Override
    public Optional<Admin> findById(String id) {
        List<Admin> result = jdbcTemplate.query("SELECT * FROM ADMIN WHERE ADMINID = ?", rowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    protected RowMapper<Admin> rowMapper() {
        return (rs, rowNum) -> {
            Admin admin = new Admin();
            admin.setAdminId(rs.getString("ADMINID"));
            admin.setPassword(rs.getString("PASSWORD"));
            admin.setName(rs.getString("NAME"));
            admin.setDateOfBirth(rs.getString("DATEOFBIRTH"));
            admin.setEmail(rs.getString("EMAIL"));
            admin.setPhoneNumber(rs.getString("PHONENUMBER"));
            return admin;
        };
    }
}
