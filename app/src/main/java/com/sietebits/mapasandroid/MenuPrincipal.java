package com.sietebits.mapasandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MenuPrincipal extends AppCompatActivity implements View.OnClickListener {

    private CardView cvTehotihuacan, cvLatino, cvCastillo, cvRevolucion;
    private RadioGroup radio_group;
    private String tipoMapa = "mapa";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        cvTehotihuacan = (CardView)findViewById(R.id.cvTehotihuacan);
        cvLatino = (CardView)findViewById(R.id.cvLatino);
        cvCastillo = (CardView)findViewById(R.id.cvCastillo);
        cvRevolucion = (CardView)findViewById(R.id.cvRevolucion);
        radio_group = (RadioGroup) findViewById(R.id.radio_group);

        cvTehotihuacan.setOnClickListener(this);
        cvLatino.setOnClickListener(this);
        cvCastillo.setOnClickListener(this);
        cvRevolucion.setOnClickListener(this);

        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radio_mapa){
                    tipoMapa = "mapa";
                } else  if(checkedId == R.id.radio_croquis) {
                    tipoMapa = "croquis";
                } else  if(checkedId == R.id.radio_hibrido) {
                    tipoMapa = "hibrido";
                } else  if(checkedId == R.id.radio_tierra) {
                    tipoMapa = "tierra";
                }
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        if(toolbar != null)
        {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onClick(View v) {
        Intent mapaIntent = new Intent(this, MapsActivity.class);
        String lugar = "";
        if(v == cvTehotihuacan){
            Toast.makeText(this, getResources().getString(R.string.IrTeotihuacan), Toast.LENGTH_SHORT).show();
            lugar = "Teo";

        }else if(v == cvLatino){
            Toast.makeText(this, getResources().getString(R.string.IrTorreLatino), Toast.LENGTH_SHORT).show();
            lugar = "Lat";

        }else if(v == cvCastillo){
            Toast.makeText(this, getResources().getString(R.string.IrCastillo), Toast.LENGTH_SHORT).show();
            lugar = "Cas";

        }else if(v == cvRevolucion){
            Toast.makeText(this, getResources().getString(R.string.IrMonumentoRev), Toast.LENGTH_SHORT).show();
            lugar = "Rev";
        }
        mapaIntent.putExtra("mapa", tipoMapa);
        mapaIntent.putExtra("lugar", lugar);
        startActivity(mapaIntent);
    }
}
