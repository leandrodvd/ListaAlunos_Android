package br.com.caelum.listaalunos.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caelum.listaalunos.R;

import java.util.List;

import br.com.caelum.listaalunos.modelo.Aluno;

/**
 * Created by android5519 on 12/01/16.
 */
public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos;
    private final Activity activity;

    public ListaAlunosAdapter(Activity activity, List<Aluno> alunos) {
        this.activity = activity;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((Aluno)getItem(position)).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = activity.getLayoutInflater().inflate(R.layout.item,parent,false);
        if(position%2==0){
            v.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
        }
        else{
            v.setBackgroundColor(activity.getResources().getColor(R.color.linha_impar));
        }
        Aluno aluno = (Aluno) alunos.get(position);
        ImageView foto = (ImageView)v.findViewById(R.id.item_foto);
        Bitmap bm ;
        if(aluno.getCaminhoFoto()!=null && !aluno.getCaminhoFoto().isEmpty()){
            bm=BitmapFactory.decodeFile(aluno.getCaminhoFoto());
        }
        else{
            bm= BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image);
        }
        bm= Bitmap.createScaledBitmap(bm, 100, 100, true);

        foto.setImageBitmap(bm);
        TextView nome = (TextView)v.findViewById(R.id.item_nome);
        nome.setText(aluno.getNome());
        return v;
    }
}
