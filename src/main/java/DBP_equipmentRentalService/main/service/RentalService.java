package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.domain.Rental;
import DBP_equipmentRentalService.main.repository.rental.RentalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class RentalService {
    private RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public void join(Rental rental) {
        rentalRepository.save(rental);
    }

    /**
     * userId 로 해당 유저가 대여한 기록들을 확인합니다.
     *
     * @param userId 검색할 유저의 Id
     * @return 해당 유저의 대여 객체로 된 리스트
     */
    public List<Rental> findByUserId(String userId) {
        return rentalRepository.findByCriteria(Map.of("userId", userId));
    }

    /**
     * rentalId 로 특정 대여 기록을 검색합니다.
     *
     * @param rentalId 검색할 대여 번호
     * @return 조건에 맞는 대여 객체 (Optional 객체로 null 이 반환될 수 있음)
     */
    public Optional<Rental> findByRentalId(String rentalId) {
        return rentalRepository.findById(rentalId);
    }
}
