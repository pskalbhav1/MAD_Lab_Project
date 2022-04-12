package com.example.project;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioButton;

import java.net.DatagramPacket;
import java.util.jar.Attributes;

public class MainActivity extends Activity {
    Button b1, b2;
    EditText ed1, ed2,ed3;
    TextView tx1,ex,ex1,txt2,txt3;
    boolean admin=false,student=false;
    String role="admin";
    String w="m",x="admin",y="admin",z;
    private DBHandler dbHandler;
    public abstract class SQLiteOpenHelper{};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt2=(TextView) findViewById(R.id.textView1);
        txt3=(TextView) findViewById(R.id.textView2);

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin(view);
            }
        });
        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegister(view);
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

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

        b1 = (Button) findViewById(R.id.button);
        ed1 = (EditText) findViewById(R.id.editText1);
        ed2 = (EditText) findViewById(R.id.editText2);
        b2 = (Button) findViewById(R.id.button2);
        ex=(TextView) findViewById(R.id.TextView);
        ex1=(TextView) findViewById(R.id.TextView1);
       
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Make corrections
                String query = "Select * From STUDENTS where Reg = '"+ed3.getText().toString() +"'";
                if(ed3.getText().toString().isEmpty() || ed2.getText().toString().isEmpty() || ed1.getText().toString().isEmpty() || z==" ")
                {
                    Toast.makeText(getApplicationContext(),"Fill in all the details", Toast.LENGTH_SHORT).show();
                    onLogin(v);
                }
                else if(query.contains(ed1.getText().toString()) && query.contains("admin") && query.contains(ed2.getText().toString()) && role=="admin")
                {
                    Toast.makeText(getApplicationContext(), "Admin Login", Toast.LENGTH_SHORT).show();
                    onProfile(v);
                }
                else if(query.contains(ed1.getText().toString()) && query.contains("student") && query.contains(ed2.getText().toString()) && role=="student")
                {
                    Toast.makeText(getApplicationContext(), "Student Login", Toast.LENGTH_SHORT).show();
                    onProfile(v);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    onLogin(v);
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Bye..", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegister(view);
            }
        });
    }

    public void onRegister(View view) {
        setContentView(R.layout.register);
        b1=(Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        ex1 = (TextView) findViewById(R.id.TextView1);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);

        dbHandler = new DBHandler(MainActivity.this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                w=ed3.getText().toString();
                x=ed1.getText().toString();
                y=ed2.getText().toString();
                z=role;
                if(ed3.getText().toString().isEmpty() || ed2.getText().toString().isEmpty() || ed1.getText().toString().isEmpty() || z==" ")
                {
                    Toast.makeText(getApplicationContext(),"Fill in all the details", Toast.LENGTH_SHORT).show();
                    onRegister(v);
                }
                else {
                    dbHandler.addNewCourse(w, x, y, z);
                    Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                    onLogin(view);
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Bye..", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        ex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin(view);
            }
        });
    }

    public void onProfile(View view){
        setContentView(R.layout.profile);
    }
}
