/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.directmessagebot.utility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author GLB-130
 */
public class ash {

    public HttpsURLConnection conn;

    public String sendPost(String fb, String postParams) throws Exception {

        URL obj = new URL(fb);
        conn = (HttpsURLConnection) obj.openConnection();

        // Acts like a browser
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Host", "instagram.com");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("Accept-Language", "en-GB,en-US;q=0.8,en;q=0.6");
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Referer", "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize/%3Fclient_id%3D9d836570317f4c18bca0db6d2ac38e29%26redirect_uri%3Dhttp%3A//websta.me/%26response_type%3Dcode%26scope%3Dcomments%2Brelationships%2Blikes");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        conn.setRequestProperty("Origin", "https://instagram.com");
        conn.setRequestProperty("X-Client-Data", "CKW2yQEIqbbJAQjEtskBCOuIygEIsJLKAQj9lcoBCLyYygEIn5rKAQ==");
        conn.setRequestProperty("Cookie", "mid=ViDPcwAEAAEZJIsQ69oXS1yIt-xM; sessionid=IGSC17d0985c7f6c412b9000d6a16f61d6090317ad3ce98e17bdddf6ccf5d546d301\n%3A1tQmHvyLs3AnxzEqGmQzN8Vr2POGAwwq%3A%7B%7D; csrftoken=cb5349ce32296df7c3b57ffd73c9713b");

        conn.setDoOutput(true);
        conn.setDoInput(true);
        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.writeBytes(postParams);
            wr.flush();
        }

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + fb);
        System.out.println("Post parameters : " + postParams);
        System.out.println("Response Code : " + responseCode);
        StringBuffer response;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        return response.toString();
    }
}
