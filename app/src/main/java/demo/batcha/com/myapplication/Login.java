package demo.batcha.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {


    EditText et_pwd,et_email;
    Button btn_signup,btn_login;
    String str_email,str_password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        et_pwd = (EditText)this.findViewById(R.id.et_pwd);
        et_email = (EditText)this.findViewById(R.id.et_email);

        btn_signup = (Button)this.findViewById(R.id.btn_signup);
        btn_login = (Button)this.findViewById(R.id.btn_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_email= et_email.getText().toString();
                str_password = et_pwd.getText().toString();


                if(et_email.getText().length()<=0 || et_pwd.getText().length()<=0){

                    if(et_email.getText().length()<=0){
                        et_email.setError("email is null");
                    }else if(et_pwd.getText().length()<=0){

                    }
                }else{
                    startActivity(new Intent(Login.this,HomeScreen.class));
                }
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Signup.class));
            }
        });



    }
}
