package br.com.caelum.listaalunos;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.caelum.listaalunos.R;

import java.io.File;

import br.com.caelum.listaalunos.dao.AlunoDAO;
import br.com.caelum.listaalunos.modelo.Aluno;


public class FormularioActivity extends ActionBarActivity {

    public static final String ALUNO_SELECIONADO = "alunoSelecionado";
    private FormularioHelper helper;
    private String localArquivoFoto;
    private int REQUEST_CODE_TIRA_FOTO = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        this.helper=new FormularioHelper(this);

        Intent intent = this.getIntent();
        Aluno aluno = (Aluno)intent.getSerializableExtra(this.ALUNO_SELECIONADO);
        if(aluno!=null){
            helper.insereDadosNoFormulario(aluno);
        }

        Button foto = helper.getFotoButton();
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localArquivoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis()+".jpg";
                Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Uri localFoto = Uri.fromFile(new File(localArquivoFoto));
                irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT,localFoto);
                startActivityForResult(irParaCamera,REQUEST_CODE_TIRA_FOTO);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE_TIRA_FOTO) {
            helper.carregaImagem(this.localArquivoFoto);
        }
        else{
            this.localArquivoFoto=null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulario, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_formulario_ok) {
            if(helper.temNome()){
                Aluno aluno = helper.pegaAlunoDoFormulario();
                AlunoDAO dao = new AlunoDAO(this);
                if(aluno.getId()==null){
                    dao.insere(aluno);
                }else{
                    dao.alterar(aluno);
                }
                dao.close();

                Toast.makeText(this,"Aluno "+aluno.getNome()+ " salvo",Toast.LENGTH_LONG).show();
                finish();
            }
            else{
                Toast.makeText(this,"Preencha o nome!",Toast.LENGTH_LONG).show();
            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
