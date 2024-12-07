package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.repository.procedure.ProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EquipmentHistoryService {
    private ProcedureRepository procedureRepository;
    @Autowired
    public EquipmentHistoryService(ProcedureRepository procedureRepository){
        this.procedureRepository = procedureRepository;
    }

    public List<Map<String, Object>> getEquipmentHistory(String itemId){
        return procedureRepository.equipmentHistory(itemId);
    }
}
