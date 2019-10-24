package com.litvas.bank.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table
@Entity
public class Project {

    @Id
    private Long id;
    private String title;
    private String description;
    private Date startDate;
    private Date deadline;

}
