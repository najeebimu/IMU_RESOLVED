package com.softorea.schoolsen.lists;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ServiceHandler {

    public final static int GET = 1;
    public final static int POST = 2;
    static String response = null;

    public ServiceHandler() {

    }

    public String makeServiceCall(String url, int method) {
        return this.makeServiceCall(url, method, null);
    }

    public String makeServiceCall(String url, int method,
                                  List<NameValuePair> params) {
        try {

            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;


            // called if post
            if (method == POST) {
                HttpPost httpPost = new HttpPost(url);

                HttpParams httpParameters = new BasicHttpParams();
                int timeOut = 10000;
                HttpConnectionParams.setConnectionTimeout(httpParameters, timeOut);
                DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

                httpPost.addHeader("Cache-Control", "no-cache");
                if (params != null) {
                    httpPost.setEntity(new UrlEncodedFormEntity(params));
                }

                httpResponse = httpClient.execute(httpPost);

                //called if get
            } else if (method == GET) {

                if (params != null) {
                    String paramString = URLEncodedUtils
                            .format(params, "utf-8");
                    url += "?" + paramString;
                }
                HttpGet httpGet = new HttpGet(url);
                HttpParams httpParameters = new BasicHttpParams();
                int timeOut = 10000;
                HttpConnectionParams.setConnectionTimeout(httpParameters, timeOut);
                DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

                httpGet.addHeader("Cache-Control", "no-cache");
                httpResponse = httpClient.execute(httpGet);

            }
            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;

    }
}
