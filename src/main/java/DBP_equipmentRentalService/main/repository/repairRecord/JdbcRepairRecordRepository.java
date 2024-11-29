package DBP_equipmentRentalService.main.repository.repairRecord;

import DBP_equipmentRentalService.main.domain.RepairRecord;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcGenericRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class JdbcRepairRecordRepository extends JdbcGenericRepository<RepairRecord> implements RepairRecordRepository {
    public JdbcRepairRecordRepository(DataSource dataSource){
        super(dataSource, RepairRecord.class);
    }

    @Override
    public Optional<RepairRecord> findById(String id){
        return Optional.empty();
    }
}
