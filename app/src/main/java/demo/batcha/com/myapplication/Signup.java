package demo.batcha.com.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {


    EditText et_email,et_pwd,et_mobno,et_cnf_pwd;
    Button btn_signup;
    SharedPreferences sharepref;
    RadioGroup rg_gender;
    Spinner sp_country;

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
        setContentView(R.layout.activity_signup);


        sharepref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);


        et_email = (EditText)this.findViewById(R.id.et_email);
        et_pwd = (EditText)this.findViewById(R.id.et_pwd);
        et_cnf_pwd = (EditText)this.findViewById(R.id.et_cnf_pwd);
        et_mobno = (EditText)this.findViewById(R.id.et_mobile);

        rg_gender = (RadioGroup)this.findViewById(R.id.rg_gender);
        sp_country = (Spinner)this.findViewById(R.id.sp_country);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select Country");
        categories.add("India");
        categories.add("US");
        categories.add("UK");
        categories.add("Education");
        categories.add("Australia");
        categories.add("British");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sp_country.setAdapter(dataAdapter);

        sp_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String SelecteedCountry = parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        btn_signup = (Button) this.findViewById(R.id.btn_signup);


        btn_signup.setOnClickListener(new View.OnClickListener() {
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
                }else if (rg_gender.getCheckedRadioButtonId()==-1){
                    Toast.makeText(Signup.this,"Please select gender !",Toast.LENGTH_SHORT).show();
                }else if(sp_country.getSelectedItemPosition()==0){
                    ((TextView)sp_country.getSelectedView()).setError("Select Country please");
                    Toast.makeText(Signup.this,"Please select Country !",Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Signup.this,HomeScreen.class));

                }else{
                    //APi response Suceess
                    int selectedId=rg_gender.getCheckedRadioButtonId();
                    RadioButton rbSelectedGender=(RadioButton)findViewById(selectedId);

                    String str_gender = rbSelectedGender.getText().toString();

                    String str_country = sp_country.getSelectedItem().toString();


                    //Toast.makeText(Signup.this,rbSelectedGender.getText(),Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(Signup.this,HomeScreen.class)
                    .putExtra("email",str_email)
                    .putExtra("mobilno",str_monbo));
                    sharepref.edit().putString("key_login","yes").apply();

                }




                Toast.makeText(Signup.this,"your text are\n Email : "+str_email+"\nPAssword : "+str_password,Toast.LENGTH_LONG).show();
                Log.d("debug btn login clicked",str_email+"-----"+str_password);

            }
        });

    }
}
