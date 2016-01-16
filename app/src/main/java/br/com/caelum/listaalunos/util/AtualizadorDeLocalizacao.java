package br.com.caelum.listaalunos.util;

import android.content.Context;
import android.location.Location;
import com.google.android.gms.location.LocationListener;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.d;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.util.concurrent.TimeUnit;

import br.com.caelum.listaalunos.MapaFragment;

/**
 * Created by android5519 on 15/01/16.
 */
public class AtualizadorDeLocalizacao implements LocationListener {

    private GoogleApiClient client;
    private MapaFragment mapaFragment;

    public AtualizadorDeLocalizacao(Context context,MapaFragment mapaFragment) {
        Configurador config = new Configurador(this);
        this.client = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(config)
                .build();
        this.client.connect();

        this.mapaFragment =  mapaFragment;

    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        LatLng local = new LatLng(latitude,longitude);

        mapaFragment.centralizaNo(local);

    }



    public void inicia(LocationRequest request) {
         LocationServices.FusedLocationApi.requestLocationUpdates(client, request, (LocationListener) this);
    }

    public void cancela(){
        LocationServices.FusedLocationApi.removeLocationUpdates(client,this);
        this.client.disconnect();
    }
}
