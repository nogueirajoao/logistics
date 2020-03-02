# Logistics

##### Está API está utilizando Spring boot, Spring Data e H2 DB.
Para rodar a aplicação via terminal: *mvn spring-boot:run*

Com o swagger temos a lista de end-points: *localhost:8080/swagger-ui.html*

Exemplo de body para salvar um novo caminhoneiro:
```
{
    "name" : "John",
    "gender" : "Masc",
    "ownVehicle": true,
    "cnhType": "E",
    "isLoaded": true,
    "truckType": 4,
    "shipping": {
        "coordinates" : {
            "originLatitude": -23.57405697,
            "originLongitude": -33.66442871,
            "destinyLatitude" : -55.57405697,
            "destinyLongitude" : -66.66442871
            },
            "shippingDate": "2020-03-21"
        }
}
```
