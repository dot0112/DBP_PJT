package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.repository.admin.AdminRepository;
import DBP_equipmentRentalService.main.repository.item.ItemRepository;
import DBP_equipmentRentalService.main.repository.lectureRoom.LectureRoomRepository;
import DBP_equipmentRentalService.main.repository.rental.RentalRepository;
import DBP_equipmentRentalService.main.repository.repairRecord.RepairRecordRepository;
import DBP_equipmentRentalService.main.repository.repairRequest.RepairRequestRepository;
import DBP_equipmentRentalService.main.repository.returns.ReturnsRepository;
import DBP_equipmentRentalService.main.repository.users.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestUpdateService {
    private final AdminRepository adminRepository;
    private final ItemRepository itemRepository;
    private final LectureRoomRepository lectureRoomRepository;
    private final RentalRepository rentalRepository;
    private final RepairRecordRepository repairRecordRepository;
    private final RepairRequestRepository repairRequestRepository;
    private final ReturnsRepository returnsRepository;
    private final UsersRepository usersRepository;

    public TestUpdateService(AdminRepository adminRepository, ItemRepository itemRepository, LectureRoomRepository lectureRoomRepository, RentalRepository rentalRepository, RepairRecordRepository repairRecordRepository, RepairRequestRepository repairRequestRepository, ReturnsRepository returnsRepository, UsersRepository usersRepository) {
        this.adminRepository = adminRepository;
        this.itemRepository = itemRepository;
        this.lectureRoomRepository = lectureRoomRepository;
        this.rentalRepository = rentalRepository;
        this.repairRecordRepository = repairRecordRepository;
        this.repairRequestRepository = repairRequestRepository;
        this.returnsRepository = returnsRepository;
        this.usersRepository = usersRepository;
    }

    public void testAdminUpdate() {
        Map<String, Object> criteria = new HashMap<>();
        Map<String, Object> changeValues = new HashMap<>();
        criteria.put("adminId", "manager001");
        changeValues.put("name", "test");
        adminRepository.update(criteria, changeValues);
    }

    public void testItemUpdate() {
        Map<String, Object> criteria = new HashMap<>();
        Map<String, Object> changeValues = new HashMap<>();
        criteria.put("itemId", "eQpCJhzh");
        changeValues.put("itemName", "test");
        itemRepository.update(criteria, changeValues);
    }

    public void testLectureRoomUpdate() {
        Map<String, Object> criteria = new HashMap<>();
        Map<String, Object> changeValues = new HashMap<>();
        criteria.put("roomNumber", "603");
        changeValues.put("buildingName", "test");
        lectureRoomRepository.update(criteria, changeValues);
    }

    public void testRentalUpdate() {
        Map<String, Object> criteria = new HashMap<>();
        Map<String, Object> changeValues = new HashMap<>();
        criteria.put("userId", "user001");
        changeValues.put("itemId", "EjbMNw6u");
        rentalRepository.update(criteria, changeValues);
    }

    public void testRepairRecordUpdate() {
        Map<String, Object> criteria = new HashMap<>();
        Map<String, Object> changeValues = new HashMap<>();
        criteria.put("repairDesc", "램프 교체");
        changeValues.put("itemId", "OmPnMR03");
        repairRecordRepository.update(criteria, changeValues);
    }

    public void testRepairRequestUpdate() {
        Map<String, Object> criteria = new HashMap<>();
        Map<String, Object> changeValues = new HashMap<>();
        criteria.put("itemId", "N0zu9Fif");
        changeValues.put("userId", "user003");
        repairRequestRepository.update(criteria, changeValues);
    }

    public void testReturnsUpdate() {
        Map<String, Object> criteria = new HashMap<>();
        Map<String, Object> changeValues = new HashMap<>();
        criteria.put("userId", "user001");
        changeValues.put("itemId", "UZ9YmZTz");
        returnsRepository.update(criteria, changeValues);
    }

    public void testUsersUpdate() {
        Map<String, Object> criteria = new HashMap<>();
        Map<String, Object> changeValues = new HashMap<>();
        criteria.put("userId", "user018");
        changeValues.put("name", "test");
        usersRepository.update(criteria, changeValues);
    }
}
