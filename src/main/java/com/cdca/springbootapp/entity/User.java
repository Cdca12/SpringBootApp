package com.cdca.springbootapp.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Cdca
 */
@Entity
@Data
@Table(name = "Users")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 50)
    private String name;
    
    private String surname;
    
    @Column(name = "mail", nullable = false, length = 50, unique = true)
    private String email;
    
    private Boolean enabled;
    
}
