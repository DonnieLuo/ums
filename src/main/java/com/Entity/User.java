package com.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Donnie on 2017/2/28.
 */
@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
}
