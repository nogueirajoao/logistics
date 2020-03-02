package trucklogistics.logistics.shippings;

import lombok.Data;

@Data
public class Coordinates {

    public Coordinates() {
    }

    public Coordinates(Double originLatitude, Double originLongitude, Double destinyLatitude, Double destinyLongitude) {
        this.originLatitude = originLatitude;
        this.originLongitude = originLongitude;
        this.destinyLatitude = destinyLatitude;
        this.destinyLongitude = destinyLongitude;
    }

    private Double originLatitude;
    private Double originLongitude;
    private Double destinyLatitude;
    private Double destinyLongitude;

}
