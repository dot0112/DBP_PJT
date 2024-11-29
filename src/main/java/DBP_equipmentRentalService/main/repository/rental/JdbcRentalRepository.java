package DBP_equipmentRentalService.main.repository.rental;

import DBP_equipmentRentalService.main.domain.Rental;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class JdbcRentalRepository extends JdbcGenericRepository<Rental> implements RentalRepository {
    @Autowired
    public JdbcRentalRepository(DataSource dataSource){
        super(dataSource, Rental.class);
    }


    @Override
    public Optional<Rental> findById(String id) {
        String sql = "SELECT * FROM RENTAL WHERE RENTALID =?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                Rental rental = new Rental();
                rental.setUserId(rs.getString("USERID"));
                rental.setItemId(rs.getString("ITEMID"));
                rental.setRentalId(rs.getString("RENTALID"));
                rental.setRentalDate(rs.getDate("RENTALDATE").toLocalDate());
                rental.setReturnDate(rs.getDate("RETURNDATE").toLocalDate());
                return Optional.of(rental);
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
