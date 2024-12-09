package DBP_equipmentRentalService.main.repository.lectureRoom;

import DBP_equipmentRentalService.main.domain.LectureRoom;
import DBP_equipmentRentalService.main.domain.LectureRoomId;
import DBP_equipmentRentalService.main.repository.genericRepository.JpaGenericRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaLectureRoomRepository extends JpaGenericRepository<LectureRoom> implements LectureRoomRepository {
    @Autowired
    public JpaLectureRoomRepository(EntityManager em) {
        super(em, LectureRoom.class);
    }

    @Override
    public Optional<LectureRoom> findById(LectureRoomId lectureRoomId) {
        String jpql = "SELECT e FROM LectureRoom e WHERE e.roomNumber = :roomNumber AND e.buildingName = :buildingName";
        TypedQuery<LectureRoom> query = em.createQuery(jpql, LectureRoom.class);

        query.setParameter("roomNumber", lectureRoomId.getRoomNumber());
        query.setParameter("buildingName", lectureRoomId.getBuildingName());

        try {
            LectureRoom lectureRoom = query.getSingleResult();
            return Optional.of(lectureRoom);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
