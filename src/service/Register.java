package service;
public class Register {
    String name, department, cms, phoneNumber, program, gender, roleSelect = null;
    private String password;
    Vehicle vehicle;
    
    Register(String cms, String phoneNumber, String name, String pass, String dept, String program, String gender, String roleSelect) {
        this.cms = cms;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.password = pass;
        this.department = dept;
        this.program = program;
        this.gender = gender;
        this.roleSelect = roleSelect; // Normal Student or Rider
    }

    public void addVehicle(String vehicleType, String vehicleModel, String vehicleNumber, String color) {
        this.vehicle = new Vehicle(vehicleType, vehicleModel, vehicleNumber, color);
    }


}
