package com.sietebits.mapasandroid;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String mapa, lugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mapa = getIntent().getExtras().getString("mapa");
        lugar = getIntent().getExtras().getString("lugar");

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng place = new LatLng(obtenerLatitud(), obtenerLongitud());

        //-- Marcador


        if(mapa.equals("croquis"))
        {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 16));
            mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_name))
                    .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                    .position(place)

                    .snippet(snippetMessage()));
        }
        else{
            mMap.addMarker(new MarkerOptions()
                    .title(lugar())
                    .position(place)
                    .snippet(snippetMessage())
                    .icon(BitmapDescriptorFactory.defaultMarker(color())));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 16));
            tipoDeMapa(mMap);
        }
    }

    private String snippetMessage() {
        String place = "";
        switch (lugar){
            case "Teo":
                place = "Lugar donde los hombres se convierten\n en dioses";
                break;
            case "Lat":
                place = "Altura 204 m  con  44 plantas";
                break;
            case "Rev":
                place = "Conmemoración Revolución mexicana";
                break;
            case "Cas":
                place = "Único Castillo Real en América";
                break;
        }

        return place;
    }

    private float color(){
        int aa = (int)(Math.random()*5 + 1);

        switch (aa)
        {
            case 1:
                return BitmapDescriptorFactory.HUE_AZURE;
            case 2:
                return BitmapDescriptorFactory.HUE_BLUE;
            case 3:
                return BitmapDescriptorFactory.HUE_ROSE;
            case 4:
                return BitmapDescriptorFactory.HUE_YELLOW;
            case 5:
                return BitmapDescriptorFactory.HUE_ORANGE;
        }
        return BitmapDescriptorFactory.HUE_CYAN;
    }

    private void tipoDeMapa(GoogleMap mMap){
        switch (mapa){
            case "mapa":
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case "tierra":
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case "hibrido":
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            default:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
        }
    }

    private String lugar() {
        String place = "";
        switch (lugar){
            case "Teo":
                place = "Teotihuacan";
                break;
            case "Lat":
                place = "Torre Latinoamericana";
                break;
            case "Rev":
                place = "Monumento a la revolución mexicana";
                break;
            case "Cas":
                place = "Castillo de Chapultepec";
                break;
        }

        return place;
    }

    private float obtenerLatitud(){
        float lat = 0;
        switch (lugar){
            case "Teo":
                lat = 19.693483f;
                break;
            case "Lat":
                lat = 19.433720f;
                break;
            case "Rev":
                lat = 19.4361024f;
                break;
            case "Cas":
                lat = 19.420151f;
                break;
        }
        return lat;
    }
    private float obtenerLongitud(){
        float lon = 0;
        switch (lugar){
            case "Teo":
                lon = -98.843127f;
                break;
            case "Lat":
                lon = -99.139948f;
                break;
            case "Rev":
                lon = -99.156046f;
                break;
            case "Cas":
                lon = -99.182557f;
                break;
        }
        return lon;
    }
}
