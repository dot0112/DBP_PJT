package DBP_equipmentRentalService.main.repository.users;

import DBP_equipmentRentalService.main.domain.Users;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UsersRepository {
    /**
     * 전달된 Users 객체를 Database 내에 Insert 합니다.
     *
     * @param users Insert 할 Users 객체
     * @return Parameter 로 전달된 Users 객체
     */
    Users save(Users users);

    /**
     * 주어진 Id 로 Users 을 검색합니다.
     *
     * @param id 검색할 Users 의 id
     * @return 검색 조건에 맞는 Users 객체
     */
    Optional<Users> findById(String id);

    /**
     * 주어진 조건으로 Users 을 검색합니다.
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
     * @return 검색 조건에 맞는 Users 객체들의 리스트
     */
    List<Users> findByCriteria(Map<String, Object> criteria);

    /**
     * Database 의 Users 테이블의 모든 Tuple 을 읽어옵니다.
     *
     * @return 생성된 Users 객체들의 리스트
     */
    List<Users> findAll();
}
