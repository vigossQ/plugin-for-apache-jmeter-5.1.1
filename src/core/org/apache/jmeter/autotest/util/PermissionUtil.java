package org.apache.jmeter.autotest.util;

import java.io.IOException;

/**
 * @author vigoss
 * @date 2019/11/11
 */
public class PermissionUtil {

    public static String checkUser(String username, String password, String projectId) {
        String url = "http://172.16.33.35:8884/poseidon/doRequest";
        String bodyJson = "{\n" +
                "    \"appKey\": \"cd744f8a-2d26-4a8d-9000-54f15225d625\",\n" +
                "    \"appSecret\": \"505cbeba-2ae4-4086-a923-473ddce13841\",\n" +
                "    \"url\": \"https://beta-apim-cn4.eniot.io/app-portal/api/v1/login\",\n" +
                "    \"method\": \"POST\",\n" +
                "    \"requestBody\": {\n" +
                "        \"account\": \"appPortal_cuicui\",\n" +
                "        \"password\": \"Test1234\"\n" +
                "    },\n" +
                "    \"headers\": {\n" +
                "        \"Content-Type\": \"application/json\"\n" +
                "    }\n" +
                "}";
        try {
            String post = HttpUtil.sendRequest(url, "POST", bodyJson);
            System.out.println(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
