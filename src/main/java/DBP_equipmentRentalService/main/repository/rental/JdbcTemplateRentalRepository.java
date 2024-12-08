package DBP_equipmentRentalService.main.repository.rental;

import DBP_equipmentRentalService.main.domain.Rental;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcTemplateGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateRentalRepository extends JdbcTemplateGenericRepository<Rental> implements RentalRepository {
    @Autowired
    public JdbcTemplateRentalRepository(DataSource dataSource) {
        super(dataSource, Rental.class);
    }

    @Override
    public Optional<Rental> findById(String id) {
        List<Rental> result = jdbcTemplate.query("SELECT * FROM RENTAL WHERE RENTALID = ?", rowMapper(), id);
        return result.stream().findAny();
    }


    @Override
    protected RowMapper<Rental> rowMapper() {
        return (rs, rowNum) -> {
            Rental rental = new Rental();
            rental.setUserId(rs.getString("USERID"));
            rental.setItemId(rs.getString("ITEMID"));
            rental.setRentalId(rs.getString("RENTALID"));
            java.sql.Date rentalDate = rs.getDate("RENTALDATE");
            if (rentalDate != null) {
                rental.setRentalDate(rentalDate.toLocalDate());
            }
            java.sql.Date returnDate = rs.getDate("RETURNDATE");
            if (returnDate != null) {
                rental.setReturnDate(returnDate.toLocalDate());
            }
            return rental;
        };
    }

}
