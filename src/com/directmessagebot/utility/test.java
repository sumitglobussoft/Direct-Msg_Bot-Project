/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.directmessagebot.utility;

import static com.directmessagebot.utility.fetchGooglePageWithProxy.fetchPageSourcefromClientGoogleSecond;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Nitesh Shah
 */
public class test {

    public HttpsURLConnection conn;
    private final List<String> cookies = new ArrayList<>();
    public List<Cookie> lstCookie = new ArrayList<>();
    List<Cookie> lstCookieDuplicate = new ArrayList<>();
    List<Cookie> lstCookieForCurrentuse = new ArrayList<>();
    List<String> cookieNames = new ArrayList<>();

    public String fetchPageSourceWithProxyPost(String newUrl, String urlParameter) throws InterruptedException, IOException {

        Random r = new Random();

//        ProxyImport obj = proxyList.get(r.nextInt(proxyList.size()));
//        String ip = obj.proxyIP;
        int portno = generateRandomPort();
//        String username = "";
//        String password = "";
//        if (obj.proxyLen> 2) {
//            username = obj.proxyUserName;
//            password = obj.proxyPassword;
//        }

        CredentialsProvider credsprovider = new BasicCredentialsProvider();
//
//        if (portno == 1601) {
//            portno = generateRandomPort();
//        }

//        System.out.println("IP ::: " + ip + "  Port ::: " + portno);
        credsprovider.setCredentials(
                new AuthScope("195.154.161.103", portno),
                new UsernamePasswordCredentials("mongoose", "Fjh30fi"));
        HttpHost proxy = new HttpHost("195.154.161.103", portno);
        //-----------------------------------------------------------------------
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();

        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsprovider)
                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:32.0) Gecko/20100101 Firefox/32.0")
                .setDefaultRequestConfig(requestConfig)
                .setProxy(proxy)
                .build();
        String responsebody = "";
        String responsestatus = null;
        int count = 0;
        try {
            try {
//            HttpClient client = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(newUrl);

//            System.out.println("----------------HTTPPOST-------------" + httppost);
                httppost.addHeader("Host", "instagram.com");
                httppost.addHeader("Connection", "keep-alive");
                httppost.addHeader("Content-Length", "99");
                httppost.addHeader("Cache-Control", "max-age=0");
                httppost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                httppost.addHeader("Origin", "https://instagram.com");
                httppost.addHeader("Upgrade-Insecure-Requests", "1");
                httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
                httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
                httppost.addHeader("Referer", "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize/%3Fclient_id%3D9d836570317f4c18bca0db6d2ac38e29%26redirect_uri%3Dhttp%3A//websta.me/%26response_type%3Dcode%26scope%3Dcomments%2Brelationships%2Blikes");
                httppost.addHeader("Accept-Encoding", "gzip, deflate");
                httppost.addHeader("Accept-Language", "en-GB,en-US;q=0.8,en;q=0.6");
                httppost.addHeader("Cookie", "mid=ViDPcwAEAAEZJIsQ69oXS1yIt-xM; sessionid=IGSC17d0985c7f6c412b9000d6a16f61d6090317ad3ce98e17bdddf6ccf5d546d301%3A1tQmHvyLs3AnxzEqGmQzN8Vr2POGAwwq%3A%7B%7D; csrftoken=cb5349ce32296df7c3b57ffd73c9713b");

                System.out.println("-----Response status   " + httppost.getRequestLine());
                ArrayList<NameValuePair> postParameters;
                postParameters = new ArrayList<>();
                String a[] = urlParameter.split("&");
                postParameters.add(new BasicNameValuePair("csrfmiddlewaretoken", a[0].split("=")[1]));
                postParameters.add(new BasicNameValuePair("username", a[1].split("=")[1]));
                postParameters.add(new BasicNameValuePair("password", a[2].split("=")[1]));

//            httppost.setEntity(new UrlEncodedFormEntity(postParameters));
                CloseableHttpResponse resp = httpclient.execute(httppost);
                System.out.println("``````````````````````````````" + resp);
                responsestatus = resp.getStatusLine().toString();
                System.out.println("----->>> Response status : " + responsestatus);
                if (responsestatus.contains("503") || responsestatus.contains("502") || responsestatus.contains("403")
                        || responsestatus.contains("400") || responsestatus.contains("407") || responsestatus.contains("401")
                        || responsestatus.contains("402") || responsestatus.contains("404") || responsestatus.contains("405")
                        || responsestatus.contains("SSLHandshakeException") || responsestatus.contains("999")
                        || responsestatus.contains("ClientProtocolException") || responsestatus.contains("SocketTimeoutException")
                        || "".equals(responsestatus)) {
                    Thread.sleep(10000);
                    do {
                        count++;
                        responsebody = fetchPageSourceWithProxyPost(newUrl, urlParameter);
                        System.out.println("=======Response Body==============" + responsebody);
                        if (responsebody == null) {
                            Thread.sleep(5000);
                            System.out.println("PROX FAILURE");
                        }
                        if (count > 5) {
                            Thread.sleep(1000);
                            break;
                        }
                    } while (responsebody == null || "".equals(responsebody));
                } else {
                    HttpEntity entity = resp.getEntity();
                    if (entity != null) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            responsebody = new StringBuilder().append(responsebody).append(inputLine).toString();
                        }
                    }
                    EntityUtils.consume(entity);
                }
            } catch (IOException | IllegalStateException e) {
                System.out.println("Exception = " + e);

                do {
                    count++;
                    responsebody = fetchPageSourceWithProxyPost(newUrl, urlParameter);
                    if (responsebody == null) {
                        System.out.println("PROX FAILURE");
                    }
                    if (count > 5) {
                        Thread.sleep(50000);
                        break;
                    }
                } while (responsebody == null || "".equals(responsebody));
            } finally {
                httpclient.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();

        }
        return responsebody;
    }

    public String fetchPageSourcefromClientGoogle(URI newurl) throws IOException, InterruptedException {

        int portNo = generateRandomPort();
        CredentialsProvider credsprovider = new BasicCredentialsProvider();
        credsprovider.setCredentials(
                new AuthScope("195.154.161.103", portNo),
                new UsernamePasswordCredentials("mongoose", "Fjh30fi"));
        HttpHost proxy = new HttpHost("195.154.161.103", portNo);
        //-----------------------------------------------------------------------
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();

        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsprovider)
                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36")
                .setDefaultRequestConfig(requestConfig)
                .setProxy(proxy)
                .build();
        String responsebody = "";
        String responsestatus = null;
        int count = 0;
        try {
            HttpGet httpget = new HttpGet(newurl);
//            httpget.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//            httpget.addHeader("Accept-Encoding", "gzip, deflate");
//            httpget.addHeader("Accept-Language", "en-US,en;q=0.5");
//            httpget.addHeader("Connection", "keep-alive");
//            httpget.addHeader("Host", "websta.me");
            // httpget.addHeader("Cookie", "__cfduid=d2315f159da304d449e5497e9a0ec96d71444976376; PHPSESSID=m0mree14vboqaum1unugo0afo3; is_message_first_access=1; is_first_access=1; _cb_ls=1; survey_150717=5; __gads=ID=5bd7642d31c3b9c8:T=1444990331:S=ALNI_MY4B5PIyVPJsWZHlNNMCa520oJdCg; OX_plg=swf|shk|pm; _gat=1; OX_sd=2; _lb.s=1766600974%3A; _lb.u=1%2F71ee2e5aa6047a789d3c197933408c82%3A; _ga=GA1.2.461314698.1444976394; _chartbeat2=BccsXu6HL99V_rZE.1444976406569.1445067485750.11; _chartbeat5=1002,0,%2F,http%3A%2F%2Fwebsta.me%2Flogin,JN62DCfLXOMDzEHbuCUISR16MeVX,body%2Fdiv%5B1%5D%2Fdiv%5B1%5D%2Fdiv%5B1%5D%2Ful%5B3%5D%2Fli%5B1%5D%2Fa%5B1%5D,c; PRUM_EPISODES=s=1445067486472&r=http%3A//websta.me/%3Ft%3Dlo; _chartbeat4=t=DszbX2B6UQM1BK0PlWC_YT3WD-VP0F&E=2&x=0&c=0.05&y=2825&w=586");

            httpget.addHeader("Host", "websta.me");
            httpget.addHeader("Connection", "keep-alive");
            httpget.addHeader("Cache-Control", "max-age=0");
            httpget.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httpget.addHeader("Upgrade-Insecure-Requests", "1");
            httpget.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
            httpget.addHeader("Accept-Encoding", "gzip, deflate");
            httpget.addHeader("Accept-Language", "en-GB,en-US;q=0.8,en;q=0.6");

            HttpClientContext context = HttpClientContext.create();
            CookieStore cookieStore = new BasicCookieStore();

            if (lstCookie.size() > 0) {
                for (Cookie postParameter : lstCookie) {

                    cookieStore.addCookie(postParameter);
                }
                context.setCookieStore(cookieStore);
            }

            CloseableHttpResponse resp = httpclient.execute(httpget, context);

            System.out.println("  GET METHOD  ");
            System.out.println("1. Response status : " + httpget.getRequestLine());
            System.out.println("2. Response Code   : " + resp.getStatusLine().getStatusCode());

            List<Cookie> lstCookieForHere = context.getCookieStore().getCookies();
            lstCookieDuplicate.clear();

            for (Cookie lstCookieForHere2 : lstCookieForHere) {

                boolean isfound = false;

                for (Cookie lstCookieForHere1 : lstCookie) {
                    if (lstCookieForHere1.getName().equals(lstCookieForHere2.getName())) {
                        isfound = true;
                        lstCookieDuplicate.add(lstCookieForHere2);
                        lstCookieForCurrentuse.add(lstCookieForHere1);
                        break;

                    }
                    if (!isfound) {
                        lstCookieDuplicate.add(lstCookieForHere2);
                    }
                }
            }

            for (Cookie lstCookieForHere1 : lstCookieForCurrentuse) {
                lstCookie.remove(lstCookieForHere1);
            }
            for (Cookie lstCookieForHere1 : lstCookieDuplicate) {
                lstCookie.add(lstCookieForHere1);
            }

            responsestatus = resp.getStatusLine().toString();
            if (responsestatus.contains("503") || responsestatus.contains("502") || responsestatus.contains("403")
                    || responsestatus.contains("400") || responsestatus.contains("407") || responsestatus.contains("401")
                    || responsestatus.contains("402") || responsestatus.contains("404") || responsestatus.contains("405")
                    || responsestatus.contains("SSLHandshakeException") || responsestatus.contains("999")
                    || responsestatus.contains("ClientProtocolException") || responsestatus.contains("SocketTimeoutException")
                    || "".equals(responsestatus)) {
                Thread.sleep(10000);
                do {
                    count++;
                    responsebody = fetchPageSourcefromClientGoogleSecond(newurl);
                    if (responsebody == null) {
                        Thread.sleep(10000);
                        System.out.println("PROX FAILURE");
                    }
                    if (count > 20) {
                        Thread.sleep(1000);
                        break;
                    }
                } while (responsebody == null || "".equals(responsebody));
            } else {
                HttpEntity entity = resp.getEntity();
                System.out.println(resp.getStatusLine());
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength());
                    BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        responsebody = new StringBuilder().append(responsebody).append(inputLine).toString();
                    }
                    // writeResponseFile(responsebody, pagename);
                }
                EntityUtils.consume(entity);
            }
        } catch (IOException | IllegalStateException e) {
            System.out.println("Exception = " + e);
            do {
                count++;
                responsebody = fetchPageSourcefromClientGoogleSecond(newurl);
                if (responsebody == null) {
                    System.out.println("PROX FAILURE");
                }
                if (count > 15) {
                    Thread.sleep(50000);
//                    responsebody = fetchPageSourcefromClientGoogleSecond(newurl);
                    break;
                }
            } while (responsebody == null || "".equals(responsebody));
        } finally {
            httpclient.close();
        }
        return responsebody;
    }

    public String fetchPageSourceWithProxySecondPost(String newurl, String urlParameter, String cookieData) throws IOException {

//        newusarameter = "csrfmiddlewaretoken=763e6b4b369f8e8c0fd1ef4109d8c30c&username=himanshumoriglobus&password=456321789";
        Random r = new Random();
        int portno = generateRandomPort();
//        ProxyImport obj = proxyList.get(r.nextInt(proxyList.size()));

//        String ip = obj.proxyIP;
//        int portno = Integer.parseInt(obj.proxyPort);
//        String username = "";
//        String password = "";
//        if (obj.proxyLen> 2) {
//            username = obj.proxyUserName;
//            password = obj.proxyPassword;
//        }
        CredentialsProvider credsprovider = new BasicCredentialsProvider();
//
        if (portno == 1601) {
            portno = generateRandomPort();
        }

        String a[] = urlParameter.split("&");
        System.out.println("" + a[0].split("=")[1]);

//        System.out.println("IP ::: " + ip + "  Port ::: " + portno);
        credsprovider.setCredentials(
                new AuthScope("195.154.161.103", portno),
                new UsernamePasswordCredentials("mongoose", "Fjh30fi"));
        HttpHost proxy = new HttpHost("195.154.161.103", portno);
        //-----------------------------------------------------------------------

        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();

        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsprovider)
                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36")
                .setDefaultRequestConfig(requestConfig)
                .setProxy(proxy)
                .build();
        String responsebody = "";
        String responsestatus = null;
        try {

            HttpPost httppost = new HttpPost(newurl);

            httppost.addHeader("Host", "instagram.com");
            httppost.addHeader("Connection", "keep-alive");

            httppost.addHeader("Cache-Control", "max-age=0");
            httppost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httppost.addHeader("Origin", "https://instagram.com");
            httppost.addHeader("Upgrade-Insecure-Requests", "1");
            httppost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.addHeader("Referer", "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize/%3Fclient_id%3D9d836570317f4c18bca0db6d2ac38e29%26redirect_uri%3Dhttp%3A//websta.me/%26response_type%3Dcode%26scope%3Dcomments%2Brelationships%2Blikes");
            httppost.addHeader("Accept-Encoding", "gzip, deflate");
            httppost.addHeader("Accept-Language", "en-GB,en-US;q=0.8,en;q=0.6");

            String cookidata = "";
            if (lstCookie == null || lstCookie.size() == 0) {
                httppost.addHeader("Cookie", "mid=Vh93ygAEAAEWvO5-al8wwlWqULks; sessionid=IGSCf76b609633e6ac98f38f89d715dde24e0d5ec319f6361ececec622826070ee4f%3A3yZ4VHnXSR6oTxwWQkur8CZF2nF9ZDAG%3A%7B%7D; csrftoken=" + a[0].split("=")[1] + "");
            } else {
                for (Cookie a1 : lstCookie) {
                    String name = a1.getName();
                    String value = a1.getValue();
                    String FullCookie = name + "=" + value + ";";

                    cookidata += FullCookie;
                }

            }

            ArrayList<NameValuePair> postParameters;
            postParameters = new ArrayList<>(3);
            postParameters.add(new BasicNameValuePair("csrfmiddlewaretoken", a[0].split("=")[1]));
            postParameters.add(new BasicNameValuePair("username", a[1].split("=")[1]));
            postParameters.add(new BasicNameValuePair("password", a[2].split("=")[1]));
            httppost.setEntity(new UrlEncodedFormEntity(postParameters));

            HttpClientContext context = HttpClientContext.create();
            CookieStore cookieStore = new BasicCookieStore();

            if (lstCookie != null) {
                for (Cookie postParameter : lstCookie) {

                    cookieStore.addCookie(null);
                }
                context.setCookieStore(cookieStore);
            }

            CloseableHttpResponse resp = httpclient.execute(httppost, context);
            System.out.println("  POST METHOD  ");
            System.out.println("1. Response status : " + httppost.getRequestLine());
            System.out.println("2. Response Code   : " + resp.getStatusLine().getStatusCode());
            responsestatus = resp.getStatusLine().toString();
            System.out.println(context.getCookieStore().getCookies());

            List<Cookie> lstCookieForHere = context.getCookieStore().getCookies();
            if (lstCookieDuplicate != null) {
                lstCookieDuplicate.clear();
            }

            for (Cookie lstCookieForHere2 : lstCookieForHere) {

                boolean isfound = false;

                try {
                    for (Cookie lstCookieForHere1 : lstCookie) {
                        if (lstCookieForHere1.getName().equals(lstCookieForHere2.getName())) {
                            isfound = true;
                            lstCookieDuplicate.add(lstCookieForHere2);
                            lstCookieForCurrentuse.add(lstCookieForHere1);
                            break;

                        }
                    }
                    if (!isfound) {
                        lstCookie.add(lstCookieForHere2);
                    }
                } catch (Exception Ex) {
                    lstCookie.add(lstCookieForHere2);
                }

            }

            if (lstCookieForCurrentuse != null) {
                for (Cookie lstCookieForHere1 : lstCookieForCurrentuse) {
                    lstCookie.remove(lstCookieForHere1);
                }
            }
            if (lstCookieDuplicate != null) {
                for (Cookie lstCookieForHere1 : lstCookieDuplicate) {
                    lstCookie.add(lstCookieForHere1);
                }
            }

            Header[] CookieData = resp.getHeaders("Set-Cookie");

            System.out.println("3. Response Status Code : " + responsestatus);

            if (responsestatus.contains("503") || responsestatus.contains("502") || responsestatus.contains("400")
                    || responsestatus.contains("401") || responsestatus.contains("402") || responsestatus.contains("403")
                    || responsestatus.contains("407") || responsestatus.contains("404") || responsestatus.contains("405")
                    || responsestatus.contains("SSLHandshakeException") || responsestatus.contains("999")
                    || responsestatus.contains("ClientProtocolException") || responsestatus.contains("SocketTimeoutException")
                    || responsestatus == null || "".equals(responsestatus)) {
                return null;
            } else {
                HttpEntity entity = resp.getEntity();
                if (entity != null) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        responsebody = new StringBuilder().append(responsebody).append(inputLine).toString();
                        System.out.println("Response Body : " + responsebody);
                    }
                }
                EntityUtils.consume(entity);
            }
        } catch (IOException | IllegalStateException e) {
            return null;
        } finally {
            httpclient.close();
        }
        return responsebody;
    }

    public String sendPost(String fb, String postParams) throws Exception {
        fb = "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize/%3Fclient_id%3D9d836570317f4c18bca0db6d2ac38e29%26redirect_uri%3Dhttp%3A//websta.me/%26response_type%3Dcode%26scope%3Dcomments%2Brelationships%2Blikes";
        postParams = "csrfmiddlewaretoken=763e6b4b369f8e8c0fd1ef4109d8c30c&username=himanshumoriglobus&password=456321789";

        URL obj = new URL(fb);
        conn = (HttpsURLConnection) obj.openConnection();

        // Acts like a browser
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Host", "instagram.com");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
        conn.setRequestProperty("Accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.setRequestProperty("Accept-Language", "en-GB,en-US;q=0.8,en;q=0.6");
//        for (String cookie : this.cookies) {
//            conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
//        }
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("Referer", "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize/%3Fclient_id%3D9d836570317f4c18bca0db6d2ac38e29%26redirect_uri%3Dhttp%3A//websta.me/%26response_type%3Dcode%26scope%3Dcomments%2Brelationships%2Blikes");
        // conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Origin", "https://instagram.com");
        conn.setRequestProperty("Cookie", "mid=Vh93ygAEAAEWvO5-al8wwlWqULks; sessionid=IGSCc4f2bb7aaf66c242017bd8dd409e9539d6dae34bb965f07dd6d658c3d7894941%3AZN0Rb8xZl7VjxFDVFVxxk3HFHXMzyWuP%3A%7B%7D; csrftoken=763e6b4b369f8e8c0fd1ef4109d8c30c");
        // conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));
        conn.setRequestProperty("Upgrade-Insecure-Requests", "1");

        conn.setDoOutput(true);
        conn.setDoInput(true);
        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.writeBytes(postParams);
            wr.flush();
        }

        int responseCode = conn.getResponseCode();

        String CookieData = conn.getHeaderField("Set-Cookie");
//    if (cookie != null)
//       cookie = cookie.substring(0, cookie.indexOf(';'));
//           System.out.println("cookie: " + cookie);

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

    public String executePost(String targetURL, String urlParameters) {

        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection

            System.out.println("targetURL : " + targetURL);
            System.out.println("urlParameters : " + urlParameters);
            String a[] = urlParameters.split("&");
            System.out.println("" + a[0].split("=")[1]);

            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Host", "instagram.com");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Content-Length", "99");
            connection.setRequestProperty("Cache-Control", "max-age=0");
            connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            connection.setRequestProperty("Origin", "https://instagram.com");
            connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Referer", "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize/%3Fclient_id%3D9d836570317f4c18bca0db6d2ac38e29%26redirect_uri%3Dhttp%3A//websta.me/%26response_type%3Dcode%26scope%3Dcomments%2Brelationships%2Blikes");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            connection.setRequestProperty("Accept-Language", "en-GB,en-US;q=0.8,en;q=0.6");
            connection.setRequestProperty("Cookie", "mid=ViDPcwAEAAEZJIsQ69oXS1yIt-xM; sessionid=IGSC17d0985c7f6c412b9000d6a16f61d6090317ad3ce98e17bdddf6ccf5d546d301%3A1tQmHvyLs3AnxzEqGmQzN8Vr2POGAwwq%3A%7B%7D; csrftoken=" + a[0].split("=")[1] + "");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //send Request 
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
            dataout.writeBytes(urlParameters);
            dataout.flush();
            dataout.close();

            //get response
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
                response.append('\n');

            }
            System.out.println("---------------------------------------->>>>>>>" + response.toString());
            br.close();
            return response.toString();
        } catch (Exception e) {
            System.out.println("Unable to full create connection");
            e.printStackTrace();
            return null;
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }

    }

    public String sendPOST1111(String targetURL, String urlParameters) throws IOException {
        URL obj = new URL(targetURL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        String a[] = urlParameters.split("&");
        System.out.println("" + a[0].split("=")[1]);
        con.setRequestMethod("POST");
        con.setRequestProperty("Host", "instagram.com");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("Content-Length", "99");
        con.setRequestProperty("Cache-Control", "max-age=0");
        con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        con.setRequestProperty("Origin", "https://instagram.com");
        con.setRequestProperty("Upgrade-Insecure-Requests", "1");
        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("Referer", "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize/%3Fclient_id%3D9d836570317f4c18bca0db6d2ac38e29%26redirect_uri%3Dhttp%3A//websta.me/%26response_type%3Dcode%26scope%3Dcomments%2Brelationships%2Blikes");
        con.setRequestProperty("Accept-Encoding", "gzip, deflate");
        con.setRequestProperty("Accept-Language", "en-GB,en-US;q=0.8,en;q=0.6");
        con.setRequestProperty("Cookie", "mid=ViDPcwAEAAEZJIsQ69oXS1yIt-xM; sessionid=IGSC17d0985c7f6c412b9000d6a16f61d6090317ad3ce98e17bdddf6ccf5d546d301%3A1tQmHvyLs3AnxzEqGmQzN8Vr2POGAwwq%3A%7B%7D; csrftoken=" + a[0].split("=")[1] + "");

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(urlParameters.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("----->>>>>>>>>>" + response.toString());
            return response.toString();
            // print result

        } else {
            System.out.println("POST request not worked");
        }
        return null;
    }

    public String sendPostWithoutProxy(String pageurl, String urlParameter) throws Exception {

        URL url = new URL(pageurl);
        String urlParameters = urlParameter;

        String body = "";
        URLConnection conn = null;
        try {
            //URLConnection conn = url.openConnection();
            conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            // OutputStreamWriter wr = new OutputStreamWriter(os);
            wr.write(urlParameters);

            wr.flush();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            // BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = rd.readLine()) != null) {
                response.append(inputLine);
            }
            rd.close();
            body = response.toString();
        } catch (Exception e) {
            System.out.println("outside exception" + e);
            int respCode = ((HttpURLConnection) conn).getResponseCode();
            System.out.println(respCode);
            try {
                //URLConnection conn = url.openConnection();
                conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                // OutputStreamWriter wr = new OutputStreamWriter(os);
                wr.write(urlParameters);
                wr.flush();
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                // BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = rd.readLine()) != null) {
                    response.append(inputLine);
                }
                rd.close();
                body = response.toString();
            } catch (Exception e1) {
                System.out.println("inside exception" + e1);
                respCode = ((HttpURLConnection) conn).getResponseCode();
                System.out.println(respCode);
                try {
                    //URLConnection conn = url.openConnection();
                    conn = url.openConnection();
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    // OutputStreamWriter wr = new OutputStreamWriter(os);
                    wr.write(urlParameters);
                    wr.flush();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    // BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = rd.readLine()) != null) {
                        response.append(inputLine);
                    }
                    rd.close();
                    body = response.toString();
                } catch (Exception e2) {
                    System.out.println("inside inside exception" + e2);
                    respCode = ((HttpURLConnection) conn).getResponseCode();
                    System.out.println(respCode);
                    try {
                        //URLConnection conn = url.openConnection();
                        conn = url.openConnection();
                        conn.setDoOutput(true);
                        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                        // OutputStreamWriter wr = new OutputStreamWriter(os);
                        wr.write(urlParameters);
                        wr.flush();
                        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        // BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        String inputLine;
                        StringBuffer response = new StringBuffer();

                        while ((inputLine = rd.readLine()) != null) {
                            response.append(inputLine);
                        }
                        rd.close();
                        body = response.toString();
                    } catch (Exception e3) {
                        System.out.println("inside4 exception" + e3);
                        respCode = ((HttpURLConnection) conn).getResponseCode();
                        System.out.println(respCode);

                    }
                }
            }

        }

        return body;
    }

    // HTTP POST request
//    public String sendPost(String newUrl, String urlParameter) throws Exception {
//
//        String url = newUrl;
//        URL obj = new URL(url);
//        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//
//        //add reuqest header
//        con.setRequestMethod("POST");
//        con.setRequestProperty("Host", "instagram.com");
//        con.setRequestProperty("Connection", "keep-alive");
//        con.setRequestProperty("Content-Length", "99");
//        con.setRequestProperty("Cache-Control", "max-age=0");
//        con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//        con.setRequestProperty("Origin", "https://instagram.com");
//        con.setRequestProperty("Upgrade-Insecure-Requests", "1");
//        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
//        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        con.setRequestProperty("Referer", "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize/%3Fclient_id%3D9d836570317f4c18bca0db6d2ac38e29%26redirect_uri%3Dhttp%3A//websta.me/%26response_type%3Dcode%26scope%3Dcomments%2Brelationships%2Blikes");
//        con.setRequestProperty("Accept-Encoding", "gzip, deflate");
//        con.setRequestProperty("Accept-Language", "en-GB,en-US;q=0.8,en;q=0.6");
//        con.setRequestProperty("Cookie", "mid=ViDPcwAEAAEZJIsQ69oXS1yIt-xM; sessionid=IGSC17d0985c7f6c412b9000d6a16f61d6090317ad3ce98e17bdddf6ccf5d546d301%3A1tQmHvyLs3AnxzEqGmQzN8Vr2POGAwwq%3A%7B%7D; csrftoken=cb5349ce32296df7c3b57ffd73c9713b");
////
//        String urlParameters = urlParameter;
//
//        // Send post request
//        con.setDoOutput(true);
//        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//        wr.writeBytes(urlParameters);
//        wr.flush();
//        wr.close();
//
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'POST' request to URL : " + url);
//        System.out.println("Post parameters : " + urlParameters);
//        System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        //print result
//        System.out.println(response.toString());
//        return response.toString();
//
//    }
    // HTTP POST request
//    public String sendPost(String newUrl, String urlParameter) throws Exception {
//
//        String url = newUrl;
//        URL obj = new URL(url);
//        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//
//        //add reuqest header
//        con.setRequestMethod("POST");
////        con.setRequestProperty("User-Agent", USER_AGENT);
////        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//        con.setRequestProperty("Host", "instagram.com");
//        con.setRequestProperty("Connection", "keep-alive");
//        con.setRequestProperty("Content-Length", "99");
//        con.setRequestProperty("Cache-Control", "max-age=0");
//        con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//        con.setRequestProperty("Origin", "https://instagram.com");
//        con.setRequestProperty("Upgrade-Insecure-Requests", "1");
//        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36");
//        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        con.setRequestProperty("Referer", "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize/%3Fclient_id%3D9d836570317f4c18bca0db6d2ac38e29%26redirect_uri%3Dhttp%3A//websta.me/%26response_type%3Dcode%26scope%3Dcomments%2Brelationships%2Blikes");
//        con.setRequestProperty("Accept-Encoding", "gzip, deflate");
//        con.setRequestProperty("Accept-Language", "en-GB,en-US;q=0.8,en;q=0.6");
//        con.setRequestProperty("Cookie", "mid=ViDPcwAEAAEZJIsQ69oXS1yIt-xM; sessionid=IGSC17d0985c7f6c412b9000d6a16f61d6090317ad3ce98e17bdddf6ccf5d546d301%3A1tQmHvyLs3AnxzEqGmQzN8Vr2POGAwwq%3A%7B%7D; csrftoken=cb5349ce32296df7c3b57ffd73c9713b");
//
//        String urlParameters = urlParameter;
//        String a[] = urlParameter.split("&");
//
//        // Send post request
//        con.setDoOutput(true);
//        try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
//            wr.writeBytes(a[0].split("=")[1]);
//            wr.writeBytes(a[1].split("=")[1]);
//            wr.writeBytes(a[2].split("=")[1]);
//            wr.flush();
//        }
//
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'POST' request to URL : " + url);
//        System.out.println("Post parameters : " + urlParameters);
//        System.out.println("Response Code : " + responseCode);
//
//        BufferedReader in = new BufferedReader(
//                new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        //print result
//        System.out.println(response.toString());
//        return response.toString();
//
//    }
    public static int generateRandomPort() {

        int portNo;
        Random random = new Random();
        int[] portList = new int[98];
        int portBegin = 1601;   //1601

        for (int k = 0; k < portList.length; k++) {
            portList[k] = portBegin;
            portBegin = portBegin + 1;
        }

        int num = random.nextInt(98);
        portNo = portList[num];
        return portNo;
    }

}
