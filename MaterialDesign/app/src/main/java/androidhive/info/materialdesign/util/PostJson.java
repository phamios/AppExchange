package androidhive.info.materialdesign.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidhive.info.materialdesign.model.App;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.os.StrictMode;

/**
 * Created by sonpx on 5/5/15.
 */
public class PostJson {

    //Insert vào bảng tbl_userapp khi chưa có dữ liệu.
    public static void PostNow(String url,String userid, String appname, String coins) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);


        // Creating HTTP client
        HttpClient httpClient = new DefaultHttpClient();
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(url);

        // Building post parameters
        // key and value pair
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
        nameValuePair.add(new BasicNameValuePair("userid",userid));
        nameValuePair.add(new BasicNameValuePair("appname",appname));
        nameValuePair.add(new BasicNameValuePair("coins", coins));

        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // writing error to Log
            e.printStackTrace();
        }

        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);

            // writing response to log
            Log.d("Http Response:", response.toString());
        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();

        }
    }

    //Kiểm tra IMEI có tồn tại trên hệ thống chưa
    public static String CheckExit(String url){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // Creating HTTP client
        HttpClient httpClient = new DefaultHttpClient();
        BufferedReader in = null;

        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(url);
        String body = null;
        // Building post parameters
        // key and value pair
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(6);
        nameValuePair.add(new BasicNameValuePair("imei","test"));

        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // writing error to Log
            e.printStackTrace();
        }

        String output = null;

        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            output = EntityUtils.toString(httpEntity);

            Log.d(">>>>>>>>>>>>>>",output);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

        return output;
       // return response.toString();
    }


    //Đăng ký User
    public static String CheckorReg(String url,String imei,String carriel, String number,String operator, String manufacturer, String model) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);


        // Creating HTTP client
        HttpClient httpClient = new DefaultHttpClient();
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(url);

        // Building post parameters
        // key and value pair
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(6);
        nameValuePair.add(new BasicNameValuePair("imei",imei));
        nameValuePair.add(new BasicNameValuePair("carriel",carriel));
        nameValuePair.add(new BasicNameValuePair("number",number));
        nameValuePair.add(new BasicNameValuePair("operator",operator));
        nameValuePair.add(new BasicNameValuePair("manufacturer",manufacturer));
        nameValuePair.add(new BasicNameValuePair("model",model));

        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // writing error to Log
            e.printStackTrace();
        }
        String output = null;
        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            output = EntityUtils.toString(httpEntity);
        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
        }
        return output;
    }


    //Check History
    public static String History(String url,String imei) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);


        // Creating HTTP client
        HttpClient httpClient = new DefaultHttpClient();
        // Creating HTTP Post
        HttpPost httpPost = new HttpPost(url);

        // Building post parameters
        // key and value pair
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(6);
        nameValuePair.add(new BasicNameValuePair("user_imei",imei));
        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // writing error to Log
            e.printStackTrace();
        }
        String output = null;

        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            output = EntityUtils.toString(httpEntity);
            Log.d(">>>>>>>>>>>>>>",output);
        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();

        }

        return output;
    }






}
