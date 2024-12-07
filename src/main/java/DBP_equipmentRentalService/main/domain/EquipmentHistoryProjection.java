package DBP_equipmentRentalService.main.domain;

import jakarta.persistence.*;

@Entity
@NamedStoredProcedureQuery(
        name = "equipmentHistory",
        procedureName = "EQUIPEMENT_HISTORY",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_itemid"),
                @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, type = Class.class, name = "p_result")
        }
)
public class EquipmentHistoryProjection {
    @Id
    Object event;

    public EquipmentHistoryProjection() {
    }
}
