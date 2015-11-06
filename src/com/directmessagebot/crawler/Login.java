/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.directmessagebot.crawler;

import com.directmessagebot.utility.PostFetchSource;
import java.io.IOException;
import java.net.URISyntaxException;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Nitesh Shah
 */
public class Login {

    PostFetchSource objPostFetchSource = new PostFetchSource();

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, Exception {
        Login objLogin = new Login();
        long messageSent = 0;
        String messageRecepient = "harshali__malhotra";
        String message = "now check";
        int loggedInStatus = objLogin.LoginbyInconosquare("himanshumoriglobus", "globussoft");
        if (loggedInStatus == 1) {
            messageSent = objLogin.sendMessagebyInconosquare(messageRecepient, message);
        }
        if (messageSent == 1) {
            System.out.println("Message Sent");
        } else {
            System.out.println("userID" + messageSent);
            System.out.println("Message Not sent");
            objLogin.followUserSendMessageUnfollowbyInconosquare(messageRecepient, message);
        }

//        objLogin.test();
    }

    public int LoginbyInconosquare(String username, String password) throws URISyntaxException, IOException, InterruptedException, Exception {

        int loggedInStatus = 0;

        try {
//        String firstUrl = "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize/%3Fclient_id%3D9d836570317f4c18bca0db6d2ac38e29%26redirect_uri%3Dhttp%3A//websta.me/%26response_type%3Dcode%26scope%3Dcomments%2Brelationships%2Blikes";
            String firstUrl = "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize%3Fclient_id%3Dd9494686198d4dfeb954979a3e270e5e%26redirect_uri%3Dhttp%253A%252F%252Ficonosquare.com%26response_type%3Dcode%26scope%3Dlikes%2Bcomments%2Brelationships";
//        Document doc = Jsoup.parse(fetchGooglePageWithProxy.fetchPageSourcefromClientGoogle(new URI(firstUrl)));
//
//        Elements e = doc.select("input[type=hidden]");
//
//        String a[] = e.toString().split("value=\"");
//        String token = a[1].replace("\" />", "");
//        System.out.println("Token : " + token);
//
//        String postdata = "csrfmiddlewaretoken=" + token.trim() + "&username=" + username + "&password=" + password + "";
            String postdata = "";
//        System.out.println("Post Data : " + postdata);
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
//
//                Document doc = Jsoup.parse(messageResponse);
//                Elements e = doc.select("input[id=user_id]");
//                String user_id = e.attr("value");
//                System.out.println("user_id : " + user_id);
//                messageSent = Integer.parseInt(user_id);

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
//            System.out.println("viewResponseForToken:::"+viewResponseForToken);

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
                        try {
                            long messageSent = sendMessagebyInconosquare(username, message);
                            if (messageSent == 1) {

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

    public void test() {
        try {
            String adfg = "{\"meta\":{\"code\":200},\"data\":[{\"username\":\"rivannna\",\"profile_picture\":\"https:\\/\\/igcdn-photos-g-a.akamaihd.net\\/hphotos-ak-xap1\\/t51.2885-19\\/11236310_1578983235702390_1960120605_a.jpg\",\"id\":\"610730888\",\"full_name\":\"\\u2800Baby Animals, Hi I'm Rivanna\\u30c4\"},{\"username\":\"rivannnas\",\"profile_picture\":\"https:\\/\\/scontent.cdninstagram.com\\/hphotos-xfp1\\/t51.2885-19\\/11950480_1473084966332663_686501107_a.jpg\",\"id\":\"1330408324\",\"full_name\":\"Baby Animals, Hi I'm Rivanna\\u2766\"},{\"username\":\"rivanator\",\"profile_picture\":\"https:\\/\\/scontent.cdninstagram.com\\/hphotos-xpf1\\/t51.2885-19\\/s150x150\\/1390058_803900373062990_1735712235_a.jpg\",\"id\":\"2173322071\",\"full_name\":\"rivannna\"},{\"username\":\"rivannna.disney\",\"profile_picture\":\"https:\\/\\/igcdn-photos-e-a.akamaihd.net\\/hphotos-ak-xaf1\\/t51.2885-19\\/11117059_1422674771375300_612861266_a.jpg\",\"id\":\"1985208597\",\"full_name\":\"I Like Disney And Rivannna\"},{\"username\":\"rivannnaa\",\"profile_picture\":\"https:\\/\\/igcdn-photos-f-a.akamaihd.net\\/hphotos-ak-xfa1\\/t51.2885-19\\/11191539_989288121096165_1660168315_a.jpg\",\"id\":\"1723845409\",\"full_name\":\"\"},{\"username\":\"_rivannna_\",\"profile_picture\":\"https:\\/\\/scontent.cdninstagram.com\\/hphotos-xpf1\\/t51.2885-19\\/11137863_651331961676895_2049970273_a.jpg\",\"id\":\"1812495563\",\"full_name\":\"\"},{\"username\":\"rivannna1\",\"profile_picture\":\"https:\\/\\/scontent.cdninstagram.com\\/hphotos-xaf1\\/t51.2885-19\\/11245931_810935508992857_1708773719_a.jpg\",\"id\":\"2123273718\",\"full_name\":\"Rivannna1\"},{\"username\":\"rivannnaaa\",\"profile_picture\":\"https:\\/\\/scontent.cdninstagram.com\\/hphotos-xaf1\\/t51.2885-19\\/11208062_407044146166545_916085659_a.jpg\",\"id\":\"1902873239\",\"full_name\":\"\"},{\"username\":\"rivan_rizqi\",\"profile_picture\":\"https:\\/\\/igcdn-photos-e-a.akamaihd.net\\/hphotos-ak-xaf1\\/t51.2885-19\\/926487_633893196688372_542945708_a.jpg\",\"id\":\"1394398422\",\"full_name\":\"rivannnarizqipratama\"},{\"username\":\"rivannna_san\",\"profile_picture\":\"https:\\/\\/instagramimages-a.akamaihd.net\\/profiles\\/anonymousUser.jpg\",\"id\":\"2021565146\",\"full_name\":\"\"}]}";

            JSONObject a = new JSONObject(adfg);
            System.out.println("aaaa:::" + a);
//                String adfg = "{\"full_name\":\"⠀Baby Animals, Hi I'm Rivannaツ\",\"profile_picture\":\"https://igcdn-photos-g-a.akamaihd.net/hphotos-ak-xap1/t51.2885-19/11236310_1578983235702390_1960120605_a.jpg\",\"id\":\"610730888\",\"username\":\"rivannna\"},{\"full_name\":\"Baby Animals, Hi I'm Rivanna❦\",\"profile_picture\":\"https://scontent.cdninstagram.com/hphotos-xfp1/t51.2885-19/11950480_1473084966332663_686501107_a.jpg\",\"id\":\"1330408324\",\"username\":\"rivannnas\"},{\"full_name\":\"rivannna\",\"profile_picture\":\"https://scontent.cdninstagram.com/hphotos-xpf1/t51.2885-19/s150x150/1390058_803900373062990_1735712235_a.jpg\",\"id\":\"2173322071\",\"username\":\"rivanator\"},{\"full_name\":\"I Like Disney And Rivannna\",\"profile_picture\":\"https://igcdn-photos-e-a.akamaihd.net/hphotos-ak-xaf1/t51.2885-19/11117059_1422674771375300_612861266_a.jpg\",\"id\":\"1985208597\",\"username\":\"rivannna.disney\"},{\"full_name\":\"\",\"profile_picture\":\"https://igcdn-photos-f-a.akamaihd.net/hphotos-ak-xfa1/t51.2885-19/11191539_989288121096165_1660168315_a.jpg\",\"id\":\"1723845409\",\"username\":\"rivannnaa\"},{\"full_name\":\"\",\"profile_picture\":\"https://scontent.cdninstagram.com/hphotos-xpf1/t51.2885-19/11137863_651331961676895_2049970273_a.jpg\",\"id\":\"1812495563\",\"username\":\"_rivannna_\"},{\"full_name\":\"Rivannna1\",\"profile_picture\":\"https://scontent.cdninstagram.com/hphotos-xaf1/t51.2885-19/11245931_810935508992857_1708773719_a.jpg\",\"id\":\"2123273718\",\"username\":\"rivannna1\"},{\"full_name\":\"\",\"profile_picture\":\"https://scontent.cdninstagram.com/hphotos-xaf1/t51.2885-19/11208062_407044146166545_916085659_a.jpg\",\"id\":\"1902873239\",\"username\":\"rivannnaaa\"},{\"full_name\":\"rivannnarizqipratama\",\"profile_picture\":\"https://igcdn-photos-e-a.akamaihd.net/hphotos-ak-xaf1/t51.2885-19/926487_633893196688372_542945708_a.jpg\",\"id\":\"1394398422\",\"username\":\"rivan_rizqi\"},{\"full_name\":\"\",\"profile_picture\":\"https://instagramimages-a.akamaihd.net/profiles/anonymousUser.jpg\",\"id\":\"2021565146\",\"username\":\"rivannna_san\"}";
            JSONArray jsonarray = a.getJSONArray("data");
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject eachVal = jsonarray.getJSONObject(i);
                if (eachVal.getString("username").equals("rivannna")) {
                    System.out.println("jsonarray.get(i):::" + eachVal.getString("id"));
                    break;
                }

            }

        } catch (JSONException jSONException) {
        }
    }

}
