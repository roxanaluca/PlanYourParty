package com.roxanaluca.planyourparty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.roxanaluca.model.planyourparty.ItemInterface;
import com.roxanaluca.singleton.planyourparty.ItemInventory;
import com.roxanaluca.singleton.planyourparty.PackageInventory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
         * SetUp database
         */

        ItemInventory.getInstance();
        PackageInventory.getInstance();

        setContentView(R.layout.activity_main);

        Button button_custom_package = findViewById(R.id.button_custom_package);
        button_custom_package.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomPackageActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });

        Button button_standard_package = findViewById(R.id.button_standard_package);
        button_standard_package.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StandardPackageActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

            }
        });

        Button button_orders = findViewById(R.id.button_orders);
        button_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Orders.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });
    }
}
