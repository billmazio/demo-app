package com.junit.tutorial;

import java.util.ArrayList;

public class Department {

    private String departmentName;
    private ArrayList<Employee> employeeList;

    public Department(String departmentName) {
        this.departmentName = departmentName;
        this.employeeList = new ArrayList<>();
    }

    public void addEmployee(String firstName, String lastName, String phoneNumberExtension) {
        Employee employee = new Employee(firstName, lastName, phoneNumberExtension);
        validateEmployee(employee);
        checkIfEmployeeAlreadyExists(employee);
        this.employeeList.add(employee);
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public ArrayList<Employee> getAllEmployees() {
        return this.employeeList;
    }

    private void validateEmployee(Employee employee) {
        employee.validateFirstName();
        employee.validateLastName();
        employee.validatePhoneNumberExt();
    }

    private void checkIfEmployeeAlreadyExists(Employee newEmployee) {
        for(Employee employee : this.employeeList) {
            if (employee.getFirstName().equalsIgnoreCase(newEmployee.getFirstName())
                    && employee.getLastName().equalsIgnoreCase(newEmployee.getLastName())
                    && employee.getPhoneNumberExt().equals(newEmployee.getPhoneNumberExt())) {
                throw new RuntimeException();
            }
        }
    }
}
