package com.roxanaluca.planyourparty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.roxanaluca.model.planyourparty.Customer;
import com.roxanaluca.singleton.planyourparty.UserInventory;

public class RegisterActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button customer = findViewById(R.id.customer);
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer user = new Customer("",FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                UserInventory.addUserInformation(user);

                Intent intent = new Intent(RegisterActivity.this, CustomerMainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }


}
