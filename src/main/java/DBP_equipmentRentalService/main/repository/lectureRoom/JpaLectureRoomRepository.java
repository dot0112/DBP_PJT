package DBP_equipmentRentalService.main.repository.lectureRoom;

import DBP_equipmentRentalService.main.domain.LectureRoom;
import DBP_equipmentRentalService.main.repository.genericRepository.JpaGenericRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaLectureRoomRepository extends JpaGenericRepository<LectureRoom> implements LectureRoomRepository {
    @Autowired
    public JpaLectureRoomRepository(EntityManager em){
        super(em, LectureRoom.class);
    }

    @Override
    public Optional<LectureRoom> findByKey(String roomNumber, String buildingName) {
        String jpql = "SELECT e FROM LECTUREROOM e WHERE e.ROOMNUMBER = :ROOMNUMBER AND e.BUILDINGNAME = :BUILDINGNAME";
        TypedQuery<LectureRoom> query = em.createQuery(jpql, LectureRoom.class);

        query.setParameter("ROOMNUMBER", roomNumber);
        query.setParameter("BUILDINGNAME", buildingName);

        LectureRoom lectureRoom = query.getSingleResult();
        return Optional.ofNullable(lectureRoom);
    }
}
