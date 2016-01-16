package br.com.caelum.listaalunos.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.caelum.listaalunos.R;

import br.com.caelum.listaalunos.dao.AlunoDAO;

/**
 * Created by android5519 on 12/01/16.
 */
public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent data) {


        Bundle bundle = data.getExtras();
        Object[] mensagens = (Object[]) bundle.get("pdus");

        byte[] mensagem = (byte[]) mensagens[0];
        SmsMessage sms = SmsMessage.createFromPdu(mensagem);

        String telefone = sms.getDisplayOriginatingAddress();
        AlunoDAO dao = new AlunoDAO(context);
        if (dao.isAluno(telefone)) {
            Toast.makeText(context, "Chegou SMS " + telefone, Toast.LENGTH_LONG).show();
            MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
            mp.start();
        }
    }
}
