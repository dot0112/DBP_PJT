package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.repository.procedure.ProcedureRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageItemService {
    private ProcedureRepository procedureRepository;

    @Autowired
    public ManageItemService(ProcedureRepository procedureRepository){
        this.procedureRepository = procedureRepository;
    }

    public void manageItems(String adminId, Integer quantity, String itemName, @Nullable String itemType, @Nullable String roomNumber, @Nullable String buildingName){
        if(itemType == null){
            procedureRepository.manageItems(adminId, quantity, itemName, null, roomNumber, buildingName, null, null);
        }
        else if(buildingName == null && roomNumber == null){
            procedureRepository.manageItems(adminId, quantity, itemName, itemType, null, null, null, null);
        }
        else{
            procedureRepository.manageItems(adminId, quantity, itemName, itemType, roomNumber, buildingName, null, null);
        }
    }
}
