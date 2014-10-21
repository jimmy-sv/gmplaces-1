package com.gmplaces.models;

import java.util.List;

public interface IDataService {

    public String putData(long lat,long lng, String description);

    public List<Address> getData();

    public String clearData();

}
