/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosighting.dao;

import com.sg.superherosighting.model.Address;
import java.util.List;

/**
 *
 * @author feng
 */
public interface AddressDao {
    public Address addAddress(Address address);
    
    public Address getAddress(int addressId);
    
    public List<Address> getAllAddresses();
    
    public Address updateAddress(Address address);
    
    public void delAddress(int addressId);
    
}
