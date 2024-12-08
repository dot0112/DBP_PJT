package DBP_equipmentRentalService.main.service.search;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.repository.item.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ItemSearchService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemSearchService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * Database 의 Item 테이블의 모든 Tuple 을 읽어옵니다.
     *
     * @return 생성된 Item 객체들의 리스트
     */
    public List<Item> searchAll() {
        return itemRepository.findAll();
    }

    /**
     * 주어진 이름으로 Item 을 검색합니다.
     *
     * @param name 검색할 Item 의 이름
     * @return 검색 조건에 맞는 Item 객체들의 리스트
     */
    public List<Item> searchByName(String name) {
        Map<String, Object> criteria = Map.of(
                "itemName", "%" + name.strip() + "%"
        );
        return itemRepository.findByCriteria(criteria);
    }

    /**
     * 주어진 종류으로 Item 을 검색합니다.
     *
     * @param type 검색할 Item 의 종류
     * @return 검색 조건에 맞는 Item 객체들의 리스트
     */
    public List<Item> searchByType(String type) {
        Map<String, Object> criteria = Map.of(
                "itemType", "%" + type.strip() + "%"
        );
        return itemRepository.findByCriteria(criteria);
    }

    /**
     * 주어진 Id 로 Item 을 검색합니다.
     *
     * @param id 검색할 Item 의 id
     * @return 검색 조건에 맞는 Item 객체
     */
    public Optional<Item> searchById(String id) {
        return itemRepository.findById(id.strip());
    }

    /**
     * 주어진 강의실로 Item 을 검색합니다.
     *
     * @param roomNumber   검색할 Item 이 위치한 강의실 번호
     * @param buildingName 검색할 Item 이 위치한 건물 이름
     * @return 검색 조건에 맞는 Item 객체들의 리스트
     */
    public List<Item> searchByLectureRoom(String roomNumber, String buildingName) {
        Map<String, Object> criteria = Map.of(
                "roomNumber", roomNumber,
                "buildingName", buildingName
        );
        return itemRepository.findByCriteria(criteria);
    }
}
