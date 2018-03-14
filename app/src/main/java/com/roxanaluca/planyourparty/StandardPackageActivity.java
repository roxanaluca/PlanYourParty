package com.roxanaluca.planyourparty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.roxanaluca.model.planyourparty.StandardPackage;
import com.roxanaluca.singleton.planyourparty.ItemInventory;
import com.roxanaluca.singleton.planyourparty.PackageInventory;

import org.xmlpull.v1.XmlPullParser;

public class StandardPackageActivity extends AppCompatActivity {

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        private int xStart,yStart;
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    xStart = X;
                    yStart = Y;
                    break;
                case MotionEvent.ACTION_UP:
                    int width = findViewById(R.id.package_search).getWidth();
                    if (Y - yStart < 200 && X - xStart > width / 4) {
                        Intent intent = new Intent(StandardPackageActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    }
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_package);

        printStandardPackage();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View content = findViewById(R.id.package_search);
        content.setOnTouchListener(touchListener);

    }

    public void printStandardPackage() //not according to the sequence diagram!!! Should be
    {
        //here you need to add height of PackageView class
        LinearLayout package_content = findViewById(R.id.package_search);
        int i,j;

        for (i = 0; i< PackageInventory.size(); i++)
        //add test CustomPackage class sample for customer package
        {
            PackageView custom = new PackageView(this);

            //set the dimension of the box according to xml format
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            float scaledPixelSize = 150;
            float pixelSize = (int) scaledPixelSize * dm.scaledDensity;
            LinearLayout.LayoutParams rules = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, (int)pixelSize);

            for (j = 0; j< StandardPackage.TypeItem.values().length;j++) {
                StandardPackage standard = (StandardPackage) PackageInventory.getItem(j);
                StandardPackage.TypeItem type = StandardPackage.TypeItem.values()[j];
                custom.setString(type, standard.getPackageItem(type).getName());
            }

            scaledPixelSize = 24;
            pixelSize = (int) scaledPixelSize * dm.scaledDensity;
            custom.setExampleDimension(pixelSize);
            package_content.addView(custom, rules);
        }
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
