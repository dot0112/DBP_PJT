package DBP_equipmentRentalService.main.domain;

import jakarta.persistence.*;

@Entity
public class RepairRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "repairRequestId", updatable = false, nullable = false)
    String repairRequestId;
    String itemId;
    String userId;
    String itemName;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRepairRequestId() {
        return repairRequestId;
    }

    public void setRepairRequestId(String repairRequestId) {
        this.repairRequestId = repairRequestId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
