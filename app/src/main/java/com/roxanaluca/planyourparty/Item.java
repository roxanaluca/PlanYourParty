package com.roxanaluca.planyourparty;

/**
 * Created by ababalola on 13/03/2018.
 */

public class Item implements ItemInterface {

    private String name;
    private String type;
    private String location;
    private int numberOfGuests;

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
