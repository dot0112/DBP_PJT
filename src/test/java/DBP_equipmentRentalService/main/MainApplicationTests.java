package DBP_equipmentRentalService.main;

import DBP_equipmentRentalService.main.service.TestService;
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
    private TestService testService;

    @BeforeEach
    public void before() {
        System.out.println("Test Before");
    }

    @AfterEach
    public void after() {
        System.out.println("Test After");
    }

    @Test
    @DisplayName("비품 등록 테스트")
    void testItemCreate() {
        testService.testItemCreate();
    }

    @Test
    @DisplayName("대여 등록 테스트")
    void testRentalCreate() {
        testService.testRentalCreate();
    }

    @Test
    @DisplayName("반납 등록 테스트")
    void testReturnsCreate() {
        testService.testReturnsCreate();
    }

    @Test
    @DisplayName("수리 요청 등록 테스트")
    void testRepairRequestCreate() {
        testService.testRepairRequestCreate();
    }
}