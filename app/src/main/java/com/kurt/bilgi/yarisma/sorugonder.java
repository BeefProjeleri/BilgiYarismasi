package com.kurt.bilgi.yarisma;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class sorugonder extends AppCompatActivity {

    Button gndr;
    TextView txt;
    EditText soru,dogru,yanlis1,yanlis2,yanlis3,kalanyer;
    String soruu,dogruu,yanlis11,yanlis22,yanlis33;
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference df=db.getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorugonder);
        txt=(TextView)findViewById(R.id.textView);
        gndr=(Button)findViewById(R.id.gnder);
        soru=(EditText)findViewById(R.id.soru);
        dogru=(EditText)findViewById(R.id.dogru);
        yanlis1=(EditText)findViewById(R.id.yanlis1);
        yanlis2=(EditText)findViewById(R.id.yanlis2);
        yanlis3=(EditText)findViewById(R.id.yanlis3);

        gndr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soruu=soru.getText().toString();
                dogruu=dogru.getText().toString();
                yanlis11=yanlis1.getText().toString();
                yanlis22=yanlis2.getText().toString();
                yanlis33=yanlis3.getText().toString();
                if(soruu.isEmpty() || dogruu.isEmpty() || yanlis11.isEmpty() || yanlis22.isEmpty() || yanlis33.isEmpty()){
                    Toast.makeText(getApplicationContext(),"tamamını doldur",Toast.LENGTH_LONG).show();
                }else {
                    yaz(soruu, dogruu, yanlis11, yanlis22, yanlis33);

                   if(!soruu.isEmpty() || !dogruu.isEmpty() || !yanlis11.isEmpty() || !yanlis22.isEmpty() || !yanlis33.isEmpty())
                   {soru.setText("");
                    dogru.setText("");
                    yanlis1.setText("");
                    yanlis2.setText("");
                    yanlis3.setText("");
                   }
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    public void yaz(String soru, String dogru, String yanlis1, String yanlis2, String yanlis3) {
       soru sr=new soru(soru,dogru,yanlis1,yanlis2,yanlis3);

        String key=df.push().getKey();
       df.child("gönderilen sorular").child(key).setValue(sr);


   }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left,R.anim.out_right);
    }
}
