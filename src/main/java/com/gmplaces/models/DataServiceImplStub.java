package com.gmplaces.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: svertepniy
 * Date: 22.10.14
 * Time: 9:29
 *
 */
public class DataServiceImplStub implements IDataService {

    @Override
    public String putData(Address address) {
        return "OK";
    }

    @Override
    public List<Address> getData() {
        List<Address> addresses = new ArrayList<Address>();
        Address addr = new Address(48.922633,24.711116999999998, "Місце1");
        addresses.add(addr);
        addr = null;
        addr = new Address(48.9201732,24.71177220000004, "Місце2");
        addresses.add(addr);
        return addresses;
   }

    @Override
    public String clearData() {
        return "OK";
    }

    @Override
    public String removeData(Address address) {
        return "OK";
    }
}
