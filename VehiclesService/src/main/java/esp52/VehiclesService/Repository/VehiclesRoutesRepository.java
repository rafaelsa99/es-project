package esp52.VehiclesService.Repository;

import org.springframework.stereotype.Repository;
import esp52.VehiclesService.models.VehiclesRoute;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface VehiclesRoutesRepository extends JpaRepository<VehiclesRoute, Integer>{

}
