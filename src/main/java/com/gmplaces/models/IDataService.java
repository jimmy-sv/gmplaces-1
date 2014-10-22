package com.gmplaces.models;

import java.util.List;

public interface IDataService {

    public String putData(Address address);

    public List<Address> getData();

    public String clearData();

}
