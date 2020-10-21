package com.example.dell.todolist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExistingItem extends AppCompatActivity {
    ListView existingList;
    ArrayList<String> toDoList;
    DatabaseReference mdbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_item);
        existingList=(ListView)findViewById(R.id.existingList);
        toDoList = new ArrayList<String>();

        mdbref = FirebaseDatabase.getInstance().getReference("message");
        mdbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Toast.makeText(ExistingItem.this, "happy", Toast.LENGTH_SHORT).show();
                for(final DataSnapshot snapshot : dataSnapshot.getChildren()){
                    toDoList.add(snapshot.child("subject").getValue().toString());
                    existingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            String mtr=(String) snapshot.child("subject").getValue();
                            Intent in=new Intent(ExistingItem.this,Details.class);
                           // Toast.makeText(ExistingItem.this, ""+toDoList.get(position), Toast.LENGTH_SHORT).show();
                            in.putExtra("subject",toDoList.get(position));
                            startActivity(in);
                            }
                    });

                }
                if(toDoList.isEmpty())
                {
                    Toast.makeText(ExistingItem.this, "No Items", Toast.LENGTH_SHORT).show();
                }
                else {
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ExistingItem.this, android.R.layout.simple_list_item_1, toDoList);
                    existingList.setAdapter(arrayAdapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
// android:background="#54D81B"