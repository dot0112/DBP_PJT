package DBP_equipmentRentalService.main.repository.procedure;

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
}
