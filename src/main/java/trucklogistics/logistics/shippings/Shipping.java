package trucklogistics.logistics.shippings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "shipping")
public class Shipping {

    public Shipping() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Embedded
    private Coordinates coordinates;

    private LocalDate shippingDate;


    public void setDestinyToOrigin() {
        this.coordinates.setDestinyLatitude(this.coordinates.getOriginLatitude());
        this.coordinates.setDestinyLongitude(this.coordinates.getOriginLongitude());
    }

}
