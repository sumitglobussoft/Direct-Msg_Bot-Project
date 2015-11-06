/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.directmessagebot.crawler;

import com.directmessagebot.entity.AccountManager;
import static com.directmessagebot.ui.DirectMessagePage.logger2textArea;
import com.directmessagebot.utility.PostFetchSource;
import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author GLB-130
 */
public class InstagramCrawler {
    PostFetchSource objPostFetchSource = new PostFetchSource();
    
    public int LoginbyInconosquare(AccountManager objAccountManager) throws URISyntaxException, IOException, InterruptedException, Exception {

        String username = objAccountManager.getUsername();
        String password = objAccountManager.getPassword();
        int loggedInStatus = 0;

        try {

            String firstUrl = "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize%3Fclient_id%3Dd9494686198d4dfeb954979a3e270e5e%26redirect_uri%3Dhttp%253A%252F%252Ficonosquare.com%26response_type%3Dcode%26scope%3Dlikes%2Bcomments%2Brelationships";

            String postdata = "";
            
            String CookieData = " mid=VjdGcAAEAAHz3mXzj_xf6uvhoQE8";

            String[] CookieDataSplited = CookieData.split(";");

            for (String CookieDataSplited1 : CookieDataSplited) {
                String[] CookieDataSplited1Data = CookieDataSplited1.split("=");
                String name = CookieDataSplited1Data[0].replace(" ", "");
                String Value = CookieDataSplited1Data[1].replace(" ", "");
                String Domain = "instagram.com";
                BasicClientCookie cookie = new BasicClientCookie(name, Value);
                cookie.setDomain(Domain);
                Cookie cookie2 = (Cookie) cookie;
                objPostFetchSource.lstCookie.add(cookie2);
            }

            
            String resFirst = objPostFetchSource.getSourceWithProxy("http://iconosquare.com", "", "iconosquare.com");

            String csfrResponse = objPostFetchSource.getSourceWithProxy("https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize%3Fclient_id%3Dd9494686198d4dfeb954979a3e270e5e%26redirect_uri%3Dhttp%253A%252F%252Ficonosquare.com%26response_type%3Dcode%26scope%3Dlikes%2Bcomments%2Brelationships", "http://iconosquare.com/", "instagram.com");

            for (Cookie CookieDataSplited1 : objPostFetchSource.lstCookie) {
                if (CookieDataSplited1.getName().equals("csrftoken")) {
                    String csrftoken = CookieDataSplited1.getValue();
                    postdata = "csrfmiddlewaretoken=" + csrftoken + "&username=" + username + "&password=" + password + "";
                    break;
                }

            }
            String host = "instagram.com";
            String referer = "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize%3Fclient_id%3Dd9494686198d4dfeb954979a3e270e5e%26redirect_uri%3Dhttp%253A%252F%252Ficonosquare.com%26response_type%3Dcode%26scope%3Dlikes%2Bcomments%2Brelationships";
            String postloginResponseCode = objPostFetchSource.fetchPageSourceWithProxyPost(firstUrl, postdata, referer, host);
            System.out.println("--------------------Post Login Response Code--------------------" + postloginResponseCode);
            String response2 = null;
            if (postloginResponseCode.contains("redirect Url :")) {
                String Url = postloginResponseCode.replace("redirect Url :", "");
                String Referer = "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize%3Fclient_id%3Dd9494686198d4dfeb954979a3e270e5e%26redirect_uri%3Dhttp%253A%252F%252Ficonosquare.com%26response_type%3Dcode%26scope%3Dlikes%2Bcomments%2Brelationships";
                try {
                    response2 = objPostFetchSource.getSourceWithProxy(Url, "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize%3Fclient_id%3Dd9494686198d4dfeb954979a3e270e5e%26redirect_uri%3Dhttp%253A%252F%252Ficonosquare.com%26response_type%3Dcode%26scope%3Dlikes%2Bcomments%2Brelationships", "instagram.com");
                } catch (Exception e1312) {
                    String viewerResponse;
                    System.out.println("Final URL");
                    viewerResponse = objPostFetchSource.getSourceWithProxy("http://iconosquare.com/viewer.php", "", "iconosquare.com");
//                System.out.println("response of viewer ::\n" + viewerResponse);
                    if ((viewerResponse != null) && (viewerResponse.length() > 0)) {
                        loggedInStatus = 1;
                        logger2textArea.append("\nLogin to "+username+" Successfull");
                        return loggedInStatus;
                    }
                }
            }
        } catch (IOException iOException) {
        }
        return loggedInStatus;
    }
    public long sendMessagebyInconosquare(String username, String message) {
        long messageSent = 0;
        try {
            String commentResponse;
            commentResponse = objPostFetchSource.getSourceWithProxy("http://iconosquare.com/comments.php", "http://iconosquare.com/viewer.php", "iconosquare.com");
            if ((commentResponse != null) && (commentResponse.length() > 0)) {
                String messageResponse;
                messageResponse = objPostFetchSource.getSourceWithProxy("http://iconosquare.com/messages.php", "http://iconosquare.com/comments.php", "iconosquare.com");

                if ((messageResponse != null) && (messageResponse.length() > 0)) {
                    String newMessageResponse;
                    newMessageResponse = objPostFetchSource.getSourceWithProxy("http://iconosquare.com/message_post_autocomplete.php", "http://iconosquare.com/messages.php", "iconosquare.com");
                    if ((newMessageResponse != null) && (newMessageResponse.length() > 0)) {
                        String sendMessageResponse;
                        String url = "http://iconosquare.com/controller_ajax.php";
                        String referer = "http://iconosquare.com/messages.php";
                        String postdata = "action=save-dm&username=" + username + "&message=" + message;
                        String host = "iconosquare.com";
                        sendMessageResponse = objPostFetchSource.fetchPageSourceWithProxyPost(url, postdata, referer, host);
                        System.out.println("ContentLength::::\n" + sendMessageResponse);
                        if (sendMessageResponse.equals("1")) {
                            messageSent = 1;
                            logger2textArea.append("\nMesage:"+message+" Sent to "+username);
                            
                        }

                    }
                }
            }
        } catch (IOException iOException) {
        }
        return messageSent;
    }

    public long followUserSendMessageUnfollowbyInconosquare(String username, String message) {
        long followUserSendMessageUnfollow = 0;
        try {
            String accessToken = "";
            String viewResponseForToken = objPostFetchSource.getSourceWithProxy("http://iconosquare.com/viewer.php", "http://iconosquare.com/messages.php", "iconosquare.com");

            Document doc = Jsoup.parse(viewResponseForToken);
            Elements e = doc.select("div[id=accesstoken]");
            accessToken = e.text();
            System.out.println("accesstoken : " + accessToken);
            if (accessToken.length() > 0) {
                String userID = "";
                String searchurl = "http://iconosquare.com/rqig.php?e=/users/search&a=ico2&t=" + accessToken + "&q=" + username;
                String jsonResponse = objPostFetchSource.getSourceWithProxy(searchurl, "http://iconosquare.com/viewer.php", "iconosquare.com");

                JSONObject a = new JSONObject(jsonResponse);
                System.out.println("aaaa:::" + a);
                JSONArray jsonarray = a.getJSONArray("data");
                System.out.println("jsonarray" + jsonarray);
                System.out.println("jsonarray.length()" + jsonarray.length());
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject eachVal = jsonarray.getJSONObject(i);
                    if (eachVal.getString("username").equals(username)) {
                        System.out.println("jsonarray.get(i):::" + eachVal.getString("id"));
                        userID = eachVal.getString("id");
                        break;
                    }
                }
                if (userID.length() > 0) {

                    String followurl = "http://iconosquare.com/controller_ajax.php";
                    String postData = "action=relationship&user_id=" + userID + "&relation=follow";
                    String followResponse = objPostFetchSource.fetchPageSourceWithProxyPost(followurl, postData, "http://iconosquare.com/viewer.php", "iconosquare.com");
                    System.out.println("followResponse" + followResponse);

                    if (followResponse.equals("1")) {
                        System.out.println("Successfully following the user");
                        logger2textArea.append("\nSuccessfully following the user");
                        try {
                            long messageSent = sendMessagebyInconosquare(username, message);
                            if (messageSent == 1) {
                                logger2textArea.append("\nMesage:"+message+" Sent to "+username);

                            } else {
                                System.out.println("sorry Message not sent");
                                sendMessagebyInconosquare(username, message);
                            }
                        } catch (Exception e1) {
                            System.out.println("Error: Message not sent");
                        }
                        try {
                            //Unfollow the user
                            String unfollowurl = "http://iconosquare.com/controller_ajax.php";
                            String unfollowPostData = "action=relationship&user_id=" + userID + "&relation=unfollow";
                            String unfollowResponse = objPostFetchSource.fetchPageSourceWithProxyPost(unfollowurl, unfollowPostData, "http://iconosquare.com/viewer.php", "iconosquare.com");
                            System.out.println("unfollowResponse" + unfollowResponse);
                            if (followResponse.equals("1")) {
                                logger2textArea.append("\nSuccessfully un following the user");
                        System.out.println("Successfully un following the user");}

                        } catch (Exception e1) {
                        }

                    }

                }

            }

        } catch (Exception e) {
        }

        return followUserSendMessageUnfollow;

    }
    
}
