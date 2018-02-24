package com.kurt.bilgi.yarisma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ScorKaydet extends AppCompatActivity {
    EditText klc;
    Button btn,btn2,btn4;
    TextView txt;
    int scor;
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference df=db.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scor_kaydet);
        klc=(EditText)findViewById(R.id.editText);
        btn=(Button)findViewById(R.id.button2);
        txt=(TextView)findViewById(R.id.textView7);
        btn2=(Button)findViewById(R.id.button3);
        btn4=(Button)findViewById(R.id.button4);
        Intent intent = getIntent();
       scor = intent.getIntExtra("scor", 0);
        txt.setText(""+scor);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!klc.getText().toString().isEmpty()){
                String k=df.push().getKey();


               // df.child("kullanıcılar").child(klc.getText().toString()).setValue(scor);
                df.child("kullanıcılar").child(k).child("k_adi").setValue(klc.getText().toString());
                df.child("kullanıcılar").child(k).child("scor").setValue(scor);
                klc.setText("");
                Toast.makeText(getApplicationContext(),"KAYDEDİLDİ",Toast.LENGTH_LONG).show();}
                else{

                    Toast.makeText(getApplicationContext(),"KULLANICI ADI GİRİNİZ",Toast.LENGTH_LONG).show();

                }


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(),sorugoster.class));
                overridePendingTransition(R.anim.left,R.anim.out_right);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                overridePendingTransition(R.anim.left,R.anim.out_right);

            }
        });




    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        overridePendingTransition(R.anim.left,R.anim.out_right);
        super.onBackPressed();
    }
}
