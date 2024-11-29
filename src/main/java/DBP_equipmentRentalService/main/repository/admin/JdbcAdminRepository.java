package DBP_equipmentRentalService.main.repository.admin;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class JdbcAdminRepository implements AdminRepository {
    private final DataSource dataSource;
    public JdbcAdminRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }
}
