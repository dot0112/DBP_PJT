package DBP_equipmentRentalService.main.domain;

import jakarta.persistence.*;

@Entity
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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    String itemId;
    String itemName;
    String itemType;
    String roomNumber;
    String buildingName;
    @Column(insertable = false)
    String currentState;
    String adminId;
    String rentableStatus;
    String rentalStatus;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getRentableStatus() {
        return rentableStatus;
    }

    public void setRentableStatus(String rentableStatus) {
        this.rentableStatus = rentableStatus;
    }

    public String getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(String rentalStatus) {
        this.rentalStatus = rentalStatus;
    }
}
