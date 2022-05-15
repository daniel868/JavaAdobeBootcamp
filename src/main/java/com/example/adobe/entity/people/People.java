package com.example.adobe.entity.people;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class People {

    protected String name;
    protected String surname;
    protected String phoneNumber;
    protected String emailAddress;
}
