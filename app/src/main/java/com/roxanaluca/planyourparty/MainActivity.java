package com.roxanaluca.planyourparty;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.roxanaluca.model.planyourparty.Customer;
import com.roxanaluca.model.planyourparty.User;
import com.roxanaluca.singleton.planyourparty.UserInventory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by mariaroxanaluca on 16/03/2018.
 */

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Choose authentication providers
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.PhoneBuilder().build(),
                    new AuthUI.IdpConfig.FacebookBuilder().build());

            // Create and launch sign-in intent
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);
        }
        else {
            UserInventory.getInstance();
            // Successfully signed in
            loadInformation();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                FirebaseUserMetadata metadata = FirebaseAuth.getInstance().getCurrentUser().getMetadata();

                if (metadata.getCreationTimestamp() == metadata.getLastSignInTimestamp()) {
                    sendToRegisterPage();
                }
                else {
                    UserInventory.getInstance();
                    // Successfully signed in
                    loadInformation();
                }
                // ...
            } else {

                // Sign in failed, check response for error code
                // ...
            }
        }
    }

    private static CountDownLatch latch;

    public void loadInformation()
    {
        setContentView(R.layout.progress_bar);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new AsyncTask<Object, Void, Void>() {
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                latch = new CountDownLatch(1);
                UserInventory.loadUserInformation(latch);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected Void doInBackground(Object... params) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                progressBar.setVisibility(View.GONE);
                sendToMainPage();

            }
        }.execute();

    }

    public void sendToRegisterPage()
    {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void sendToMainPage()
    {
        User userCustom = UserInventory.getUserInformation();
        // here we need to identify the type of user

        if (userCustom == null) {
            sendToRegisterPage();
            return;
        }
        if (userCustom instanceof Customer) {
            Intent intent = new Intent(MainActivity.this, CustomerMainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }else{
            /*Intent intent = new Intent(MainActivity.this, SupplierMainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);*/
        }
    }

    protected void logout()
    {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
}
