package com.bam.dev.utils;

import com.bam.dev.models.Company;
import com.bam.dev.models.Employee;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

import static com.bam.dev.csv.CSVConverter.CSVConfig;

@UtilityClass
public class DataUtil {


    public static final CSVConfig csvConfig = new CSVConfig(
            "Employee Details",
            new CSVConfig.CSVColumn("Emp Name", "name"),
            new CSVConfig.CSVColumn("Emp Age", "age"),
            new CSVConfig.CSVColumn("Emp City", "city"),
            new CSVConfig.CSVColumn("Emp Company Name", "company.name")
    );

    public static Company getCompany(int n) {
        Company company = new Company();
        company.setName("Company" + n);
        return company;
    }

    public static Employee getEmployee(int i) {
        Employee employee = new Employee();
        employee.setName("Employee" + i);
        employee.setAge(i + 20);
        employee.setCity("City");
        employee.setCompany(getCompany(i));
        return employee;
    }

    public static List<Employee> getEmployees(int n) {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            employees.add(getEmployee(i));
        }
        return employees;
    }
}
