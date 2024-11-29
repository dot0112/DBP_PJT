package DBP_equipmentRentalService.main.repository.lectureRoom;

import DBP_equipmentRentalService.main.domain.LectureRoom;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LectureRoomRepository {
    LectureRoom save(LectureRoom lectureRoom);
    Optional<LectureRoom> findByKey(String roomNumber, String buildingName);
    List<LectureRoom> findByCriteria(Map<String, Object> criteria);
    List<LectureRoom> findAll();
}
