package com.gmplaces.models;

import java.util.List;

/**
 * Created by roman vintonyak on 22.10.14.
 */
public interface IDataService {

    //put new address into file
    //returns status of operation
    public String putData(Address address);

    //return all data
    public List<Address> getData();

    //clear all data
    public String clearData();

    //remove specific address from file
    public String removeData(Address address);
}
