package trucklogistics.logistics.truckers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TruckerRepository extends JpaRepository<Trucker, Integer> {

    List<Trucker> getAllByIsLoadedFalse();

    List<Trucker> getAllByOwnVehicleIsTrue();

    @Query(value = "SELECT t FROM Trucker t WHERE t.shipping.shippingDate = ?1 AND t.isLoaded = true")
    List<Trucker> getLoadedTruckersByDate(LocalDate date);

}
