package br.com.caelum.listaalunos;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.caelum.listaalunos.R;

import java.util.List;

import br.com.caelum.listaalunos.dao.AlunoDAO;
import br.com.caelum.listaalunos.modelo.Aluno;


public class ListaAluno extends ActionBarActivity {
    private List<Aluno> alunos;
    private ListView lista;
    private  ArrayAdapter<Aluno> adapter;
    

    AlunoDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aluno);

        lista = (ListView) this.findViewById(R.id.lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListaAluno.this,"Item.Clicado" + position,Toast.LENGTH_LONG).show();
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String aluno = ((Aluno) parent.getItemAtPosition(position)).toString();

                Toast.makeText(ListaAluno.this,aluno,Toast.LENGTH_LONG).show();

                return false; //true or false dependendo se quiser mostrar
            }
        });

        Button inserir  = (Button)findViewById(R.id.lista_alunos_floating_button);
        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAluno.this,FormularioActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(lista);

    }
    @Override
    public void onResume(){
        super.onResume();
        carregarLista();
    }

    private void carregarLista() {
       dao = new AlunoDAO(this);
       alunos = dao.getLista();
       adapter = new ArrayAdapter<Aluno>(this,android.R.layout.simple_list_item_1,alunos);
       lista.setAdapter(adapter);
        dao.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_aluno, menu);
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
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Aluno alunoSelecionado = (Aluno) lista.getItemAtPosition(info.position);

        MenuItem  ligar = menu.add("Ligar");
        MenuItem  enviarSMS = menu.add("Enviar SMS");
        MenuItem  acharNoMapa = menu.add("Achar no Mapa");
        MenuItem  navegar = menu.add("Navegar no Site");
        MenuItem  deletar = menu.add("Deletar");


        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AlunoDAO dao = new AlunoDAO(ListaAluno.this);
                dao.deletar(alunoSelecionado);
                dao.close();
                carregarLista();
                return false;

            }
        });
    }

}
