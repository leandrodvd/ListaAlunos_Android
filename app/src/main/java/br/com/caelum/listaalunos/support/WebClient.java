package br.com.caelum.listaalunos.support;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by android5519 on 13/01/16.
 */
public class WebClient {
    private static final String URL = "http://www.caelum.com.br/mobile";

    public String post(String json){
        try {
            HttpPost post = new HttpPost(URL);
            post.setEntity(new StringEntity(json));
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type","application/json");

            DefaultHttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(post);
            String jsonDeResposta = EntityUtils.toString(response.getEntity());
            return jsonDeResposta;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        } catch (ClientProtocolException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;

    }
}
