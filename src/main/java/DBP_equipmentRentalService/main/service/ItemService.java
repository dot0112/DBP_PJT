package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.domain.Item;
import DBP_equipmentRentalService.main.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public List<Item> findItems(){
        List<Item> items = itemRepository.findAll();

        Collections.shuffle(items);

        return items.subList(0, Math.min(10, items.size()));
    }
}
