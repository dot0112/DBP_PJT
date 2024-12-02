package DBP_equipmentRentalService.main.repository.repairRequest;

import DBP_equipmentRentalService.main.domain.RepairRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RepairRequestRepository {
    /**
     * 전달된 RepairRequest 객체를 Database 내에 Insert 합니다.
     *
     * @param repairRequest Insert 할 RepairRequest 객체
     * @return Parameter 로 전달된 RepairRequest 객체
     */
    RepairRequest save(RepairRequest repairRequest);

    /**
     * 주어진 Id 로 RepairRequest 을 검색합니다.
     *
     * @param id 검색할 RepairRequest 의 id
     * @return 검색 조건에 맞는 RepairRequest 객체
     */
    Optional<RepairRequest> findById(String id);

    /**
     * 주어진 조건으로 RepairRequest 를 검색합니다.
     * <p>
     * Parameter 예시:
     * <pre>
     * criteria = Map.of(
     *     "Type_String", "String",         // 정확한 문자열 매치
     *     "Type_String_Wild", "S_trin%",   // 와일드카드 문자열 검색 ('%'는 0개 이상의 문자, '_'는 단일 문자)
     *     "Type_Int", 10,                  // 정수 값 매치
     *     "Type_LocalDate", LocalDate.parse("2024-12-02")  // 날짜 매치
     * );
     * </pre>
     *
     * @param criteria 검색할 조건
     * @return 검색 조건에 맞는 Admin 객체들의 리스트
     */
    List<RepairRequest> findByCriteria(Map<String, Object> criteria);

    /**
     * Database 의 RepairRequest 테이블의 모든 Tuple 을 읽어옵니다.
     *
     * @return 생성된 RepairRequest 객체들의 리스트
     */
    List<RepairRequest> findAll();
}
