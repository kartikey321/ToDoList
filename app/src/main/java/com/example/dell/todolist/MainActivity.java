package com.example.dell.todolist;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
 Button existingItem, addItem;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);
           existingItem=(Button)findViewById(R.id.existingItem);
           addItem=(Button)findViewById(R.id.addItem);

           existingItem.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent in=new Intent(MainActivity.this,ExistingItem.class);
                   startActivity(in);
               }
           });
           addItem.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent in=new Intent(MainActivity.this,CreateItem.class);
                   startActivity(in);
               }
           });
       }
}
