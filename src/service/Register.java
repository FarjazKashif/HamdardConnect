package service;
public class Register {
    String name, department, cms, phoneNumber, program, gender, roleSelect = null;
    private String password;
    
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

    // VehicleRegistration vR = vR();

}
