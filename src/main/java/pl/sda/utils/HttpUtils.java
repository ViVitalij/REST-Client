package pl.sda.utils;

import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by m.losK on 2017-03-06.
 */
public class HttpUtils {
    public static String parseResponse(HttpResponse response) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();

        BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(response.getEntity().getContent())));

        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }

        return stringBuffer.toString();
    }
}
