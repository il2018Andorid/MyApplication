package demo.batcha.com.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {


    final int SPLASH_TIME_OUT = 3500;
    SharedPreferences sharepref;
    ImageView imgLogo1,imgLogo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharepref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if(sharepref.getString("key_login", "no").equalsIgnoreCase("Yes")){

                    startActivity(new Intent(SplashScreen.this,Signup.class));
                    finish();

                }else{
                    startActivity(new Intent(SplashScreen.this, Login.class));
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);

    }
}
