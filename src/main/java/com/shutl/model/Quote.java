package com.shutl.model;

import java.util.*;

public class Quote {
    String pickupPostcode;
    String deliveryPostcode;
    String vehicle;
    Long price;

    public Quote() {}

    // Keeping allows the original tests to pass
    public Quote(String pickupPostcode, String deliveryPostcode) {
        this.pickupPostcode = pickupPostcode;
        this.deliveryPostcode = deliveryPostcode;
    }

    public Quote(String pickupPostcode, String deliveryPostcode, Long price) {
        this.pickupPostcode = pickupPostcode;
        this.deliveryPostcode = deliveryPostcode;
        this.price = price;
    }
    //

    public Quote(String pickupPostcode, String deliveryPostcode, String vehicle) {
        this.pickupPostcode = pickupPostcode;
        this.deliveryPostcode = deliveryPostcode;
		this.vehicle = vehicle;
    }

    public Quote(String pickupPostcode, String deliveryPostcode, String vehicle, Long price) {
        this.pickupPostcode = pickupPostcode;
        this.deliveryPostcode = deliveryPostcode;
        this.vehicle = vehicle;
        this.price = price;
    }

    public String getPickupPostcode() {
        return this.pickupPostcode;
    }

    public void setPickupPostcode(String pickupPostcode) {
        this.pickupPostcode = pickupPostcode;
    }

    public String getDeliveryPostcode() {
        return this.deliveryPostcode;
    }

    public void setDeliveryPostcode(String deliveryPostcode) {
        this.deliveryPostcode = deliveryPostcode;
    }

    public String getVehicle() {
        return this.vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    // Shifted the logic to a method in the class itself
    public Long calculatePrice() {

        // Dictionary mapping vehicle modes to their multipliers
        Map<String, Double> vehicleValues = new HashMap<String, Double>();
        vehicleValues.put("bicycle", 1.1);
        vehicleValues.put("motorbike", 1.15);
        vehicleValues.put("parcel_car", 1.2);
        vehicleValues.put("small_van", 1.3);
        vehicleValues.put("large_van", 1.4);
        
        Double factor;

        // Allows for calculation in the case of no vehicle for tests
        if (this.vehicle == null) {
            factor = 1.0;
        } else {
            factor = vehicleValues.get(this.vehicle);
        }

        Long calculated = Math.round(Math.abs(
            (Long.valueOf(this.deliveryPostcode, 36) 
           - Long.valueOf(this.pickupPostcode,   36))/100000000)*factor);
        return calculated;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
