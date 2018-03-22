package com.roxanaluca.model.planyourparty;

/**
 * Created by ababalola on 13/03/2018.
 */

public class Customer extends User
{
    private String address;

    public Customer(String username, String name) {
        super(username, name);
    }

    public Customer(String username, String name, String address) {
        super(username, name);
        this.address = address;
    }
}
