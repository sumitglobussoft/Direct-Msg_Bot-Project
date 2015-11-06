/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.directmessagebot.crawler;

import com.directmessagebot.utility.fetchGooglePageWithProxy;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.net.URI;
import java.net.URISyntaxException;
import org.jsoup.select.Elements;

/**
 *
 * @author Nitesh Shah
 */
public class DirectMessageBot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        // TODO code application logic here
        DirectMessageBot objDirectMessageBot = new DirectMessageBot();
        objDirectMessageBot.getProfileData();
    }

    public void getProfileData() throws IOException, InterruptedException, URISyntaxException {

        String a = "http://websta.me/n/cristiano";
        Document doc = Jsoup.parse(fetchGooglePageWithProxy.fetchPageSourcefromClientGoogle(new URI(a)));

        Elements eleFullnamename = doc.select("div[class=profbox] div[class=userinfo] h2");
        String fullname = eleFullnamename.text().trim();

        Elements eleusername = doc.select("div[class=profbox] div[class=userinfo] h1");
        String username = eleusername.text().trim();

        Elements eleProfileUrl = doc.select("div[class=profbox] img[class=profimg]");
        String imageUrl = eleProfileUrl.attr("src").trim();

        Elements elePosts = doc.select("div[class=userinfo] li span[class=counts_media]");
        String Post = elePosts.text().trim();

        Elements eleFollowers = doc.select("div[class=userinfo] li span[class=counts_followed_by]");
        String followers = eleFollowers.text().trim();

        Elements eleFollowing = doc.select("div[class=userinfo] li span[class=following]");
        String following = eleFollowing.text().trim();

        Elements eleUserID = doc.select("div[class=userinfo] ul[class]");
        String a1[] = eleUserID.toString().split(" ");
        String userid = a1[2].replace("user-", "").replace("\">", "").trim();

        String postdata = "target_users=%5B%7B%22username%22%3A%22" + username + "%22%2C%22profile_picture%22%3A%22" + imageUrl + "%22%2C%22id%22%3A%22" + userid + "%22%2C%22full_name%22%3A%22" + fullname + "%22%2C%22text%22%3A%22" + username + "%22%7D%5D&text=" + "Hii";
        System.out.println("Post Data : " + postdata);
//        res_postdata = Obj_DMessage.globusHttpHelper.postFormData(new URI("http://websta.me/messages/api/create_message"), postdata, "http://websta.me/messages/new", "");
    }

}
