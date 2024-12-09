package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.domain.LectureRoomId;
import DBP_equipmentRentalService.main.domain.RepairRecordId;
import DBP_equipmentRentalService.main.repository.admin.AdminRepository;
import DBP_equipmentRentalService.main.repository.item.ItemRepository;
import DBP_equipmentRentalService.main.repository.lectureRoom.LectureRoomRepository;
import DBP_equipmentRentalService.main.repository.rental.RentalRepository;
import DBP_equipmentRentalService.main.repository.repairRecord.RepairRecordRepository;
import DBP_equipmentRentalService.main.repository.repairRequest.RepairRequestRepository;
import DBP_equipmentRentalService.main.repository.returns.ReturnsRepository;
import DBP_equipmentRentalService.main.repository.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class TestSelectService {
    private final AdminRepository adminRepository;
    private final ItemRepository itemRepository;
    private final LectureRoomRepository lectureRoomRepository;
    private final RentalRepository rentalRepository;
    private final RepairRecordRepository repairRecordRepository;
    private final RepairRequestRepository repairRequestRepository;
    private final ReturnsRepository returnsRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public TestSelectService(AdminRepository adminRepository, ItemRepository itemRepository, LectureRoomRepository lectureRoomRepository, RentalRepository rentalRepository, RepairRecordRepository repairRecordRepository, RepairRequestRepository repairRequestRepository, ReturnsRepository returnsRepository, UsersRepository usersRepository) {
        this.adminRepository = adminRepository;
        this.itemRepository = itemRepository;
        this.lectureRoomRepository = lectureRoomRepository;
        this.rentalRepository = rentalRepository;
        this.repairRecordRepository = repairRecordRepository;
        this.repairRequestRepository = repairRequestRepository;
        this.returnsRepository = returnsRepository;
        this.usersRepository = usersRepository;
    }

    public void testAdminSelect() {
        adminRepository.findAll();
        adminRepository.findById("manager001");
        adminRepository.findByCriteria(Map.of("adminId", "manager001",
                "password", "1234",
                "name", "김철수",
                "dateOfBirth", "80/01/01",
                "email", "kim001@naver.com",
                "phoneNumber", "010-1234-5678"));
    }

    public void testItemSelect() {
        itemRepository.findAll();
        itemRepository.findById("qDXeQDYj");
        itemRepository.findByCriteria(Map.of("itemId", "qDXeQDYj",
                "itemName", "실험용 기기",
                "itemType", "전자기기",
                "roomNumber", "810",
                "buildingName", "정보관",
                "currentState", "나쁨",
                "adminId", "manager003",
                "rentableStatus", "대여불가",
                "rentalStatus", "수리 중"
        ));
    }

    public void testLectureRoomSelect() {
        lectureRoomRepository.findAll();
        LectureRoomId lectureRoomId = new LectureRoomId("603", "생활과학관");
        lectureRoomRepository.findById(lectureRoomId);
        lectureRoomRepository.findByCriteria(Map.of("roomNumber", "603",
                "buildingName", "생활과학관",
                "departmentName", "아동학과",
                "adminId", "manager006"));
    }

    public void testRentalSelect() {
        rentalRepository.findAll();
        rentalRepository.findById("RENT00080");
        rentalRepository.findByCriteria(Map.of("userId", "user001",
                "itemId", "gkYAbeIW",
                "rentalId", "RENT00080",
                "rentalDate", LocalDate.of(2024, 12, 1),
                "returnDate", LocalDate.of(2024, 12, 8)));
    }

    public void testRepairRecordSelect() {
        repairRecordRepository.findAll();
        RepairRecordId repairRecordId = new RepairRecordId("qDxeQDYj", LocalDate.of(2024, 12, 4));
        repairRecordRepository.findById(repairRecordId);
        repairRecordRepository.findByCriteria(Map.of("itemId", "qDXeQDYj",
                "repairDesc", "램프 교체",
                "repairCost", 50000,
                "repairDate", LocalDate.of(2024, 12, 4)));
    }

    public void testRepairRequestSelect() {
        repairRequestRepository.findAll();
        repairRequestRepository.findById("REQ00024");
        repairRequestRepository.findByCriteria(Map.of("itemId", "Jed2ftKI",
                "userId", "user031",
                "repairRequestId", "REQ00024",
                "itemName", "삼성전자 AC060KA4PBH5SY",
                "requestDate", LocalDate.of(2024, 12, 7),
                "isRepaired", 1));

    }

    public void testReturnsSelect() {
        returnsRepository.findAll();
        returnsRepository.findById("RTN00109");
        returnsRepository.findByCriteria(Map.of("userId", "user001",
                "itemId", "gkYAbeIW",
                "rentalId", "RENT00080",
                "returnId", "RTN00109",
                "actualReturnDate", LocalDate.of(2024, 12, 6),
                "repairRequest", "N"));
    }

    public void testUsersSelect() {
        usersRepository.findAll();
        usersRepository.findById("user031");
        usersRepository.findByCriteria(Map.of("userId", "user031",
                "password", "1234",
                "name", "김지우",
                "dateOfBirth", LocalDate.of(2020, 7, 1),
                "email", "kim031@naver.com",
                "phoneNumber", "010-1234-5678",
                "rentalAvailability", 0,
                "rentalAvailabilityDate", LocalDate.of(2024, 12, 13)));
    }


}
