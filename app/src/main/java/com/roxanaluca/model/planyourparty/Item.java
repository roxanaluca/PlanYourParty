package com.roxanaluca.model.planyourparty;

import android.location.LocationListener;

/**
 * Created by ababalola on 13/03/2018.
 */

public class Item implements ItemInterface {

    private String name;
    private String type;
    private String location;
    private int cost;
    private int numberOfGuests;

    public Item(String Name,String Type, String Location,int NumberOfGuests,int Cost)
    {
        name = Name;
        type = Type;
        location = Location;
        cost = Cost;
        numberOfGuests = NumberOfGuests;
    }
    public Item(String Name,String Type, String Location,int Cost)
    {
        name = Name;
        type = Type;
        location = Location;
        cost = Cost;
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
