package com.roxanaluca.singleton.planyourparty;

import android.text.style.TabStopSpan;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roxanaluca.model.planyourparty.Item;
import com.roxanaluca.model.planyourparty.ItemInterface;
import com.roxanaluca.model.planyourparty.StandardPackage;
import com.roxanaluca.planyourparty.PackageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariaroxanaluca on 14/03/2018.
 */

public class PackageInventory {

    private static int NUMBEROFPACKAGE = 3;
    private static PackageInventory ourInstance;
    private static List<ItemInterface> m_Model;
    final static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference ref = database.getReference("standardPackage");

    public static PackageInventory getInstance() {
        if (ourInstance == null) {
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    m_Model = new ArrayList<>();
                    Iterable<DataSnapshot> packages = dataSnapshot.getChildren();
                    for (DataSnapshot text : packages){
                        //for current info about the package

                        ArrayList<Item> list = new ArrayList<>();
                        Iterable<DataSnapshot> items = text.getChildren();
                        //get their index in the item list

                        for (DataSnapshot item : items) {
                            int value = item.getValue(Integer.class);
                            list.add(ItemInventory.getItem(value));
                        }

                        m_Model.add(new StandardPackage(list));
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
            ourInstance = new PackageInventory();
        }
        return ourInstance;
    }

    public static int size()
    {
        return m_Model.size();
    }

    private PackageInventory() {
    }

    public static ItemInterface getItem(int i)
    {
        return m_Model.get(i);
    }
}
