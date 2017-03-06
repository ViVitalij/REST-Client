package pl.sda.myPostman;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import pl.sda.utils.HttpUtils;

import java.io.IOException;

/**
 * Created by m.losK on 2017-03-06.
 */
public class Sender {

    public static String createUser(String url, String request) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);

        post.setEntity(new StringEntity(request));

        HttpResponse response = httpClient.execute(post);
        return HttpUtils.parseResponse(response);
    }

    public static String getUser(String url, String id) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url + "?id=" + id);

        HttpResponse response = httpClient.execute(get);
        return HttpUtils.parseResponse(response);
    }
}
