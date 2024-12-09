package DBP_equipmentRentalService.main.repository.lectureRoom;

import DBP_equipmentRentalService.main.domain.LectureRoom;
import DBP_equipmentRentalService.main.domain.LectureRoomId;
import DBP_equipmentRentalService.main.repository.genericRepository.MemoryGenericRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
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
    public Optional<LectureRoom> findById(LectureRoomId lectureRoomId) {
        return Optional.ofNullable(store.get(Arrays.asList(lectureRoomId.getRoomNumber(), lectureRoomId.getBuildingName())));
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

    @Override
    public Boolean update(Map<String, Object> criteria, Map<String, Object> changeValues) {
        List<LectureRoom> objectsToChange = findByCriteria(criteria);
        for (LectureRoom object : objectsToChange) {
            for (Map.Entry<String, Object> entry : changeValues.entrySet()) {
                String fieldName = entry.getKey();
                Object newValue = entry.getValue();

                try {
                    // 필드 접근 및 값 설정
                    Field field = LectureRoom.class.getDeclaredField(fieldName);
                    field.setAccessible(true); // private 필드 접근 허용
                    field.set(object, newValue); // 새로운 값 설정
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException("Error updating field: " + fieldName, e);
                }
            }
        }
        return true;
    }

    public void clearStore() {
        store.clear();
    }
}
