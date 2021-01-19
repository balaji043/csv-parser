package com.bam.dev;

import com.bam.dev.csv.CSVConverter;
import com.bam.dev.models.Company;
import com.bam.dev.models.Employee;
import com.bam.dev.utils.DataUtil;

import java.io.File;
import java.util.List;
import java.util.Objects;

import static com.bam.dev.csv.CSVConverter.CSVConfig;
import static com.bam.dev.utils.DataUtil.CSV_CONFIG_COMPANY;
import static com.bam.dev.utils.DataUtil.CSV_CONFIG_EMPLOYEE;

public class Main {

    public static void main(String[] args) {

        List<Employee> employees = DataUtil.getEmployees(4);
        CSVConverter<Employee> employeeCSVConverter = new CSVConverter<>(CSV_CONFIG_EMPLOYEE);
        File employeeFile = employeeCSVConverter.convert(employees);
        printResult(employeeFile);

        List<Company> companies = DataUtil.getCompanies(4);
//        CSVConverter<Company> companyCSVConverter = new CSVConverter<Company>(CSV_CONFIG_COMPANY);
//        File companyFile = companyCSVConverter.convert(companies);
//        printResult(companyFile);

//        CSVConverter<Employee> employeeCSVConverter = new CSVConverter<>();
//        File empFile = employeeCSVConverter.convert(employees, CSV_CONFIG_EMPLOYEE);
//        printResult(empFile);

        convertToCSVFiles(companies, CSV_CONFIG_COMPANY);
        convertToCSVFiles(employees, CSV_CONFIG_EMPLOYEE);

    }

    private static <T> void convertToCSVFiles(List<T> list, CSVConfig<T> csvConfig) {
        CSVConverter<T> employeeCSVConverter = new CSVConverter<>(csvConfig);
        File employeeFile = employeeCSVConverter.convert(list);
        printResult(employeeFile);
    }

    private static void printResult(File file) {
        if (Objects.nonNull(file)) {
            System.out.println("CSV file Created");
        } else {
            System.out.println("CSV file not Created");
        }
    }

}
