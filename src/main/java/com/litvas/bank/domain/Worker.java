package com.litvas.bank.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "worker_sequence")
    @SequenceGenerator(name = "worker_sequence", sequenceName = "worker_sequence")
    private Long id;
    private String firstname;
    private String lastname;
    private Long salary;
    private String position;
    @OneToOne
    private Project currentProject;

}
