package DBP_equipmentRentalService.main.repository.procedure;

import java.util.List;
import java.util.Map;

public interface ProcedureRepository {
    void setBorrowLimit(String userId);

    List<Map<String, Object>> equipmentHistory(String itemId);
}
