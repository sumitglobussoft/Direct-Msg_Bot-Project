/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.directmessagebot.crawler;

import com.directmessagebot.utility.FetchSource;
import com.directmessagebot.utility.PostFetchSource;
import com.directmessagebot.utility.fetchGooglePageWithProxy;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author GLB-130
 */
public class LoginTest {

    PostFetchSource objPostFetchSource = new PostFetchSource();
    FetchSource objFetchSource = new FetchSource();

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, Exception {
        LoginTest objLogin = new LoginTest();
        objLogin.LoginbyWebsta("himanshumoriglobus", "456321789");
    }

    public void LoginbyWebsta(String username, String password) throws URISyntaxException, IOException, InterruptedException, Exception {

        String firstUrl = "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize%3Fclient_id%3Dd9494686198d4dfeb954979a3e270e5e%26redirect_uri%3Dhttp%253A%252F%252Ficonosquare.com%26response_type%3Dcode%26scope%3Dlikes%2Bcomments%2Brelationships";

        Document doc = Jsoup.parse(fetchGooglePageWithProxy.fetchPageSourcefromClientGoogle(new URI(firstUrl)));

        Elements e = doc.select("input[type=hidden]");

        String a[] = e.toString().split("value=\"");
        String token = a[1].replace("\" />", "");
        System.out.println("Token : " + token);

        String postdata = token.trim() + "&" + username + "&" + password;
        System.out.println("Post Data : " + postdata);

        String respone = objFetchSource.fetchPageSourceWithProxyPostForInstagramLogin("https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize%3Fclient_id%3Dd9494686198d4dfeb954979a3e270e5e%26redirect_uri%3Dhttp%253A%252F%252Ficonosquare.com%26response_type%3Dcode%26scope%3Dlikes%2Bcomments%2Brelationships", postdata);

        System.out.println("------------------------Get Response-----------------------" + respone);

    }

}
