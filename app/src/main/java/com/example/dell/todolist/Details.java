package com.example.dell.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Details extends AppCompatActivity {
Button modify,done,save;
EditText subjectitem,detailsitem;
String subject1;
DatabaseReference mref;

    DatePicker picker;
    ItemDatabase itemDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        subject1=intent.getStringExtra("subject");

        modify=(Button)findViewById(R.id.modify);
        done=(Button)findViewById(R.id.done);
        save=(Button)findViewById(R.id.save);
        subjectitem=(EditText)findViewById(R.id.subjectitem);
        detailsitem=(EditText)findViewById(R.id.detailsitem);
        mref = FirebaseDatabase.getInstance().getReference("message");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(final DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(snapshot.child("subject").getValue().toString().equals(subject1))
                    {
                        subjectitem.setText(snapshot.child("subject").getValue().toString());
                        detailsitem.setText(snapshot.child("details").getValue().toString());
                        Toast.makeText(Details.this, "Done"+snapshot.child("day").getValue().toString(), Toast.LENGTH_SHORT).show();
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        subjectitem.setEnabled(false);
        detailsitem.setEnabled(false);
        save.setEnabled(false);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjectitem.setEnabled(true);
                detailsitem.setEnabled(true);
                save.setEnabled(true);
            }
        });


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                delete_item(subject1);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                issubjectitemChanged(subjectitem.getText().toString(),detailsitem.getText().toString());
                Toast.makeText(Details.this, "Data modified", Toast.LENGTH_SHORT).show();
                Intent in=new Intent(Details.this,MainActivity.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                finish();
            }
        });
    }

    private void issubjectitemChanged(final String str, final String mtr) {
                  //mref.child(subject1).child("subject").setValue(str);
            //return "yes";
            mref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Toast.makeText(Details.this, "dbref"+mref, Toast.LENGTH_SHORT).show();
                    for(DataSnapshot snapshot:dataSnapshot.getChildren())
                    {
                        if(snapshot.child("subject").getValue().equals(subject1))
                        {

                            String itemno=(String)snapshot.child("str").getValue();
                            DatabaseReference dbr=FirebaseDatabase.getInstance().getReference("message");
                            Toast.makeText(Details.this, "dbr "+dbr, Toast.LENGTH_SHORT).show();
                            mref.child(itemno).child("subject").setValue(str);
                            mref.child(itemno).child("details").setValue(str);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }

  private void delete_item(final String subject1) {
      mref.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              Toast.makeText(Details.this, "dbref"+mref, Toast.LENGTH_SHORT).show();
             for(DataSnapshot snapshot:dataSnapshot.getChildren())
              {
                  if(snapshot.child("subject").getValue().equals(subject1))
                  {

                      String itemno=(String)snapshot.child("str").getValue();
                      DatabaseReference dbr=FirebaseDatabase.getInstance().getReference("message").child(itemno);
                      Toast.makeText(Details.this, "dbr "+dbr, Toast.LENGTH_SHORT).show();
                     dbr.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                          @Override
                          public void onComplete(@NonNull Task<Void> task) {
                              if(task.isSuccessful())
                              {
                                  Intent in=new Intent(Details.this,MainActivity.class);
                                  startActivity(in);
                                  Details.this.finish();
                                  Toast.makeText(Details.this, "Successfully deleted "+subject1, Toast.LENGTH_SHORT).show();
                              }
                              else {
                                  Toast.makeText(Details.this, "Not deleted"+subject1, Toast.LENGTH_SHORT).show();
                              }
                          }
                      });


                  }
              }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });
  }

    }



