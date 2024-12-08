package DBP_equipmentRentalService.main.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class RepairRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    String repairRequestId;
    String itemId;
    String userId;
    String itemName;
    @Column(nullable = false, insertable = false)
    LocalDate requestDate;
    @Column(insertable = false)
    Integer isRepaired;

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

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public Integer getIsRepaired() {
        return (isRepaired != null) ? isRepaired : 0;
    }

    public void setIsRepaired(Integer isRepaired) {
        this.isRepaired = isRepaired;
    }
}
