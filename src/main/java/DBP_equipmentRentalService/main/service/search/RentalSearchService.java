package DBP_equipmentRentalService.main.service.search;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.repository.item.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RentalSearchService {
    private final ItemRepository itemRepository;

    @Autowired
    public RentalSearchService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * Database 의 Item 테이블의 대여 가능한 모든 Tuple 을 읽어옵니다.
     *
     * @return 생성된 Item 객체들의 리스트
     */
    public List<Item> searchAll() {
        return itemRepository.findByCriteria(Map.of("rentableStatus", "대여가능"));
    }

    /**
     * 주어진 이름으로 대여 가능한 Item 을 검색합니다.
     *
     * @param name 검색할 Item 의 이름
     * @return 검색 조건에 맞는 Item 객체들의 리스트
     */
    public List<Item> searchByName(String name) {
        Map<String, Object> criteria = Map.of(
                "itemName", "%" + name.strip() + "%",
                "rentableStatus", "대여가능"
        );
        return itemRepository.findByCriteria(criteria);
    }

    /**
     * 주어진 종류으로 대여 가능한 Item 을 검색합니다.
     *
     * @param type 검색할 Item 의 종류
     * @return 검색 조건에 맞는 Item 객체들의 리스트
     */
    public List<Item> searchByType(String type) {
        Map<String, Object> criteria = Map.of(
                "itemType", "%" + type.strip() + "%",
                "rentableStatus", "대여가능"
        );
        return itemRepository.findByCriteria(criteria);
    }

    /**
     * 주어진 Id 로 Item 을 검색합니다. (대여 가능한 경우)
     *
     * @param id 검색할 Item 의 id
     * @return 검색 조건에 맞는 Item 객체
     */
    public List<Item> searchById(String id) {
        return itemRepository.findByCriteria(Map.of("itemId", id.strip(), "rentableStatus", "대여가능"));
    }

    /**
     * 주어진 강의실로 대여 가능한 Item 을 검색합니다.
     *
     * @param roomNumber   검색할 Item 이 위치한 강의실 번호
     * @param buildingName 검색할 Item 이 위치한 건물 이름
     * @return 검색 조건에 맞는 Item 객체들의 리스트
     */
    public List<Item> searchByLectureRoom(String roomNumber, String buildingName) {
        Map<String, Object> criteria = Map.of(
                "roomNumber", roomNumber,
                "buildingName", buildingName,
                "rentableStatus", "대여가능"
        );
        return itemRepository.findByCriteria(criteria);
    }
}
