package main.java.com.nagarro.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Image{

    @Id
    @GeneratedValue
    int id;

}

