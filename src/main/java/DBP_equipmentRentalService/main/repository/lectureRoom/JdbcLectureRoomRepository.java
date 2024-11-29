package DBP_equipmentRentalService.main.repository.lectureRoom;

import DBP_equipmentRentalService.main.domain.LectureRoom;
import DBP_equipmentRentalService.main.repository.genericRepository.JdbcGenericRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class JdbcLectureRoomRepository extends JdbcGenericRepository<LectureRoom> implements LectureRoomRepository {
    public JdbcLectureRoomRepository(DataSource dataSource){
        super(dataSource, LectureRoom.class);
    }

    @Override
    public Optional<LectureRoom> findById(String id) {
        return Optional.empty();
    }
}
