package br.com.caelum.listaalunos;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.com.caelum.listaalunos.R;

import br.com.caelum.listaalunos.modelo.Prova;

/**
 * Created by android5519 on 14/01/16.
 */
public class DetalhesProvaFragment extends Fragment {
    private Prova prova;
    TextView materia;
    TextView data;
    ListView topicos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_prova,parent,false);
        if(getArguments()!=null){
            this.prova = (Prova) getArguments().getSerializable("prova");
        }
        buscaComponentes(view);
        populaCamposComDados(prova);

        return view;
    }

    private void buscaComponentes(View layout){
        this.materia=(TextView) layout.findViewById(R.id.detalhe_prova_materia);
        this.data=(TextView) layout.findViewById(R.id.detalhe_prova_data);
        this.topicos = (ListView)layout.findViewById(R.id.detalhe_prova_topicos);
    }


    public void populaCamposComDados (Prova prova){
        if(prova != null){
            this.materia.setText(prova.getMateria());
            this.data.setText(prova.getData());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1,prova.getTopicos());
            this.topicos.setAdapter(adapter);
        }
    }
}
