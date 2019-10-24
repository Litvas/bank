package com.litvas.bank.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table
@Entity
public class Project {

    @Id
    @SequenceGenerator(name = "project_sequence", sequenceName = "project_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_sequence")
    private Long id;
    private String title;
    private String description;
    private Date startDate;
    private Date deadline;

}
