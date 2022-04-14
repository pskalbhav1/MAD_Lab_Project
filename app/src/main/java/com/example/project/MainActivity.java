package com.example.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioButton;

public class MainActivity extends Activity {
    Button b_login,b_register,b_cancel,b_logout,b_book,b_booking,b_request;
    EditText ed_name, ed_regno,ed_psw;
    TextView t_login,t_register;
    boolean admin=false,student=false;
    String role="admin";
    String regno="m",name="admin",psw="admin",c_role;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t_login=(TextView) findViewById(R.id.textView1);
        t_register=(TextView) findViewById(R.id.textView2);

        t_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin(view);
            }
        });
        t_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegister(view);
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        admin=false;
        student=false;
        switch (view.getId()) {
            case R.id.radio_admin:
                if (checked) {
                    admin = true;
                    role = "admin";
                    break;
                }

            case R.id.radio_student:
                if (checked) {
                    student=true;
                    role="student";
                    break;
                }
        }
    }
    public void onLogin(View view) {
        setContentView(R.layout.login);

        b_login = (Button) findViewById(R.id.buttony);
        ed_name = (EditText) findViewById(R.id.editText1);
        ed_psw = (EditText) findViewById(R.id.editText2);
        b_cancel = (Button) findViewById(R.id.button2);
        t_register=(TextView) findViewById(R.id.TextView);

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Make corrections
                String query = "Select * From STUDENTS where Reg = '"+ed_regno.getText().toString() +"'";
                if(query.isEmpty())
                {
                    onRegister(v);
                }
                else if(ed_regno.getText().toString().isEmpty() || ed_psw.getText().toString().isEmpty() || ed_name.getText().toString().isEmpty() || c_role==" ")
                {
                    Toast.makeText(getApplicationContext(),"Fill in all the details", Toast.LENGTH_SHORT).show();
                    onLogin(v);
                }else if(query.contains(ed_name.getText().toString())  && query.contains(ed_psw.getText().toString()) && student && !admin)
                {
                    Toast.makeText(getApplicationContext(), "Student Login", Toast.LENGTH_SHORT).show();
                    // onProfile_student(v);
                }
                else if(query.contains(ed_name.getText().toString()) && query.contains(ed_psw.getText().toString()) && admin && !student)
                {
                    Toast.makeText(getApplicationContext(), "Admin Login", Toast.LENGTH_SHORT).show();
                    //onProfile_admin(v);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    onLogin(v);
                }
            }
        });

        b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Bye..", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        t_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegister(view);
            }
        });
    }

    public void onRegister(View view) {
        setContentView(R.layout.register);
        b_register=(Button) findViewById(R.id.button);
        b_cancel = (Button) findViewById(R.id.button2);
        t_login = (TextView) findViewById(R.id.TextView1);
        ed_name = (EditText) findViewById(R.id.editText);
        ed_psw = (EditText) findViewById(R.id.editText2);
        ed_regno = (EditText) findViewById(R.id.editText3);

        dbHandler = new DBHandler(MainActivity.this);
        b_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regno=ed_regno.getText().toString();
                name=ed_name.getText().toString();
                psw=ed_psw.getText().toString();
                c_role=role;
                if(ed_name.getText().toString().isEmpty() || ed_psw.getText().toString().isEmpty() || ed_regno.getText().toString().isEmpty() || c_role==" ")
                {
                    Toast.makeText(getApplicationContext(),"Fill in all the details", Toast.LENGTH_SHORT).show();
                    onRegister(view);
                }
                else {
                    dbHandler.addNewCourse(regno,name,psw,c_role);
                    Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                    onLogin(view);
                }
            }
        });

        b_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Bye..", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        t_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin(view);

            }
        });
    }

    public void onProfile_admin(View view){
        setContentView(R.layout.profile);
        b_booking=(Button) findViewById(R.id.button);
        b_request = (Button) findViewById(R.id.button2);
        b_logout=(Button) findViewById(R.id.button3);
        b_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check_Booking(v);
            }
        });
        b_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check_Request(v);
            }
        });
        b_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogout(v);
            }
        });
    }

    public void onProfile_student(View view){
        setContentView(R.layout.profile_student);
        b_book=(Button)findViewById(R.id.button);
        b_request = (Button) findViewById(R.id.button2);
        b_logout=(Button) findViewById(R.id.button3);
        b_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBooking(v);
            }
        });
        b_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRequest(v);
            }
        });
        b_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogout(v);
            }
        });
    }

    public void onLogout(View v){
        setContentView(R.layout.activity_main);
    }

    public void onBooking(View v){
        setContentView(R.layout.slot);
    }

    public void onRequest(View v){
        setContentView(R.layout.request);
    }

    public void Check_Booking(View v){
        setContentView(R.layout.slot_view);
    }

    public void Check_Request(View v){
        setContentView(R.layout.grant_request);
    }
}