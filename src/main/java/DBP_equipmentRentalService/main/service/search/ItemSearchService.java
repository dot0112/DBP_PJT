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

    /*
     * 구현할 기능
     * 물품 이름으로 검색
     * 물품 카테고리로 검색
     * 물품 id로 검색
     * 물품 강의실로 검색
     * */

    public List<Item> searchByName(String name) {
        Map<String, Object> criteria = Map.of(
                "itemName", name
        );
        return itemRepository.findByCriteria(criteria);
    }

    public List<Item> searchByType(String type) {
        Map<String, Object> criteria = Map.of(
                "itemType", type
        );
        return itemRepository.findByCriteria(criteria);
    }

    public Optional<Item> searchById(String id) {
        return itemRepository.findById(id);
    }

    public List<Item> searchByLectureRoom(String roomNumber, String buildingName) {
        Map<String, Object> criteria = Map.of(
                "roomNumber", roomNumber,
                "buildingName", buildingName
        );
        return itemRepository.findByCriteria(criteria);
    }
}
