package br.com.caelum.listaalunos;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.caelum.listaalunos.dao.AlunoDAO;
import br.com.caelum.listaalunos.modelo.Aluno;
import br.com.caelum.listaalunos.util.AtualizadorDeLocalizacao;
import br.com.caelum.listaalunos.util.Localizador;

/**
 * Created by android5519 on 15/01/16.
 */
public class MapaFragment extends SupportMapFragment {
    GoogleMap mapa;
    @Override
    public void onResume() {
        super.onResume();
        mapa=getMap();
        Localizador localizador = new Localizador(getActivity());
        LatLng localCaelum = localizador.getCoordenada("Rua Vergueiro 3158 Vila Mariana");
        mapa.addMarker(new MarkerOptions().title("Caelum").position(localCaelum));
        this.centralizaNo(localCaelum);
        AlunoDAO dao = new AlunoDAO(getActivity());
        List<Aluno> alunos = dao.getLista();
        for (Aluno aluno:alunos){
            LatLng localAluno = localizador.getCoordenada(aluno.getEndereco());
            MarkerOptions marcador = new MarkerOptions()
                    .title(aluno.getNome())
                    .position(localAluno);
            mapa.addMarker(marcador);

        }

        AtualizadorDeLocalizacao atualizador = new AtualizadorDeLocalizacao(getActivity(),this);

    }

    public void centralizaNo(LatLng local) {
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(local, 11);
        mapa.moveCamera(camera);

    }
}
