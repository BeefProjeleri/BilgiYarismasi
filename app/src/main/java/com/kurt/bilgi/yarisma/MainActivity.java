package com.kurt.bilgi.yarisma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Intent itn,itn2;
    Button gonder,basla,iyi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gonder=(Button)findViewById(R.id.gonder);
        basla=(Button)findViewById(R.id.basla);
        iyi=(Button)findViewById(R.id.button6);
        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itn=new Intent(getApplicationContext(),sorugonder.class);
                startActivity(itn);
                overridePendingTransition(R.anim.right,R.anim.out_left);

            }
        });
        basla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                itn2=new Intent(getApplicationContext(),sorugoster.class);
                startActivity(itn2);
                overridePendingTransition(R.anim.right,R.anim.out_left);

            }
        });
        iyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),EnIyiler.class));
                overridePendingTransition(R.anim.right,R.anim.out_left);

            }
        });
    }
}
