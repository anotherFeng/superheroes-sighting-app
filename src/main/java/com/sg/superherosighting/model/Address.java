/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author feng
 */
public class Address {
    private int addressId;
    
    @Length(max = 100, message = "Address must be nomore than 100 characters in length.")
    private String address;
    
    @Length(max = 30, message = "City must be nomore than 30 characters in length.")
    private String city;
    
    @Length(max = 30, message = "State must be nomore than 30 characters in length.")
    private String state;
    
    @Length(max = 30, message = "Country must be nomore than 30 characters in length.")
    private String country;
    
    @Length(max = 10, message = "Zip must be nomore than 50 characters in length.")
    private String zip;
    
    @NotEmpty(message = "You must supply a name for the World.")
    @Length(max = 50, message = "World must be nomore than 50 characters in length.")
    private String world;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.addressId;
        hash = 31 * hash + Objects.hashCode(this.address);
        hash = 31 * hash + Objects.hashCode(this.city);
        hash = 31 * hash + Objects.hashCode(this.state);
        hash = 31 * hash + Objects.hashCode(this.country);
        hash = 31 * hash + Objects.hashCode(this.zip);
        hash = 31 * hash + Objects.hashCode(this.world);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (this.addressId != other.addressId) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.zip, other.zip)) {
            return false;
        }
        if (!Objects.equals(this.world, other.world)) {
            return false;
        }
        return true;
    }
    
}
