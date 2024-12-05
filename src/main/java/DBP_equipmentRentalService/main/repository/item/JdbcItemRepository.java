package DBP_equipmentRentalService.main.repository.item;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class JdbcItemRepository extends JdbcGenericRepository<Item> implements ItemRepository {
    @Autowired
    public JdbcItemRepository(DataSource dataSource) {
        super(dataSource, Item.class);
    }

    @Override
    public Optional<Item> findById(String id) {
        String sql = "SELECT * FROM ITEM WHERE ITEMID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Item item = new Item();
                item.setItemId(rs.getString("ITEMID"));
                item.setItemName(rs.getString("ITEMNAME"));
                item.setItemType(rs.getString("ITEMTYPE"));
                item.setRoomNumber(rs.getString("ROOMNUMBER"));
                item.setBuildingName(rs.getString("BUILDINGNAME"));
                item.setCurrentState(rs.getString("CURRENTSTATE"));
                item.setAdminId(rs.getString("ADMINID"));
                item.setRentableStatus(rs.getString("RENTABLESTATUS"));
                item.setRentalStatus(rs.getString("RENTALSTATUS"));
                return Optional.of(item);
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
