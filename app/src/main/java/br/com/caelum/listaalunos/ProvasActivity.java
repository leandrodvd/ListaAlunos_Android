package br.com.caelum.listaalunos;



import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import br.com.caelum.listaalunos.R;

import br.com.caelum.listaalunos.modelo.Prova;

public class ProvasActivity extends ActionBarActivity {
    private Prova prova;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(isTablet()){
            transaction.replace(R.id.provas_lista,new ListaProvasFragment());
            transaction.replace(R.id.provas_detalhes,new DetalhesProvaFragment());
        }else{
            transaction.replace(R.id.provas_view,new ListaProvasFragment());
        }

        transaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_provas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("prova" ,this.prova);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        this.prova = (Prova) savedInstanceState.getSerializable("prova");
        if(prova!=null){
            selecionaProva(prova);
        }

    }

    private boolean isTablet(){
        return getResources().getBoolean(R.bool.isTablet);
    }

    public void selecionaProva(Prova selecionada) {
        this.prova = selecionada;
        FragmentManager manager = getSupportFragmentManager();
        if(isTablet()){
            DetalhesProvaFragment  detalhesProva  = (DetalhesProvaFragment)manager.findFragmentById(R.id.provas_detalhes);
            detalhesProva.populaCamposComDados(selecionada);
        }
        else{
            Bundle argumentos = new Bundle();
            argumentos.putSerializable("prova",selecionada);

            DetalhesProvaFragment detalhesProva = new DetalhesProvaFragment();
            detalhesProva.setArguments(argumentos);

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.provas_view,detalhesProva);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
