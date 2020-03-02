package trucklogistics.logistics.truckers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import trucklogistics.logistics.shippings.Coordinates;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/truckers")
public class TruckerController {

    private final TruckerService service;

    public TruckerController(TruckerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Trucker> getAllTruckers() { return service.getAllTruckers(); }

    @GetMapping("/ownsvehicle")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Trucker> getAllTruckersOwnsVehicle() {
        return service.getAllTruckersOwnsVehicle();
    }

    @GetMapping("/discharged/setdestinytorigin")
    public List<Trucker> getAllDischargedTruckers() {
        return service.getAllTruckersDischarged();
    }

    @PostMapping
    public Trucker saveTrucker(@RequestBody Trucker trucker) {
        return service.saveTrucker(trucker);
    }

    @PutMapping
    public Trucker updateTrucker(@RequestBody Trucker trucker) {
        return service.updateTrucker(trucker);
    }

    @DeleteMapping
    public void deleteTrucker(@RequestBody Trucker trucker) {
        service.deleteTrucker(trucker);
    }

    @GetMapping("/listcoordinatesgroupedbytrucktype")
    public Map<TruckType, List<Coordinates>> getList(){
        return service.listOfOriginAndDestinyGroupedByTruckType();
    }

    @GetMapping("/loadeadtrucksbydate")
    public List<Trucker> getLoadedTruckersByDate(@RequestParam String date) {
        return service.getLoadedTrucksByDate(date);
    }

    @GetMapping("/report")
    public ReportResponse getReport() {
        return service.getReport();
    }

}
