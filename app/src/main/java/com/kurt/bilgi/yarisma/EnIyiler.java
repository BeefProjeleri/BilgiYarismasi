package com.kurt.bilgi.yarisma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Thread.sleep;


public class EnIyiler extends AppCompatActivity {

    FirebaseDatabase db=FirebaseDatabase.getInstance();
    Query dff=db.getReference();
    TextView txt;
    String bilgiler,kullanici_adi,puan;
    int sayac=10;
    ListView list;
    List<String> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_iyiler);
        txt=(TextView)findViewById(R.id.textView6);
        list=(ListView)findViewById(R.id.list);
        dff=  FirebaseDatabase.getInstance().getReference().child("kullanıcılar").orderByChild("scor").limitToLast(10);

        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    for(DataSnapshot post:dataSnapshot.getChildren()){

                        kullanici_adi=post.child("k_adi").getValue().toString();
                        puan=post.child("scor").getValue().toString();

                        bilgiler=sayac+"-"+kullanici_adi+"       "+puan;
                        arrayList.add(bilgiler);
                        sayac=sayac-1;
                    }
                    Collections.reverse(arrayList);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"hata:"+e,Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dff.addValueEventListener(valueEventListener);





        final Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
                            list.setAdapter(adapter);
                        }
                    });
                }
            }
        });
        thread.start();





    }

    @Override
    public void onBackPressed() {


        super.onBackPressed();
        overridePendingTransition(R.anim.left,R.anim.out_right);
    }
}
