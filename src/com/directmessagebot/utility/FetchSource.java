/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.directmessagebot.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Mendon Ashwini It has all the methods to fetch the response i.e with and without proxy. both get and post
 * request
 */
public class FetchSource {

    public String fetchPageSourceWithProxy(String newurl) throws IOException, InterruptedException {

        Random r = new Random();

//        ProxyImport obj = proxyList.get(r.nextInt(proxyList.size()));
        String ip = "195.154.161.103";
        int portno = generateRandomPort();
        String username = "mongoose";
        String password = "Fjh30fi";
//        if (obj.proxyLen> 2) {
//            username = obj.proxyUserName;
//            password = obj.proxyPassword;
//        }

        CredentialsProvider credsprovider = new BasicCredentialsProvider();
//
        if (portno == 1601) {
            portno = generateRandomPort();
        }

        System.out.println("IP ::: " + ip + "  Port ::: " + portno);
        credsprovider.setCredentials(
                new AuthScope(ip, portno),
                new UsernamePasswordCredentials(username, password));
        HttpHost proxy = new HttpHost(ip, portno);
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
            HttpGet httpget = new HttpGet(newurl);
            httpget.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httpget.addHeader("Accept-Encoding", "gzip, deflate");
            httpget.addHeader("Accept-Language", "en-US,en;q=0.5");
            httpget.addHeader("Connection", "keep-alive");

//            System.out.println("Response status" + httpget.getRequestLine());
            CloseableHttpResponse resp = httpclient.execute(httpget);
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
                    responsebody = fetchPageSourceWithProxySecond(newurl);
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
                responsebody = fetchPageSourceWithProxySecond(newurl);
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
        return responsebody;
    }

    public String fetchPageSourceWithProxySecond(String newurl) throws IOException {

        Random r = new Random();

//        ProxyImport obj = proxyList.get(r.nextInt(proxyList.size()));
//
//        String ip = obj.proxyIP;
//        int portno = Integer.parseInt(obj.proxyPort);
//        String username = "";
//        String password = "";
//        if (obj.proxyLen> 2) {
//            username = obj.proxyUserName;
//            password = obj.proxyPassword;
//        }
        String ip = "195.154.161.103";
        int portno = generateRandomPort();
        String username = "mongoose";
        String password = "Fjh30fi";

        CredentialsProvider credsprovider = new BasicCredentialsProvider();
//
        if (portno == 1601) {
            portno = generateRandomPort();
        }

        System.out.println("IP ::: " + ip + "  Port ::: " + portno);
        credsprovider.setCredentials(
                new AuthScope(ip, portno),
                new UsernamePasswordCredentials(username, password));
        HttpHost proxy = new HttpHost(ip, portno);
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
        try {
            HttpGet httpget = new HttpGet(newurl);
            httpget.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httpget.addHeader("Accept-Encoding", "gzip, deflate");
            httpget.addHeader("Accept-Language", "en-US,en;q=0.5");
            httpget.addHeader("Connection", "keep-alive");
            httpget.addHeader("Referer", "http://www.indiabix.com/aptitude/area/");
            CloseableHttpResponse resp = httpclient.execute(httpget);
            responsestatus = resp.getStatusLine().toString();
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

    public String fetchsourceWithoutProxy(String url) throws IOException {
        {
            try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
                HttpGet httpget = new HttpGet(url);

                ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                    public String handleResponse(
                            final HttpResponse response) throws ClientProtocolException, IOException {
                        int status = response.getStatusLine().getStatusCode();
                        if (status >= 200 && status < 300) {
                            HttpEntity entity = response.getEntity();
                            return entity != null ? EntityUtils.toString(entity) : null;
                        } else {
                            throw new ClientProtocolException("Unexpected response status: " + status);
                        }
                    }
                };
                String respBody = httpclient.execute(httpget, responseHandler);
//                System.out.println("respBody:::::::"+respBody);
                //System.out.println(httpclient.execute(httpget, responseHandler).getBytes());
                return respBody;
            }
        }
    }


    /* This method will generate the random port number for the proxies*/
    public int generateRandomPort() {

        int portNo;
        Random random = new Random();
        int[] portList = new int[98];
        int portBegin = 1601;

        for (int i = 0; i < portList.length; i++) {
            portList[i] = portBegin;
            portBegin = portBegin + 1;
        }

        int num = random.nextInt(98);
        portNo = portList[num];
        return portNo;
    }

    public String fetchPageSourceWithProxyPostForInstagramLogin(String newurl, String urlParameter) throws IOException, InterruptedException {

        String urlParameters[] = urlParameter.split("&");
        String cookieappend = "mid=Vi9eRQAEAAFNgFikvx56VKeZImgO; sessionid=IGSC27d3ebfcdd5bde48d768047ae0606d93f5753ceaed1286efbd621ea316fa0e4e\n%3AlxxMjdzAhtlibnrWWwRdCIToDBEzFxCS%3A%7B%7D; csrftoken=" + urlParameters[0];
//        ProxyImport obj = proxyList.get(r.nextInt(proxyList.size()));
//
//        String ip = obj.proxyIP;
//        int portno = Integer.parseInt(obj.proxyPort);
//        String username = "";
//        String password = "";
//        if (obj.proxyLen> 2) {
//            username = obj.proxyUserName;
//            password = obj.proxyPassword;
//        }

        String ip = "195.154.161.103";
        int portno = generateRandomPort();
        String username = "mongoose";
        String password = "Fjh30fi";

        CredentialsProvider credsprovider = new BasicCredentialsProvider();
//
        if (portno == 1601) {
            portno = generateRandomPort();
        }

        System.out.println("IP ::: " + ip + "  Port ::: " + portno);
        credsprovider.setCredentials(
                new AuthScope(ip, portno),
                new UsernamePasswordCredentials(username, password));
        HttpHost proxy = new HttpHost(ip, portno);
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
            HttpPost httppost = new HttpPost(newurl);
            httppost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httppost.addHeader("Accept-Encoding", "gzip, deflate");
            httppost.addHeader("Accept-Language", "en-US,en;q=0.5");
            httppost.addHeader("Connection", "keep-alive");
            httppost.addHeader("Cookie", cookieappend);
            httppost.addHeader("Host", "instagram.com");
            httppost.addHeader("Referer", "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize%3Fclient_id%3Dd9494686198d4dfeb954979a3e270e5e\n"
                    + "%26redirect_uri%3Dhttp%253A%252F%252Ficonosquare.com%26response_type%3Dcode%26scope%3Dlikes%2Bcomments\n"
                    + "%2Brelationships");

//            System.out.println("Response status" + httpget.getRequestLine());
            ArrayList<NameValuePair> postParameters;
            postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("csrfmiddlewaretoken", urlParameters[0]));
            postParameters.add(new BasicNameValuePair("username", urlParameters[1]));
            postParameters.add(new BasicNameValuePair("password", urlParameters[2]));
            httppost.setEntity(new UrlEncodedFormEntity(postParameters));
            CloseableHttpResponse resp = httpclient.execute(httppost);
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
                    responsebody = fetchPageSourceWithProxyPostForInstagramLoginSecond(newurl, urlParameter);
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
                System.out.println("=============================================================");
                responsebody = fetchPageSourceWithProxyPostForInstagramLoginSecond(newurl, urlParameter);
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
        return responsebody;
    }

    public String fetchPageSourceWithProxyPostForInstagramLoginSecond(String newurl, String urlParameter) throws IOException {

        String urlParameters[] = urlParameter.split("&");
        String cookieappend = "mid=Vi9eRQAEAAFNgFikvx56VKeZImgO; sessionid=IGSC27d3ebfcdd5bde48d768047ae0606d93f5753ceaed1286efbd621ea316fa0e4e\n"
                + "%3AlxxMjdzAhtlibnrWWwRdCIToDBEzFxCS%3A%7B%7D; csrftoken=" + urlParameters[0];
        System.out.println("cookieappend:::::"+cookieappend);
//        ProxyImport obj = proxyList.get(r.nextInt(proxyList.size()));
//
//        String ip = obj.proxyIP;
//        int portno = Integer.parseInt(obj.proxyPort);
//        String username = "";
//        String password = "";
//        if (obj.proxyLen> 2) {
//            username = obj.proxyUserName;
//            password = obj.proxyPassword;
//        }
        String ip = "195.154.161.103";
        int portno = 1601;
        String username = "mongoose";
        String password = "Fjh30fi";
        CredentialsProvider credsprovider = new BasicCredentialsProvider();
//
        if (portno == 1601) {
            portno = generateRandomPort();
        }

        System.out.println("IP ::: " + ip + "  Port ::: " + portno);
        credsprovider.setCredentials(
                new AuthScope(ip, portno),
                new UsernamePasswordCredentials(username, password));
        HttpHost proxy = new HttpHost(ip, portno);
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
        try {
            HttpPost httppost = new HttpPost(newurl);
            httppost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httppost.addHeader("Accept-Encoding", "gzip, deflate");
            httppost.addHeader("Accept-Language", "en-US,en;q=0.5");
            httppost.addHeader("Connection", "keep-alive");
            httppost.addHeader("Cookie", cookieappend);
            httppost.addHeader("Host", "instagram.com");
            httppost.addHeader("Referer", "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize%3Fclient_id%3Dd9494686198d4dfeb954979a3e270e5e\n"
                    + "%26redirect_uri%3Dhttp%253A%252F%252Ficonosquare.com%26response_type%3Dcode%26scope%3Dlikes%2Bcomments\n"
                    + "%2Brelationships");

            ArrayList<NameValuePair> postParameters;
            postParameters = new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("csrfmiddlewaretoken", urlParameters[0]));
            postParameters.add(new BasicNameValuePair("username", urlParameters[1]));
            postParameters.add(new BasicNameValuePair("password", urlParameters[2]));
            httppost.setEntity(new UrlEncodedFormEntity(postParameters));

            CloseableHttpResponse resp = httpclient.execute(httppost);
            responsestatus = resp.getStatusLine().toString();
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

    public String sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
//		System.out.println(response.toString());
        return response.toString();

    }

    public String sendPostWithoutProxy(String pageurl, String urlParameter) throws Exception {

        URL url = new URL(pageurl);
        String urlParameters = urlParameter;

        String forcook[] = urlParameter.split("&");
        String cookieappend = "mid=Vi9eRQAEAAFNgFikvx56VKeZImgO; sessionid=IGSC27d3ebfcdd5bde48d768047ae0606d93f5753ceaed1286efbd621ea316fa0e4e\n"
                + "%3AlxxMjdzAhtlibnrWWwRdCIToDBEzFxCS%3A%7B%7D; csrftoken=" + forcook[0];

        String body = "";
        URLConnection conn = null;
        try {
            //URLConnection conn = url.openConnection();
            conn = url.openConnection();
            //for instagram

            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Cookie", cookieappend);
            conn.setRequestProperty("Host", "instagram.com");
            conn.setRequestProperty("Referer", "https://instagram.com/accounts/login/?force_classic_login=&next=/oauth/authorize%3Fclient_id%3Dd9494686198d4dfeb954979a3e270e5e\n"
                    + "%26redirect_uri%3Dhttp%253A%252F%252Ficonosquare.com%26response_type%3Dcode%26scope%3Dlikes%2Bcomments%2Brelationships");
            //done
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

}
