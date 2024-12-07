package DBP_equipmentRentalService.main.repository.procedure;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NamedStoredProcedureQuery(
        name = "setBorrowLimit",
        procedureName = "SET_BORROW_LIMIT",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_userid")
        }
)

@NamedStoredProcedureQuery(
        name = "equipmentHistory",
        procedureName = "EQUIPEMENT_HISTORY",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_itemid"),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = void.class, name = "p_result")
        }
)

@NamedStoredProcedureQuery(
        name = "manageItems",
        procedureName = "MANAGE_ITEMS",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_itemName"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_itemType"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_adminId"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name = "p_quantity"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_roomNumber"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_buildingName"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_rentableStatus"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_rentalStatus"),
        }
)

@Repository
public class JpaProcedureRepository implements ProcedureRepository {
    private final EntityManager em;

    @Autowired
    public JpaProcedureRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void setBorrowLimit(String userId) {
        try {
            em.createNamedStoredProcedureQuery("setBorrowLimit")
                    .setParameter("p_userid", userId)
                    .execute();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Map<String, Object>> equipmentHistory(String itemId) {
        ResultSet rs = null;
        try {
            StoredProcedureQuery query = em.createNamedStoredProcedureQuery("equipmentHistory");
            query.setParameter("p_itemid", itemId);

            query.execute();
            rs = (ResultSet) query.getOutputParameterValue("p_result");
            List<Map<String, Object>> procedureResult = new ArrayList<>();

            while (rs.next()) {
                LocalDate eventDate = rs.getDate("EVENT_DATE").toLocalDate();
                String eventType = rs.getString("EVENT_TYPE"),
                        details = rs.getString("DETAILS");
                Map<String, Object> event = new HashMap<>();
                event.put("eventDate", eventDate);
                event.put("eventType", eventType);
                event.put("details", details);
                procedureResult.add(event);
            }
            return procedureResult;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }

    @Override
    public void manageItems(String adminId, Integer quantity, String itemName, @Nullable String itemType, @Nullable String roomNumber, @Nullable String buildingName, @Nullable String rentableStatus, @Nullable String rentalStatus) {

        if (adminId == null || quantity == null || itemName == null) {
            throw new IllegalArgumentException("adminId, quantity, and itemName must not be null");
        }

        try {
            em.createNamedStoredProcedureQuery("manageItems").setParameter("p_itemName", itemName).setParameter("p_itemType", itemType).setParameter("p_adminId", adminId).setParameter("p_quantity", quantity).setParameter("p_roomNumber", roomNumber).setParameter("p_buildingName", buildingName).setParameter("p_rentableStatus", rentableStatus).setParameter("p_rentalStatus", rentalStatus).execute();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
