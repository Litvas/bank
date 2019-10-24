package com.litvas.bank.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table
@Entity
public class Worker {

    @Id
    private Long id;
    private String firstname;
    private String lastname;
    private Long salary;
    private String position;
    private Project currentProject;

}
