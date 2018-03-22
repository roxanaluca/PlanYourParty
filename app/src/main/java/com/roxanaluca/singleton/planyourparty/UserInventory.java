package com.roxanaluca.singleton.planyourparty;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.roxanaluca.model.planyourparty.Customer;
import com.roxanaluca.model.planyourparty.Supplier;
import com.roxanaluca.model.planyourparty.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by mariaroxanaluca on 17/03/2018.
 */

public class UserInventory {

    final static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static DatabaseReference ref;
    private static UserInventory ourInstance;
    private static FirebaseUser user;

    public static UserInventory getInstance() {
        if (ourInstance == null) {
            ourInstance = new UserInventory();
        }
        return ourInstance;
    }

    private UserInventory() {

    }

    private static User myAccount = null;


    public static void loadUserInformation(final CountDownLatch latch) {

        database.goOnline();
        user = FirebaseAuth.getInstance().getCurrentUser();

        ref = database.getReference("users").child(user.getUid());
        ref.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                latch.countDown();
                if ((boolean)dataSnapshot.child("isCustomer").getValue()) {
                    String name = user.getDisplayName();
                    myAccount = new Customer("", name);
                } else if ((boolean) dataSnapshot.child("isSupplier").getValue()) {
                    String name = user.getDisplayName();
                    myAccount = new Supplier("", name);
                } else {
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                latch.countDown();
            }
        });

    }

    public static User getUserInformation()
    {
        return myAccount;
    }

    public static void addUserInformation(User userC){
        database.goOnline();
        user = FirebaseAuth.getInstance().getCurrentUser();
        ref = database.getReference("users").child(user.getUid());
        Map<String, Object> node = new HashMap<>();
        if (userC instanceof Customer)
        {
            node.put("isCustomer",true);
            node.put("isSupplier",false);
        }
        else {
            node.put("isCustomer",false);
            node.put("isSupplier",true);
        }

        ref.setValue(node);
    }
}
