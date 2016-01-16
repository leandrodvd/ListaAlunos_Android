package br.com.caelum.listaalunos.util;

import android.content.ContentValues;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by android5519 on 15/01/16.
 */
public class Localizador {
    private Context context;
    private Geocoder geo;
    public Localizador(Context context) {
        this.context=context;
        this.geo =new Geocoder(context);
    }

    public LatLng getCoordenada(String enderecoStr){
        try {
            List<Address> enderecos = geo.getFromLocationName(enderecoStr, 1);
            if(enderecos!=null){
                Address endereco = enderecos.get(0);
                return new LatLng(endereco.getLatitude(),endereco.getLongitude());
            }
            else{
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


}
