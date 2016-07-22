package ru.stqa.pft.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {
    private CloseableHttpClient httpClient;
    private ApplicationManager app;

    public HttpSession(ApplicationManager app) {
        this.app = app;
        httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    public boolean logIn(String username, String password) throws IOException {
        //creating empty POST request
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");
        //Input parameters
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("secure_session", "on"));
        params.add(new BasicNameValuePair("return", "index.php"));
        //Encode the input params and add to the POST request
        post.setEntity(new UrlEncodedFormEntity(params));
        //Send the POST request and save server response
        CloseableHttpResponse response = httpClient.execute(post);
        String body = getTextFrom(response);
        //Check that user is successfully loged in
        return body.contains(String.format("<span class=\"italic\">%s</span>", username));
    }

    private String getTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }

    //
    public boolean isLoggedInAs(String username) throws IOException {
        //create GET request
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");
        //Send the GET request and save its response
        CloseableHttpResponse response = httpClient.execute(get);
        String body = getTextFrom(response);
        return body.contains(String.format("<span class=\"italic\">%s</span>", username));
    }
}
