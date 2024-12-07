package DBP_equipmentRentalService.main.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class RepairRecordId implements Serializable {
    private String itemId;
    private LocalDate repairDate;

    public RepairRecordId() {
    }

    public RepairRecordId(String itemId, LocalDate repairDate) {
        this.itemId = itemId;
        this.repairDate = repairDate;
    }

    public LocalDate getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(LocalDate repairDate) {
        this.repairDate = repairDate;
    }

    public void setRepairDate(Timestamp repairDate) {
        this.repairDate = repairDate.toLocalDateTime().toLocalDate();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepairRecordId that = (RepairRecordId) o;
        return Objects.equals(itemId, that.itemId) &&
                Objects.equals(repairDate, that.repairDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, repairDate);
    }
}
