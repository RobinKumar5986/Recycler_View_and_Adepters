package com.example.haider.recyclerviewandadepters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] names ={"A1","B2","C3","D4","E5","F6","G7","H8","I9","J10","K11","L12","M13","N14"};
    int[] images={R.drawable.p1,R.drawable.p4,R.drawable.p5,R.drawable.p6
            ,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,R.drawable.p6
            ,R.drawable.p4,R.drawable.p5,R.drawable.p6,R.drawable.p4,R.drawable.p5};

    String number="9999999999999";
    ArrayList<dataSetter> data=new ArrayList<>();
    FloatingActionButton addContact;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView=findViewById(R.id.recContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        for(int i=0;i<names.length;i++){
            data.add(new dataSetter(images[i] ,names[i], number ) );
        }
        //---------Creating the Adapter----------//
        contactAdapter adapter=new contactAdapter(this,data);
        recyclerView.setAdapter(adapter);
//        //--------Adding Contact----------------//
        addContact=findViewById(R.id.btnFloating);

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update_activity);
                dialog.show();
                dialog.findViewById(R.id.btnAction).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText na=dialog.findViewById(R.id.txtName);
                        String name=na.getText().toString();

                        EditText no=dialog.findViewById(R.id.txtNumber);
                        String num=no.getText().toString();

                        data.add(new dataSetter(images[0],name,num));
                        adapter.notifyItemInserted(data.size()-1);
                        recyclerView.scrollToPosition(data.size()-1);
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}