package com.roxanaluca.planyourparty;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.xmlpull.v1.XmlPullParser;

public class StandardPackage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_standard_package);

        //here you need to add height of PackageView class
        LinearLayout content = findViewById(R.id.package_search);
        for (int i = 0;i<2;i++)
        //add test CustomPackage class sample for customer package
        {
            PackageView custom = new PackageView(this);
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            float scaledPixelSize = 150;
            float pixelSize = (int) scaledPixelSize * dm.scaledDensity;

            LinearLayout.LayoutParams rules = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, (int)pixelSize);
            custom.setFoodString("Spaghetti");
            custom.setMusicString("Max Production");
            custom.setVenueString("London");

            scaledPixelSize = 24;
            pixelSize = (int) scaledPixelSize * dm.scaledDensity;
            custom.setExampleDimension(pixelSize);
            content.addView(custom, rules);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
