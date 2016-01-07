package br.com.caelum.listaalunos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.caelum.listaalunos.modelo.Aluno;

/**
 * Created by android5519 on 07/01/16.
 */
public class AlunoDAO extends SQLiteOpenHelper{

    private static final int VERSAO = 1;
    private static final String TABELA = "Alunos";
    private static final String DATABASE = " CadastroCaelum";


    public AlunoDAO(Context context){
        super(context,"DATABASE",null,VERSAO);

    }

    public void onCreate(SQLiteDatabase database){
        String ddl = "CREATE TABLE " + TABELA
                        + " (id INTEGER PRIMARY KEY, "
                        + " nome TEXT NOT NULL, "
                        + " telefone TEXT, "
                        + " endereco TEXT, "
                        + " site TEXT, "
                        + " nota REAL); ";
        database.execSQL(ddl);
    }

    public void onUpgrade(SQLiteDatabase database,
                           int versaoAntiga, int versaoNova){
        String sql = " DROP TABLE IF EXISTS " + TABELA;
        database.execSQL(sql);
        onCreate(database);
    }

    public void insere(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome",aluno.getNome());
        values.put("telefone",aluno.getTelefone());
        values.put("endereco",aluno.getEndereco());
        values.put("site",aluno.getSite());
        values.put("nota",aluno.getNota());

        getWritableDatabase().insert(TABELA,null,values);

    }

}

