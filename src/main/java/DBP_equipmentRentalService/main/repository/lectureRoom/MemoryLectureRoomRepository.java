package DBP_equipmentRentalService.main.repository.lectureRoom;

import DBP_equipmentRentalService.main.domain.LectureRoom;
import DBP_equipmentRentalService.main.repository.genericRepository.MemoryGenericRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryLectureRoomRepository extends MemoryGenericRepository<LectureRoom> implements LectureRoomRepository {
    private static final Map<List<Object>, LectureRoom> store = new HashMap<>();


    @Override
    public LectureRoom save(LectureRoom lectureRoom) {
        store.put(Arrays.asList(lectureRoom.getRoomNumber(), lectureRoom.getBuildingName()), lectureRoom);
        return lectureRoom;
    }

    @Override
    public Optional<LectureRoom> findById(String roomNumber, String buildingName) {
        return Optional.ofNullable(store.get(Arrays.asList(roomNumber, buildingName)));
    }

    @Override
    public List<LectureRoom> findByCriteria(Map<String, Object> criteria) {
        return store.values().stream()
                .filter(lectureRoom -> matchesCriteria(lectureRoom, criteria))
                .collect(Collectors.toList());
    }

    @Override
    public List<LectureRoom> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
