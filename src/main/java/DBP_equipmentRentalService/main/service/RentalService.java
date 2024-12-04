package DBP_equipmentRentalService.main.service;

import DBP_equipmentRentalService.main.domain.Rental;
import DBP_equipmentRentalService.main.repository.rental.RentalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RentalService {
    private RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository){
        this.rentalRepository = rentalRepository;
    }

    public void join(Rental rental){ rentalRepository.save(rental);}
}
