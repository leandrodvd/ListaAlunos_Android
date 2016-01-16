package br.com.caelum.listaalunos.util;

import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;

/**
 * Created by android5519 on 15/01/16.
 */
public class Configurador implements GoogleApiClient.ConnectionCallbacks{
    private AtualizadorDeLocalizacao atualizadorDeLocalizacao;


    public Configurador(AtualizadorDeLocalizacao atualizadorDeLocalizacao) {
        this.atualizadorDeLocalizacao = atualizadorDeLocalizacao;
    }

    @Override
    public void onConnected(Bundle bundle) {
        LocationRequest request = LocationRequest.create();
        request.setInterval(2000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setSmallestDisplacement(50);
        atualizadorDeLocalizacao.inicia(request);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
