package com.example.zedin.entregaunodes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class M11MenuCalorias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m11_menu_calorias);
    }

    public void irComida(View view){
        Intent intent = new Intent(this, M11ShareActivity.class);
        startActivity(intent);
    }

    public void irHistorial(View view){
        Intent intent = new Intent(this, M11HistorialActivity.class);
        startActivity(intent);
    }
    public void irSugerencia(View view){
        Intent intent = new Intent(this, M11SugerenActivity.class);
        startActivity(intent);
    }
}
