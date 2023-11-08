package com.junit.tutorial;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class DepartmentTest {

    String departmentName;
    Department department;

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1, delimiter = ';')
    public void shouldAddEmployeeFromCsvFile(String firstName, String lastName, String phoneNumExt) {
        System.out.println("Executing Test: shouldAddEmployeeFromCsvFile");
        department.addEmployee(firstName, lastName, phoneNumExt);
        Assertions.assertFalse(department.getAllEmployees().isEmpty());
        Assertions.assertEquals(1, department.getAllEmployees().size());
    }

    @ParameterizedTest
    @CsvSource(value = {"John;Doe;1234", "George;Smith;2134", "Nick;Jones;4321"}, delimiter = ';')
    public void shouldAddEmployeeFromCsvSource(String firstName, String lastName, String phoneNumExt) {
        System.out.println("Executing Test: shouldAddEmployeeFromCsvSource");
        department.addEmployee(firstName, lastName, phoneNumExt);
        Assertions.assertFalse(department.getAllEmployees().isEmpty());
        Assertions.assertEquals(1, department.getAllEmployees().size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234", "2134", "4321"})
    public void shouldAddEmployeeFromValueSource(String phoneNumExt) {
        System.out.println("Executing Test: shouldAddEmployeeFromValueSource");
        department.addEmployee("John", "Doe", phoneNumExt);
        Assertions.assertFalse(department.getAllEmployees().isEmpty());
        Assertions.assertEquals(1, department.getAllEmployees().size());
    }

    @RepeatedTest(10)
    @DisplayName("Should Add Employee with Incrementing Phone Number Ext")
    public void shouldAddEmployeeWithIncrementingPhoneNumExt(RepetitionInfo repetitionInfo) {
        System.out.println("Executing Test: shouldAddEmployeeWithIncrementingPhoneNumExt");
        String phoneNumExt = String.valueOf(repetitionInfo.getCurrentRepetition() + 1000);
        System.out.println("Adding Employee with Phone Num Ext: " + phoneNumExt);
        department.addEmployee("John", "Doe", phoneNumExt);
        Assertions.assertFalse(department.getAllEmployees().isEmpty());
        Assertions.assertEquals(1, department.getAllEmployees().size());
    }

    @Test
    @DisplayName("Should Not Add Employee if First Name is Null")
    public void shouldNotAddEmployeeIfFirstNameIsNull() {
        System.out.println("Executing Test: shouldNotAddEmployeeIfFirstNameIsNull");
        Assertions.assertThrows(RuntimeException.class, () -> department.addEmployee(null, "Doe", "1234"));
    }

    @Test
    @DisplayName("Should Not Add Employee if Last Name is Null")
    public void shouldNotAddEmployeeIfLastNameIsNull() {
        System.out.println("Executing Test: shouldNotAddEmployeeIfLastNameIsNull");
        Assertions.assertThrows(RuntimeException.class, () -> department.addEmployee("John", null, "1234"));
    }

    @Test
    @DisplayName("Should Not Add Employee if Phone Num Ext is Null")
    public void shouldNotAddEmployeeIfPhoneNumExtIsNull() {
        System.out.println("Executing Test: shouldNotAddEmployeeIfPhoneNumExtIsNull");
        Assertions.assertThrows(RuntimeException.class, () -> department.addEmployee("John", "Doe", null));
    }

    @Test
    @DisplayName("Should Not Add Employee if Phone Num Ext length is not equal to 4")
    public void shouldNotAddEmployeeIfPhoneNumExtIsNotEqualTo4() {
        System.out.println("Executing Test: shouldNotAddEmployeeIfPhoneNumExtIsNotEqualTo4");
        Assertions.assertThrows(RuntimeException.class, () -> department.addEmployee("John", "Doe", "123"));
    }

    @Test
    @DisplayName("Should Not Add Employee if Phone Num Ext is not numeric")
    public void shouldNotAddEmployeeIfPhoneNumExtIsNotNumeric() {
        System.out.println("Executing Test: shouldNotAddEmployeeIfPhoneNumExtIsNotNumeric");
        Assertions.assertThrows(RuntimeException.class, () -> department.addEmployee("John", "Doe", "A123"));
    }

    @Test
    @DisplayName("Should Not Add Employee if Phone Num Ext starts with 0")
    public void shouldNotAddEmployeeIfPhoneNumExtStartsWith0() {
        System.out.println("Executing Test: shouldNotAddEmployeeIfPhoneNumExtStartsWith0");
        Assertions.assertThrows(RuntimeException.class, () -> department.addEmployee("John", "Doe", "0123"));
    }

    @Test
    @DisplayName("Should Not Add Employee if Already Exists")
    public void shouldNotAddEmployeeIfAlreadyExists() {
        System.out.println("Executing Test: shouldNotAddEmployeeIfAlreadyExists");
        department.addEmployee("John", "Doe", "1234");
        Assertions.assertThrows(RuntimeException.class, () -> department.addEmployee("John", "Doe", "1234"));
    }

    @Test
    @DisplayName("Should Test Create Department If APP_MODE is equal to DEV")
    public void shouldTestCreateDepartmentIfAppModeIsDev() {
        System.out.println("Checking Assumption: APP_MODE is equal to DEV");
        Assumptions.assumeTrue(System.getenv("APP_MODE").equalsIgnoreCase("DEV"));
        System.out.println("Executing Test: shouldTestCreateDepartmentIfAppModeIsDev");
        Assertions.assertEquals(departmentName, department.getDepartmentName());
    }

    @Test
    @DisplayName("Should Create Department Only On Linux OS")
    @EnabledOnOs(value = {OS.LINUX}, disabledReason = "Enabled only on Linux OS")
    public void shouldCreateDepartmentOnLinuxOS() {
        System.out.println("Executing Test: shouldCreateDepartmentOnLinuxOS");
        Assertions.assertEquals(departmentName, department.getDepartmentName());
    }

    @Test
    @DisplayName("Should Create Department Only On Windows OS")
    @EnabledOnOs(value = {OS.WINDOWS}, disabledReason = "Enabled only on Windows OS")
    public void shouldCreateDepartmentOnWindowsOS() {
        System.out.println("Executing Test: shouldCreateDepartmentOnWindowsOS");
        Assertions.assertEquals(departmentName, department.getDepartmentName());
    }

    @Test
    @DisplayName("Should Create Department")
    public void shouldCreateDepartment() {
        System.out.println("Executing Test: shouldCreateDepartment");
        Assertions.assertEquals(departmentName, department.getDepartmentName());
        Assertions.assertTrue(department.getAllEmployees().isEmpty());
    }

    @Test
    @DisplayName("Should Add Employee")
    public void shouldAddEmployee() {
        System.out.println("Executing Test: shouldAddEmployee");
        department.addEmployee("John", "Doe", "1234");
        Assertions.assertFalse(department.getAllEmployees().isEmpty());
        Assertions.assertEquals(1, department.getAllEmployees().size());
    }

    @BeforeEach
    void setUp() {
        System.out.println("Executing Before Each");
        departmentName = "QA Department";
        department = new Department(departmentName);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Executing After Each");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Executing Before All");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Executing After All");
    }

}