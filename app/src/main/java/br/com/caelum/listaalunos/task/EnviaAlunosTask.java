package br.com.caelum.listaalunos.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.listaalunos.converter.AlunoConverter;
import br.com.caelum.listaalunos.dao.AlunoDAO;
import br.com.caelum.listaalunos.modelo.Aluno;
import br.com.caelum.listaalunos.support.WebClient;

/**
 * Created by android5519 on 13/01/16.
 */
public class EnviaAlunosTask extends AsyncTask<Object,Object,String>{
    private Context context;
    ProgressDialog pd ;
    public EnviaAlunosTask(Context context) {
        this.context=context;
    }

    @Override
    protected String doInBackground(Object... params) {
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos;
        alunos = dao.getLista();
        dao.close();
        String json = new AlunoConverter().toJSON(alunos);
        WebClient client = new WebClient();
        String resp = client.post(json);
        return resp;
    }

    @Override
    protected void onPreExecute() {
        pd =ProgressDialog.show(context,"Aguarde...","Envio de Dados para web",true,true);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context,result,Toast.LENGTH_SHORT).show();
        pd.dismiss();

    }
}
