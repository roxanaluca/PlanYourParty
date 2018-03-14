package com.roxanaluca.singleton.planyourparty;

import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roxanaluca.model.planyourparty.Item;

import java.util.*;

import static com.roxanaluca.singleton.planyourparty.ItemInventory.loadDatabase;

/**
 * Created by ababalola on 13/03/2018.
 */

public class ItemInventory {
    private static ItemInventory ourInstance;
    private static List<Item> m_Model;
    final static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference ref = database.getReference("item");

    public static ItemInventory getInstance() {
        if (ourInstance == null) {
            loadDatabase();
            ourInstance = new ItemInventory();
        }
        return ourInstance;
    }

    private static void loadDatabase()
    {

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                m_Model = new ArrayList<>();
                Iterable<DataSnapshot> items = dataSnapshot.getChildren();
                for (DataSnapshot text : items){
                    Item item = text.getValue(Item.class);
                    m_Model.add(item);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    private ItemInventory() {

    }

    public static Item getItem(int i)
    {
        if (m_Model!=null)
            return m_Model.get(i);
        return getInstance().getItem(i);
    }
}
