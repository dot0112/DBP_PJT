package DBP_equipmentRentalService.main.repository.lectureRoom;

import DBP_equipmentRentalService.main.domain.LectureRoom;
import DBP_equipmentRentalService.main.repository.genericRepository.JpaGenericRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaLectureRoomRepository extends JpaGenericRepository<LectureRoom, String> implements LectureRoomRepository {
    @Autowired
    public JpaLectureRoomRepository(EntityManager em){
        super(em, LectureRoom.class);
    }
}
