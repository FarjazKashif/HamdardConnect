package service;

public class VehicleRegistration {
    String vehicleType, vehicleModel, color;
    String vehicleNumber;
    boolean availability = true;

    public VehicleRegistration(String vehicleType, String vehicleModel, String vehicleNumber, String color) {
        this.vehicleType = vehicleType;
        this.vehicleModel = vehicleModel;
        this.vehicleNumber = vehicleNumber;
        this.color = color;
    }
}
