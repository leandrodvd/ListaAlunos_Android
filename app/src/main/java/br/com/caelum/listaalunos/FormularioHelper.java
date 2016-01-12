package br.com.caelum.listaalunos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.example.caelum.listaalunos.R;

import br.com.caelum.listaalunos.modelo.Aluno;

/**
 * Created by android5519 on 06/01/16.
 */
public class FormularioHelper {

    private EditText nome;
    private EditText endereco;
    private EditText site;
    private EditText telefone;
    private RatingBar nota;
    private ImageView foto;
    private Button fotoButton;
    private Aluno aluno;


    public FormularioHelper(FormularioActivity activity) {
        this.nome=(EditText)activity.findViewById(R.id.formulario_nome);
        this.endereco=(EditText)activity.findViewById(R.id.formulario_endereco);
        this.site=(EditText)activity.findViewById(R.id.formulario_site);
        this.telefone=(EditText)activity.findViewById(R.id.formulario_telefone);
        this.nota=(RatingBar)activity.findViewById(R.id.formulario_nota);
        this.foto=(ImageView)activity.findViewById(R.id.formulario_foto);
        this.fotoButton=(Button)activity.findViewById(R.id.formulario_foto_button);
        this.aluno=new Aluno();
    }

    public Button getFotoButton(){
        return this.fotoButton;
    }


    public Aluno pegaAlunoDoFormulario(){
        aluno.setNome(this.nome.getText().toString());
        aluno.setEndereco(this.endereco.getText().toString());
        aluno.setSite(this.site.getText().toString());
        aluno.setTelefone(this.telefone.getText().toString());
        aluno.setNota(Double.valueOf(this.nota.getProgress()));
       // if (this.foto.getTag()!=null){
            aluno.setCaminhoFoto((String)this.foto.getTag());
       // }

        return aluno;
    }

    public boolean temNome() {
        return ! this.nome.getText().toString().equals("");
    }

    public void insereDadosNoFormulario(Aluno aluno) {
        this.aluno=aluno;
        this.nome.setText(aluno.getNome());
        this.endereco.setText(aluno.getEndereco());
        this.site.setText(aluno.getSite());
        this.telefone.setText(aluno.getTelefone());
        this.nota.setProgress(aluno.getNota().intValue());
        if(aluno.getCaminhoFoto()!=null){
            carregaImagem(aluno.getCaminhoFoto());
        }

    }

    public void carregaImagem(String localArquivoFoto) {
        Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
        Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto,imagemFoto.getWidth(),
                300,true);
        foto.setImageBitmap(imagemFotoReduzida);
        foto.setTag(localArquivoFoto);
        foto.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}
