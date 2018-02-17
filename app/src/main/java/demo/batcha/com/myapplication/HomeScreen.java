package demo.batcha.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        String email = getIntent().hasExtra("email")
                ?getIntent().getStringExtra("email").toString() : "";

        String mobno = getIntent().getStringExtra("mobilno").toString();

    }
}
