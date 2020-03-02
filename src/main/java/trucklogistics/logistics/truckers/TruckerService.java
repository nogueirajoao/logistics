package trucklogistics.logistics.truckers;

import org.springframework.stereotype.Service;
import trucklogistics.logistics.shippings.Coordinates;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TruckerService {

    private final TruckerRepository repository;

    private final List<Trucker> getList() { return repository.findAll();}

    public List<Trucker> getAllTruckers() { return getList(); }

    public TruckerService(TruckerRepository repository) {
        this.repository = repository;
    }

    public Trucker saveTrucker(Trucker trucker) {
        return repository.saveAndFlush(trucker);
    }

    public void deleteTrucker(Trucker trucker) {
        repository.delete(trucker);
    }

    public List<Trucker> getAllTruckersDischarged() {

        List<Trucker> list = repository.getAllByIsLoadedFalse();

        list.stream().forEach((trucker) ->
                trucker.getShipping().setDestinyToOrigin());

        return repository.saveAll(list);
    }

    public List<Trucker> getAllTruckersOwnsVehicle() {
        return repository.getAllByOwnVehicleIsTrue();
    }

    public Trucker updateTrucker(Trucker trucker) {
        return repository.save(trucker);
    }

    public List<Trucker> getLoadedTrucksByDate(String string) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(string, dateTimeFormatter);

        List<Trucker> list = repository.getLoadedTruckersByDate(date);

        return list;

    }

    public Map<TruckType, List<Coordinates>> listOfOriginAndDestinyGroupedByTruckType() {

        List<Trucker> list = getList();

        Map<TruckType, List<Coordinates>> shippingList = list.stream()
                .collect(Collectors.groupingBy(Trucker::getTruckType, Collectors.mapping(
                        (trucker) -> trucker.getShipping().getCoordinates(), Collectors.toList())));

        return shippingList;
    }

    public ReportResponse getReport() {

        List<Trucker> truckers = getList();

        Map<Integer, Long> yearReport = truckers.stream()
                .filter(trucker -> trucker.getIsLoaded()).collect(Collectors.groupingBy(
                trucker -> trucker.getShipping().getShippingDate().getYear(), Collectors.counting()));

        Map<String, Long> monthReport = truckers.stream()
                .filter(trucker -> trucker.getIsLoaded()).collect(Collectors.groupingBy(
                        trucker -> trucker.getShipping().getShippingDate().toString().substring(0, 7),
                        Collectors.counting()));

        Map<LocalDate, Long> dayReport = truckers.stream()
                .filter(trucker -> trucker.getIsLoaded()).collect(Collectors.groupingBy(
                        trucker -> trucker.getShipping().getShippingDate(), Collectors.counting()));

//        dayReport.forEach((k, v) -> {
//            list.add("No dia " + k + " passaram " + v + " caminhões carregados.");
//        });

//        monthReport.forEach((k, v) -> {
//            list.add("No mês " + k + " passaram " + v + " caminhões carregados.");
//        });

//        yearReport.forEach((k, v) -> {
//            list.add("No ano " + k + " passaram " + v + " caminhões carregados.");
//        });

        ReportResponse response = new ReportResponse(dayReport, monthReport, yearReport);

        return response;
    }

}
