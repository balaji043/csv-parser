package com.bam.dev.utils;

import static com.bam.dev.csv.CSVConverter.CSVConfig;
import com.bam.dev.models.Company;
import com.bam.dev.models.Employee;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class DataUtil {


    public static final CSVConfig csvConfig = new CSVConfig(
            "Employee Details",
            new CSVConfig.CSVColumn("Emp Name", "name"),
            new CSVConfig.CSVColumn("Emp Age", "age"),
            new CSVConfig.CSVColumn("Emp City", "city"),
            new CSVConfig.CSVColumn("Emp Company Name", "company.name")
    );

    public static List<Company> getCompanies() {
        ArrayList<Company> companies = new ArrayList<Company>();
        for (int i = 0; i < 5; i++)
            companies.add(getCompany(i));
        return companies;
    }

    public static Company getCompany(int n) {
        ArrayList<Employee> employees = new ArrayList<Employee>();

        Company company = new Company();
        company.setName("Company" + n);
        company.setEmployees(employees);

        for (int i = 1; i <= 2; i++)
            employees.add(getEmployee(i, company));

        return company;
    }

    public static Employee getEmployee(int i, Company company) {
        Employee employee = new Employee();
        employee.setName("Employee" + i);
        employee.setAge(i + 20);
        employee.setCity("City");
        employee.setCompany(company);
        return employee;
    }
}
