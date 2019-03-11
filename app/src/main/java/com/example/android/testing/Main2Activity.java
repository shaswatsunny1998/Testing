package com.example.android.testing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends Activity {
    private EditText user;
    private EditText pass;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        user=(EditText) findViewById(R.id.editText);
        pass=(EditText) findViewById(R.id.editText2);
        btn=(Button) findViewById(R.id.button);
        String username=user.getText().toString();
        String pass=user.getText().toString();
    }
    public void clickbtn(View v)
    {
        String username=user.getText().toString();
        String pas=pass.getText().toString();
        if(username.equals("shaswat") && pas.equals("12345"))
        {
            startActivity(new Intent(this,MainActivity.class));
        }
        else
        {
            Toast.makeText(this, "Incorrect User", Toast.LENGTH_SHORT).show();
        }
    }
}
