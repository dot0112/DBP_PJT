package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.domain.*;
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

@Service
public class TestInsertService {
    private final AdminRepository adminRepository;
    private final ItemRepository itemRepository;
    private final LectureRoomRepository lectureRoomRepository;
    private final RentalRepository rentalRepository;
    private final RepairRecordRepository repairRecordRepository;
    private final RepairRequestRepository repairRequestRepository;
    private final ReturnsRepository returnsRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public TestInsertService(AdminRepository adminRepository, ItemRepository itemRepository, LectureRoomRepository lectureRoomRepository, RentalRepository rentalRepository, RepairRecordRepository repairRecordRepository, RepairRequestRepository repairRequestRepository, ReturnsRepository returnsRepository, UsersRepository usersRepository) {
        this.adminRepository = adminRepository;
        this.itemRepository = itemRepository;
        this.lectureRoomRepository = lectureRoomRepository;
        this.rentalRepository = rentalRepository;
        this.repairRecordRepository = repairRecordRepository;
        this.repairRequestRepository = repairRequestRepository;
        this.returnsRepository = returnsRepository;
        this.usersRepository = usersRepository;
    }

    public void testAdminCreate() {
        Admin admin = new Admin();
        admin.setAdminId("test");
        admin.setPassword("test");
        adminRepository.save(admin);
    }

    public void testItemCreate() {
        Item item = new Item();
        item.setItemName("test");
        item.setItemType("test");
        itemRepository.save(item);
    }

    public void testLectureRoomCreate() {
        LectureRoom lectureRoom = new LectureRoom();
        lectureRoom.setRoomNumber("test");
        lectureRoom.setBuildingName("test");
        lectureRoomRepository.save(lectureRoom);
    }

    public void testRentalCreate() {
        Rental rental = new Rental();
        rental.setUserId("user001");
        rental.setItemId("drXN7aD4");
        rental.setRentalDate(LocalDate.now());
        rentalRepository.save(rental);
    }

    public void testRepairRecordCreate() {
        RepairRecord repairRecord = new RepairRecord();
        repairRecord.setItemId("qDXeQDYj");
        repairRecord.setRepairDate(LocalDate.now());
        repairRecordRepository.save(repairRecord);
    }

    public void testRepairRequestCreate() {
        RepairRequest repairRequest = new RepairRequest();
        repairRequest.setItemId("EjbMNw6u");
        repairRequest.setUserId("user001");
        repairRequest.setItemName("파미가구 아이언");
        repairRequestRepository.save(repairRequest);
    }

    public void testReturnsCreate() {
        Returns returns = new Returns();
        returns.setRentalId("RENT00080");
        returns.setUserId("user001");
        returns.setItemId("gkYAbeIW");
        returnsRepository.save(returns);
    }

    public void testUsersCreate() {
        Users users = new Users();
        users.setUserId("test");
        users.setPassword("test");
        usersRepository.save(users);
    }

}
