package DBP_equipmentRentalService.main.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@NamedStoredProcedureQuery(
        name = "setBorrowLimit",
        procedureName = "SET_BORROW_LIMIT",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "p_userid")
        }
)
public class Returns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    String returnId;
    String userId;
    String itemId;
    String rentalId;
    LocalDate actualReturnDate;

    @Column(name = "REPAIRREQUEST")
    String repairRequest;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getRentalId() {
        return rentalId;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public String getReturnId() {
        return returnId;
    }

    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public String getRepairRequest() {
        return repairRequest;
    }

    public void setRepairRequest(String repairRequest) {
        this.repairRequest = repairRequest;
    }
}
