package com.example.zedin.entregaunodes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

public class M11ShareActivity extends AppCompatActivity {

    ImageButton sugerencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m11_share);
        Spinner spinner = (Spinner) findViewById(R.id.spinner_comida);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.arreglo_comida, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        sugerencia = (ImageButton)findViewById(R.id.sig);
        sugerencia.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               Intent siguiente = new Intent(M11ShareActivity.this, M11SugerenActivity.class);
               startActivity(siguiente);

           }
        });

    }
}
