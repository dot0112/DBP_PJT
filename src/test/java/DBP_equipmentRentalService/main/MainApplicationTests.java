package DBP_equipmentRentalService.main;

import DBP_equipmentRentalService.main.service.TestExecuteProcedure;
import DBP_equipmentRentalService.main.service.TestInsertService;
import DBP_equipmentRentalService.main.service.TestSelectService;
import DBP_equipmentRentalService.main.service.TestUpdateService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MainApplication.class)
@Transactional
class MainApplicationTest {

    @Autowired
    private TestInsertService testInsertService;
    @Autowired
    private TestUpdateService testUpdateService;
    @Autowired
    private TestExecuteProcedure testExecuteProcedure;
    @Autowired
    private TestSelectService testSelectService;

    @BeforeEach
    public void before() {
        System.out.println("Test Before");
    }

    @AfterEach
    public void after() {
        System.out.println("Test After");
    }

    @Test
    @DisplayName("관리자 등록 테스트")
    void testAdminCreate() {
        testInsertService.testAdminCreate();
    }

    @Test
    @DisplayName("비품 등록 테스트")
    void testItemCreate() {
        testInsertService.testItemCreate();
    }

    @Test
    @DisplayName("강의실 등록 테스트")
    void testLectureRoomCreate() {
        testInsertService.testLectureRoomCreate();
    }

    @Test
    @DisplayName("수리 내역 등록 테스트")
    void testRepairRecordCreate() {
        testInsertService.testRepairRecordCreate();
    }

    @Test
    @DisplayName("수리 요청 등록 테스트")
    void testRepairRequestCreate() {
        testInsertService.testRepairRequestCreate();
    }

    @Test
    @DisplayName("대여 등록 테스트")
    void testRentalCreate() {
        testInsertService.testRentalCreate();
    }

    @Test
    @DisplayName("반납 등록 테스트")
    void testReturnsCreate() {
        testInsertService.testReturnsCreate();
    }

    @Test
    @DisplayName("유저 등록 테스트")
    void testUsersCreate() {
        testInsertService.testUsersCreate();
    }

    @Test
    @DisplayName("관리자 수정 테스트")
    void testAdminUpdate() {
        testUpdateService.testAdminUpdate();
    }

    @Test
    @DisplayName("비품 수정 테스트")
    void testItemUpdate() {
        testUpdateService.testItemUpdate();
    }

    @Test
    @DisplayName("강의실 수정 테스트")
    void testLectureRoomUpdate() {
        testUpdateService.testLectureRoomUpdate();
    }

    @Test
    @DisplayName("대여 수정 테스트")
    void testRentalUpdate() {
        testUpdateService.testRentalUpdate();
    }

    @Test
    @DisplayName("수리 내역 수정 테스트")
    void testRepairRecordUpdate() {
        testUpdateService.testRepairRecordUpdate();
    }

    @Test
    @DisplayName("수리 요청 수정 테스트")
    void testRepairRequestUpdate() {
        testUpdateService.testRepairRequestUpdate();
    }

    @Test
    @DisplayName("반납 수정 테스트")
    void testReturnsUpdate() {
        testUpdateService.testReturnsUpdate();
    }

    @Test
    @DisplayName("사용자 수정 테스트")
    void testUsersUpdate() {
        testUpdateService.testUsersUpdate();
    }

    @Test
    @DisplayName("EquipmentHistory 프로시저 호출")
    void testEquipmentHistory() {
        testExecuteProcedure.testEquipmentHistory();
    }

    @Test
    @DisplayName("ManageItems 프로시저 호출 - 배치 오류")
    void testManageItemsAssignError() {
        testExecuteProcedure.testManageItemsAssignError();
    }

    @Test
    @DisplayName("ManageItems 프로시저 호출")
    void testManageItems() {
        testExecuteProcedure.testManageItems();
    }

    @Test
    @DisplayName("ManageItems 프로시저 호출 - 등록 오류")
    void testManageItemsInsertError() {
        testExecuteProcedure.testManageItemsInsertError();
    }

    @Test
    @DisplayName("ManageItems 프로시저 호출 - 등록")
    void testManageItemsInsert() {
        testExecuteProcedure.testManageItemsInsert();
    }

    @Test
    @DisplayName("ManageItems 프로시저 호출 - 배치")
    void testManageItemsAssign() {
        testExecuteProcedure.testManageItemsAssign();
    }

    @Test
    @DisplayName("SetBorrowLimit 프로시저 호출")
    void testSetBorrowLimit() {
        testExecuteProcedure.testSetBorrowLimit();
    }

    @Test
    @DisplayName("Admin 검색")
    void testAdminSelect() {
        testSelectService.testAdminSelect();
    }

    @Test
    @DisplayName("Item 검색")
    void testItemSelect() {
        testSelectService.testItemSelect();
    }

    @Test
    @DisplayName("LectureRoom 검색")
    void testLectureRoomSelect() {
        testSelectService.testLectureRoomSelect();
    }

    @Test
    @DisplayName("Rental 검색")
    void testRentalSelect() {
        testSelectService.testRentalSelect();
    }

    @Test
    @DisplayName("RepairRecord 검색")
    void testRepairRecordSelect() {
        testSelectService.testRepairRecordSelect();
    }

    @Test
    @DisplayName("RepairRequest 검색")
    void testRepairRequestSelect() {
        testSelectService.testRepairRequestSelect();
    }

    @Test
    @DisplayName("Returns 검색")
    void testReturnsSelect() {
        testSelectService.testReturnsSelect();
    }

    @Test
    @DisplayName("Users 검색")
    void testUsersSelect() {
        testSelectService.testUsersSelect();
    }
}