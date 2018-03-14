package com.roxanaluca.model.planyourparty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariaroxanaluca on 14/03/2018.
 */

public class StandardPackage implements ItemInterface{
    List<Item> list;
    public StandardPackage(List<Item> listInitial)
    {
        if (listInitial.size() == 3)
            list = new ArrayList<Item>(listInitial);
        else list = null;
    }

    public Item getPackageItem(TypeItem type)
    {
        switch (type){
            case FOOD:
                return list.get(0);
            case VENUE:
                return list.get(1);
            case MUSIC:
                return list.get(2);
        }
        return null;
    }

    public enum TypeItem {
        FOOD,VENUE, MUSIC;
    }
}
