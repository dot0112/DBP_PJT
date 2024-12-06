package DBP_equipmentRentalService.main.repository.procedure;

import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Map;

public interface ProcedureRepository {
    /**
     * 주어진 UserId 로 해당 사용자의 대여 가능 여부 및 연체를 설정합니다.
     *
     * @param userId 대여 가능 여부를 설정할 사용자의 UserId
     */
    void setBorrowLimit(String userId);

    /**
     * 주어진 ItemId 로 해당 비품의 기록을 가져옵니다.
     *
     * <p>
     * Output Map 예시:
     * <pre>
     * criteria = Map.of(
     *     "Type_String", "String",         // 정확한 문자열 매치
     *     "Type_String_Wild", "S_trin%",   // 와일드카드 문자열 검색 ('%'는 0개 이상의 문자, '_'는 단일 문자)
     *     "Type_Int", 10,                  // 정수 값 매치
     *     "Type_LocalDate", LocalDate.parse("2024-12-02")  // 날짜 매치
     * );
     * </pre>
     *
     * @param itemId 기록을 검색할 비품의 Id
     * @return 검색된 비품의 기록
     */
    List<Map<String, Object>> equipmentHistory(String itemId);

    /**
     * 주어진 관리자 Id, 횟수, 비품 이름으로 등록, 배치를 반복합니다.
     * <p>
     * 필수 Parameter
     * 공통 필수 Parameter: adminId, quantity, itemName
     * 등록 필수 Parameter: itemType
     * 배치 필수 Parameter: roomNumber, buildingName
     * <p>
     * 선택 Parameter
     * 등록 선택 Parameter: rentableStatus, rentalStatus
     *
     * @param adminId        관리자 Id
     * @param quantity       반복할 횟수
     * @param itemName       비품 이름
     * @param itemType       비품 타입: 등록
     * @param roomNumber     강의실 번호: 배치
     * @param buildingName   건물 번호: 배치
     * @param rentableStatus 대여 가능 여부: 등록
     * @param rentalStatus   대여 상태: 등록
     */
    void manageItems(String adminId, Integer quantity, String itemName, @Nullable String itemType, @Nullable String roomNumber, @Nullable String buildingName, @Nullable String rentableStatus, @Nullable String rentalStatus);
}
