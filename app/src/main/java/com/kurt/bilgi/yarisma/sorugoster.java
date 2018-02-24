package com.kurt.bilgi.yarisma;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import static java.lang.Thread.sleep;

public class sorugoster extends AppCompatActivity {
    Random generator = new Random();
    Button btn1,btn2,btn3,btn4;
    TextView txt,sayac,skor;
    DatabaseReference dff;
    CountDownTimer countDownTimer,count;
    long syc=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorugoster);
        txt=(TextView)findViewById(R.id.soru);
        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);
        sayac=(TextView)findViewById(R.id.sayac);
        skor=(TextView)findViewById(R.id.skor);
        genelislem();
        say();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btn1.getText().toString().equals(so.getDogru())){
                    countDownTimer.cancel();
                    btn1.setBackgroundResource(R.color.dogru);
                    beklet();
                    syc=skr+syc;
                    skor.setText(""+syc);

                }else{
                    btn1.setBackgroundResource(R.color.yanlis);
                    error();

                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn2.getText().toString().equals(so.getDogru())){
                    countDownTimer.cancel();
                    btn2.setBackgroundResource(R.color.dogru);
                    beklet();
                    syc=skr+syc;
                    skor.setText(""+syc);

                }else{
                    btn2.setBackgroundResource(R.color.yanlis);
                    error();
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(btn3.getText().toString().equals(so.getDogru())){
                    countDownTimer.cancel();
                    btn3.setBackgroundResource(R.color.dogru);
                    beklet();
                    syc=skr+syc;
                    skor.setText(""+syc);

                }else{
                    btn3.setBackgroundResource(R.color.yanlis);
                    error();
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn4.getText().toString().equals(so.getDogru())){
                    countDownTimer.cancel();
                    btn4.setBackgroundResource(R.color.dogru);
                    beklet();
                    syc=skr+syc;
                    skor.setText(""+syc);

                }else{
                    btn4.setBackgroundResource(R.color.yanlis);
                    error();
                }
            }
        });
    }

    long skr=0;
    int sayac1=0;
    boolean bool=false;
    int sayi=0;//değişkeni firebasden çeip eşiyleyeceğiz
    public void genelislem(){
        dff=FirebaseDatabase.getInstance().getReference().child("soru_id");

        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    sayi=Integer.parseInt(dataSnapshot.getValue().toString());
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"hata:"+e,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dff.addValueEventListener(valueEventListener);


     int k= generator.nextInt(sayi+1);
        göster(k);
        sayac1=sayac1+1;

        countDownTimer=new CountDownTimer(11000,1000) {
            @Override
            public void onTick(long l) {
                skr=l/1000;
                sayac.setText(""+l/1000);
            }

            @Override
            public void onFinish() {
                // zaman bittiği
                error();
            }
        };

        if(bool){
            countDownTimer.start();
        }
        else{
            bool=true;
        }

    }

    soru so;
    public void göster(int id){
        dff=FirebaseDatabase.getInstance().getReference().child("sorular").child(""+id);//soruların olduğu dosya
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    so=dataSnapshot.getValue(soru.class);

                    if(so!=null){
                        soruüret();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"intenetinizi kontrol edin",Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                     Toast.makeText(getApplicationContext(),"hata:"+e,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dff.addValueEventListener(valueEventListener);

    }
    public void soruüret(){
        int b=generator.nextInt(4);
       txt.setText(so.getSoru());

        switch (b){

            case 0:
                btn1.setText(so.getDogru());
                btn2.setText(so.getYanlis1());
                btn3.setText(so.getYanlis2());
                btn4.setText(so.getYanlis3());

                break;
            case 1:
                btn1.setText(so.getYanlis1());
                btn2.setText(so.getDogru());
                btn3.setText(so.getYanlis2());
                btn4.setText(so.getYanlis3());
                break;
            case 2:
                btn1.setText(so.getYanlis2());
                btn2.setText(so.getYanlis1());
                btn3.setText(so.getDogru());
                btn4.setText(so.getYanlis3());
                break;
            case 3:
                btn1.setText(so.getYanlis3());
                btn2.setText(so.getYanlis1());
                btn3.setText(so.getYanlis2());
                btn4.setText(so.getDogru());
                break;
        }
    }
    public void error() {

       /* final Dialog dialog = new Dialog(getApplicationContext());
        dialog.setContentView(R.layout.error);
        dialog.setTitle("!!!!");
        Button kaydet=dialog.findViewById(R.id.kaydet);
        Button nokaydet=dialog.findViewById(R.id.nokayit);
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kaydet button işlevi
                finish();

                startActivity(new Intent(getApplicationContext(),ScorKaydet.class).putExtra("scor",Integer.parseInt(skor.getText().toString())));
                dialog.dismiss();

            }
        });
        nokaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kaydetme işlemiini yapması gereken işlem
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                dialog.dismiss();
            }
        });
        dialog.show();
        countDownTimer.cancel();
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });*/

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(sorugoster.this);
        View mView = getLayoutInflater().inflate(R.layout.error, null);
        Button kayıt=(Button)mView.findViewById(R.id.kaydet);
        Button kayıdetme=(Button)mView.findViewById(R.id.nokayit);
        TextView dgr=(TextView)mView.findViewById(R.id.textView9);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();

        kayıt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(),ScorKaydet.class).putExtra("scor",Integer.parseInt(skor.getText().toString())));
                overridePendingTransition(R.anim.right,R.anim.out_left);
                dialog.dismiss();
            }
        });
        kayıdetme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                overridePendingTransition(R.anim.left,R.anim.out_right);
                dialog.dismiss();

            }
        });
        dialog.setCancelable(true);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
               // finish();
               //startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        countDownTimer.cancel();
        dgr.setText(so.getDogru());

            dialog.show();


    }
    public void say(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(sorugoster.this);
        View mView = getLayoutInflater().inflate(R.layout.geri, null);
        final TextView textView=(TextView)mView.findViewById(R.id.say);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        count=new CountDownTimer(4000,1000) {
            @Override
            public void onTick(long l) {
                textView.setText(""+l/1000);
            }

            @Override
            public void onFinish() {
                countDownTimer.start();
                dialog.dismiss();
            }
        };
        dialog.setCancelable(false);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                //finish();
                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        count.start();
    }
    public void beklet(){

        final Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btn1.setBackgroundResource(R.drawable.buton);
                            btn2.setBackgroundResource(R.drawable.buton);
                            btn3.setBackgroundResource(R.drawable.buton);
                            btn4.setBackgroundResource(R.drawable.buton);
                           // int k= generator.nextInt(sayi+1);
                           // if(sayac1==dizi.length){
                           //
                           //     dizi[sayac1]=k;
                           //     genelislem();
                           // }
                           // else {
                                genelislem();
                          //  }
                        }
                    });
                }
            }
        });
        thread.start();
    }

    @Override
    public void onBackPressed() {
        countDownTimer.cancel();
        finish();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        overridePendingTransition(R.anim.left,R.anim.out_right);
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }
}
