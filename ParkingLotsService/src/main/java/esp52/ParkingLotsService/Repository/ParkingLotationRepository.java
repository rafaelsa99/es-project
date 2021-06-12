package esp52.ParkingLotsService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import esp52.ParkingLotsService.models.ParkingLotationFree;

@Repository
public interface ParkingLotationRepository extends JpaRepository<ParkingLotationFree, Integer>{

}
