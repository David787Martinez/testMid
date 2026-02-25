package com.chakray.testmid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

/**
 *
 * @author luis-barrera
 */
@Entity
@Table(name = "address", schema = "public")
public class AddressModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Integer id;
    
    @Column(name = "name", nullable = false, length = 100)
    @Size(min = 1, max = 100)
    private String name;
    
    @Column(name = "street", nullable = false, length = 100)
    @Size(min = 1, max = 100)
    private String street;
    
    @Column(name = "country_code", length = 100)
    @Size(min = 1, max = 100)
    private String countryCode;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id")
    private UsersModel user;

    public AddressModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public UsersModel getUser() {
        return user;
    }

    public void setUser(UsersModel user) {
        this.user = user;
    }
    
    
}
