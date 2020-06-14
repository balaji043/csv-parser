package com.bam.dev;

import com.bam.dev.csv.CSVConverter;
import com.bam.dev.models.Employee;
import com.bam.dev.utils.DataUtil;

import java.io.File;
import java.util.List;
import java.util.Objects;

import static com.bam.dev.utils.DataUtil.csvConfig;

public class Main {

    public static void main(String[] args) {
        List<Employee> employees = DataUtil.getEmployees(4);
        CSVConverter<Employee> employeeCSVConverter = new CSVConverter<>(csvConfig);
        File s = employeeCSVConverter.convert(employees);
        if (Objects.nonNull(s)) {
            System.out.println("CSV file Created");
        } else {
            System.out.println("CSV file not Created");
        }
    }

}
