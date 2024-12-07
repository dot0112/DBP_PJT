package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.domain.LectureRoom;
import DBP_equipmentRentalService.main.repository.lectureRoom.LectureRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureRoomService {
    private LectureRoomRepository lectureRoomRepository;

    @Autowired
    public LectureRoomService(LectureRoomRepository lectureRoomRepository){
        this.lectureRoomRepository = lectureRoomRepository;
    }

    public List<LectureRoom> findAll(){
        return lectureRoomRepository.findAll();
    }
}
