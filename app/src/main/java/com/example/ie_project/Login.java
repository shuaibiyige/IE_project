package com.example.ie_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity
{
    private Button signIn;
    private Button signUp;
    private EditText email;
    private EditText password;
    private Button forget;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();

        signIn = (Button) findViewById(R.id.sign_in);
        signUp = (Button) findViewById(R.id.sign_up);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        forget = (Button) findViewById(R.id.forget);

        signIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (checkEmail(email.getText().toString()) && email.getText().toString().trim().length() != 0 && password.getText().toString().trim().length() != 0)
                {
                    String user_email = email.getText().toString();
                    String ueser_password = password.getText().toString();

                    // save data

                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }
                else        // not valid
                {
                    if (!checkEmail(email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                    else if (email.getText().toString().trim().length() == 0)
                        Toast.makeText(getApplicationContext(), "Email can not be empty", Toast.LENGTH_SHORT).show();
                    else if (password.getText().toString().trim().length() == 0)
                        Toast.makeText(getApplicationContext(), "Password can not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {



            }
        });
    }

    public boolean checkEmail(String email)
    {
        boolean flag;
        try
        {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }
        catch (Exception e)
        {
            flag = false;
        }
        return flag;
    }
}
