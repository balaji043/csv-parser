package com.bam.dev.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Company {
    private String name;
    private List<Employee> employees;
}
