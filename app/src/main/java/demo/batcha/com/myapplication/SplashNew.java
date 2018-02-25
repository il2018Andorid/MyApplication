package demo.batcha.com.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashNew extends AppCompatActivity {


    final int SPLASH_TIME_OUT = 3500;
    SharedPreferences sharepref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_new);

        sharepref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if(sharepref.getString("key_login", "no").equalsIgnoreCase("Yes")){

                    startActivity(new Intent(SplashNew.this,Signup.class));
                    finish();

                }else{
                    startActivity(new Intent(SplashNew.this, Login.class));
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);

    }
}
