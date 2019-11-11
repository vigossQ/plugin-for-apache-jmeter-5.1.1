package org.apache.jmeter.autotest.util;

import org.sonarsource.scanner.api.internal.shaded.okhttp.*;
import org.sonarsource.scanner.api.internal.shaded.okhttp.internal.http.HttpMethod;

import java.io.IOException;

/**
 * @author vigoss
 * @date 2019/11/5
 */
public class HttpUtil {

    public static String sendRequest(String url, String method, String bodyJson) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        RequestBody body =
                HttpMethod.permitsRequestBody(method)
                        ? RequestBody.create(MediaType.parse("application/json; charset=utf-8"), bodyJson)
                        : null;
        final Request request =
                new Request.Builder()
                        .url(url)
                        .method(method, body)
                        .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }
}
