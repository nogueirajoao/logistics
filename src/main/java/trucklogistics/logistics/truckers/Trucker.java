package trucklogistics.logistics.truckers;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import trucklogistics.logistics.shippings.Shipping;

import javax.persistence.*;

@Data
@Entity
@Table(name = "trucker")
public class Trucker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String gender;
    private Boolean ownVehicle;
    private Character cnhType;
    private Boolean isLoaded;

    @Enumerated
    private TruckType truckType;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Shipping shipping;

}
