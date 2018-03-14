package com.roxanaluca.model.planyourparty;

import android.location.LocationListener;

/**
 * Created by ababalola on 13/03/2018.
 */

public class Item implements ItemInterface {

    private String name;
    private String type;
    private String location;
    private double cost;
    private int numberOfGuests;

    public Item()
    {
        this.name = "";
        this.type = "";
        this.location = "";
        this.cost = 0;
        this.numberOfGuests = 0;
    }

    public Item(String name,String type, String location,int numberOfGuests,double cost)
    {
        this.name = name;
        this.type = type;
        this.location = location;
        this.cost = cost;
        this.numberOfGuests = numberOfGuests;
    }

    public Item(String name,String type, String location,double cost)
    {
        this.name = name;
        this.type = type;
        this.location = location;
        this.numberOfGuests = 0;
        this.cost = cost;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }


    public String getLocation()
    {
        return location;
    }


    public int getNumberOfGuests()
    {
        return numberOfGuests;
    }

}
