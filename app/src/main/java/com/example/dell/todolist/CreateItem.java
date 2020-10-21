package com.example.dell.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class CreateItem extends AppCompatActivity {
  Button save,cancel;
  EditText subject,details;
    DatabaseReference reff;
    DatePicker picker;
    ItemDatabase itemDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);
        save=(Button)findViewById(R.id.save);
        cancel=(Button)findViewById(R.id.cancel);
        subject=(EditText)findViewById(R.id.subject);
        details=(EditText)findViewById(R.id.details);
        picker=(DatePicker)findViewById(R.id.datePicker1);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* itemDatabase=new ItemDatabase();
                reff= FirebaseDatabase.getInstance().getReference("message");
                itemDatabase.setSubject(subject.getText().toString());
                itemDatabase.setDetails(details.getText().toString());
                itemDatabase.setDay(picker.getDayOfMonth());
                itemDatabase.setMonth(picker.getMonth());
                itemDatabase.setYear(picker.getYear());
                reff.push().setValue(itemDatabase);*/
                Random random = new Random();
                int randomNumber = random.nextInt(10000-200) + 65;
                String str=Integer.toString(randomNumber);
                reff= FirebaseDatabase.getInstance().getReference("message");
                ItemDatabase food_item_db=new ItemDatabase(str,subject.getText().toString(),
                                                            details.getText().toString(),
                                                             picker.getDayOfMonth(),picker.getMonth(),
                                                              picker.getYear());
                //reff.child(subject.getText().toString()).setValue(food_item_db);
                reff.child(str).setValue(food_item_db);
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateItem.this);
                Toast.makeText(CreateItem.this, "Saved item in the Database.", Toast.LENGTH_SHORT).show();
                builder.setMessage("Items saved in the database");
                builder.setTitle("Confirmation");
                builder.setCancelable(false);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which)
                                    {
                                        Intent in=new Intent(CreateItem.this,MainActivity.class);
                                        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(in);
                                        finish();
                                    }
                                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(CreateItem.this,MainActivity.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                finish();

            }
        });



    }
}
