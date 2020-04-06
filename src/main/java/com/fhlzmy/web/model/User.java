package com.fhlzmy.web.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Getter
@Setter
@javax.persistence.Table(name = "user")
public class User {

    @Id
    private Long id;

    @Column
    private String account;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private String sex;

}
