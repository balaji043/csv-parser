package com.bam.dev.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Employee {
    private String name;
    private Integer age;
    private String city;
    private List<Company> previousCompanies;
}
