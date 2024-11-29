package DBP_equipmentRentalService.main.repository.item;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcTemplateGenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateItemRepository extends JdbcTemplateGenericRepository<Item> implements ItemRepository {
    @Autowired
    public JdbcTemplateItemRepository(DataSource dataSource){
        super(dataSource, Item.class);
    }


    @Override
    public Optional<Item> findById(String id) {
        List<Item> result = jdbcTemplate.query("SELECT * FROM ITEM WHERE ITEMID = ?", rowMapper(), id);
        return result.stream().findAny();
    }


    @Override
    protected RowMapper<Item> rowMapper() {
        return (rs, rowNum) -> {
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
            return item;
        };
    }
}
