package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.repository.procedure.ProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestExecuteProcedure {
    private final ProcedureRepository procedureRepository;

    @Autowired
    public TestExecuteProcedure(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    public void testSetBorrowLimit() {
        procedureRepository.setBorrowLimit("user001");
    }

    public void testEquipmentHistory() {
        List<Map<String, Object>> result = procedureRepository.equipmentHistory("gkYAbeIW");
    }

    public void testManageItems() {
//        procedureRepository.manageItems();
    }
}
