package com.company.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ERROR_REPORT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErrorReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TYPE", nullable = false)
    private String exceptionType;

    @Column(name = "MESSAGE")
    private String message;

    @CreationTimestamp
    @Column(name = "OCCURED_ON", nullable = false)
    private Date timeOccured;



}