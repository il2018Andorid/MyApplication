package demo.batcha.com.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    EditText et_email,et_pwd,et_mobno,et_cnf_pwd;
    Button btn_login,btn_signup;
    SharedPreferences sharepref;

    String str_email,str_password,str_monbo,str_cnf_pwd;

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharepref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);


        et_email = (EditText)this.findViewById(R.id.et_email);
        et_pwd = (EditText)this.findViewById(R.id.et_pwd);
        et_cnf_pwd = (EditText)this.findViewById(R.id.et_cnf_pwd);
        et_mobno = (EditText)this.findViewById(R.id.et_mobile);

        btn_login = (Button)this.findViewById(R.id.btn_login);
        btn_signup = (Button) this.findViewById(R.id.btn_signup);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_email= et_email.getText().toString();
                str_password = et_pwd.getText().toString();
                str_monbo = et_mobno.getText().toString();
                str_cnf_pwd=et_cnf_pwd.getText().toString();


                if(et_email.getText().length()<=0 || et_pwd.getText().length()<=0 || et_mobno.getText().length()<=0){

                    if(et_email.getText().length()<=0){
                        et_email.setError("email is null");
                    }else if(et_mobno.getText().length()<=0){

                    }else if(et_pwd.getText().length()<=0){

                    }
                }else if(et_mobno.getText().length()!=10){
                    et_mobno.setError("valid mobile");
                }else if(isEmailValid(str_email) ==false) {
                    et_email.setError("Not valid email");
                }else if(!et_cnf_pwd.getText().toString().equals(et_pwd.getText().toString())){
                    et_pwd.setError("password not matched..");
                }else{
                    //APi response Suceess
                    startActivity(new Intent(MainActivity.this,Second.class));
                    sharepref.edit().putString("key_login","yes").apply();

                }




                Toast.makeText(MainActivity.this,"your text are\n Email : "+str_email+"\nPAssword : "+str_password,Toast.LENGTH_LONG).show();
                Log.d("debug btn login clicked",str_email+"-----"+str_password);

            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
