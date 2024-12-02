package DBP_equipmentRentalService.main.repository.repairRecord;

import DBP_equipmentRentalService.main.domain.RepairRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RepairRecordRepository {
    /**
     * 전달된 RepairRecord 객체를 Database 내에 Insert 합니다.
     *
     * @param repairRecord Insert 할 RepairRecord 객체
     * @return Parameter 로 전달된 RepairRecord 객체
     */
    RepairRecord save(RepairRecord repairRecord);

    /**
     * 주어진 Id(비품 번호, 수리 날짜)로 RepairRecord 를 검색합니다.
     *
     * @param itemId     검색할 비품 번호
     * @param repairDate 검색할 수리 날짜
     * @return 검색 조건에 맞는 RepairRecord 객체
     */
    Optional<RepairRecord> findById(String itemId, LocalDate repairDate);

    /**
     * 주어진 조건으로 RepairRecord 를 검색합니다.
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
     * @return 검색 조건에 맞는 RepairRecord 객체들의 리스트
     */
    List<RepairRecord> findByCriteria(Map<String, Object> criteria);

    /**
     * Database 의  RepairRecord 테이블의 모든 Tuple 을 읽어옵니다.
     *
     * @return 생성된 RepairRecord 객체들의 리스트
     */
    List<RepairRecord> findAll();
}
