package DBP_equipmentRentalService.main.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LectureRoomId implements Serializable {
    private String roomNumber;
    private String buildingName;

    public LectureRoomId() {
    }

    public LectureRoomId(String roomNumber, String buildingName) {
        this.roomNumber = roomNumber;
        this.buildingName = buildingName;
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

    // equals()와 hashCode() 구현 (필수)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LectureRoomId that = (LectureRoomId) o;
        return Objects.equals(roomNumber, that.roomNumber) &&
                Objects.equals(buildingName, that.buildingName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, buildingName);
    }
}
