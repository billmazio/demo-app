package com.junit.tutorial;

public class Employee {

    private String firstName;
    private String lastName;
    private String phoneNumberExt;

    public Employee(String firstName, String lastName, String phoneNumberExt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumberExt = phoneNumberExt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumberExt() {
        return phoneNumberExt;
    }

    public void validateFirstName() {
        if (this.firstName == null) {
            throw new RuntimeException("First Name cannot be null or empty");
        }
    }

    public void validateLastName() {
        if (this.lastName == null) {
            throw new RuntimeException("Last Name cannot be null or empty");
        }
    }

    public void validatePhoneNumberExt() {
        if (this.phoneNumberExt == null) {
            throw new RuntimeException("Phone Number Extension cannot be null or empty");
        }

        if (this.phoneNumberExt.length() != 4) {
            throw new RuntimeException("Phone Number Extension can be 4 digits long");
        }

        if (!this.phoneNumberExt.matches("\\d+")) {
            throw new RuntimeException("Phone Number Extension can contain only digits");
        }

        if (this.phoneNumberExt.startsWith("0")) {
            throw new RuntimeException("Phone Number Extension cannot start with 0");
        }
    }
}
