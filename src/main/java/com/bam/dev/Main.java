package com.bam.dev;

import static com.bam.dev.utils.DataUtil.csvConfig;

import com.bam.dev.csv.CSVConverter;
import com.bam.dev.models.Company;
import com.bam.dev.models.Employee;
import com.bam.dev.utils.DataUtil;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        Company companies = DataUtil.getCompany(1);

        CSVConverter<Employee> employeeCSVConverter = new CSVConverter<>(csvConfig);

        File s = employeeCSVConverter.convert(companies.getEmployees());


    }

}
