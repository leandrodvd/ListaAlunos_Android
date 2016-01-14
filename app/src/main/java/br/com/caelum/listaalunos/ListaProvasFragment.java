package br.com.caelum.listaalunos;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.caelum.listaalunos.R;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.listaalunos.modelo.Prova;

/**
 * Created by android5519 on 13/01/16.
 */
public class ListaProvasFragment extends Fragment {
    private ListView listViewProvas ;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layoutProvas = inflater.inflate(R.layout.fragment_lista_provas,container,false);
        this.listViewProvas =(ListView)layoutProvas.findViewById(R.id.lista_provas_listview);

        Prova prova1 = new Prova("20/06/2015","Matemática");
        prova1.setTopicos(Arrays.asList("Álgebra linear", "Cálculo", "Estatística"));

        Prova prova2 = new Prova("25/07/2015","Português");
        prova1.setTopicos(Arrays.asList("Complemento Nominal", "Oração Subordinada", "Análise Sintática"));

        List<Prova> provas = Arrays.asList(prova1,prova2);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(getActivity(),android.R.layout.simple_list_item_1,provas);
        listViewProvas.setAdapter(adapter);

        listViewProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova selecionada = (Prova) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(),"Prova selecionada: "+selecionada,Toast.LENGTH_LONG).show();
            }
        });

        return layoutProvas;
    }
}
