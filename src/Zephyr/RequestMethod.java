package Zephyr;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

@SuppressWarnings("deprecation")
public class RequestMethod {

    public static String sendGET(String uriStr) throws URISyntaxException {
        URI uri= new URI(uriStr);

        HttpResponse response = null;
        @SuppressWarnings("resource")
        HttpClient restClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setHeader("Authorization","Basic " + JiraConstatnts.auth);
        httpGet.setHeader("Content-Type", "application/json");

        try {
            response = restClient.execute(httpGet);
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        int statusCode = response.getStatusLine().getStatusCode();
        String string1 = null;
        if (statusCode >= 200 && statusCode < 300) {
            try {
                HttpEntity entity = response.getEntity();
                string1= EntityUtils.toString(entity);

            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {
            try {
                throw new ClientProtocolException("Unexpected response status: " + statusCode);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            }
        }

        return string1;
    }

    public static String sendPost(String uriStr, JSONObject postBody) throws URISyntaxException, UnsupportedEncodingException {
        HttpResponse response = null;
        @SuppressWarnings("resource")
        HttpClient restClient = new DefaultHttpClient();
        URI uri = new URI(uriStr);
        StringEntity postJSON = null;
        postJSON = new StringEntity(postBody.toString());

        HttpPost postrequest = new HttpPost(uri);
        postrequest.addHeader("Authorization","Basic " + JiraConstatnts.auth);
        postrequest.addHeader("Content-Type", "application/json");
        postrequest.setEntity(postJSON);

        try {
            response = restClient.execute(postrequest);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String responseString = null;
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode >= 200 && statusCode < 300) {
            HttpEntity entity = response.getEntity();
            try {
                responseString = EntityUtils.toString(entity);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {
                throw new ClientProtocolException("Unexpected response status: " + statusCode);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            }
        }

        return responseString;
    }

    public static String sendPUT(String uriStr, JSONObject putBody) throws URISyntaxException, UnsupportedEncodingException {
        HttpResponse response = null;
        HttpClient restClient = new DefaultHttpClient();
        URI uri = new URI(uriStr);
        StringEntity putJSON = null;
        putJSON = new StringEntity(putBody.toString());

        HttpPut putrequest = new HttpPut(uri);
        putrequest.addHeader("Authorization","Basic " + JiraConstatnts.auth);
        putrequest.addHeader("Content-Type", "application/json");
        putrequest.setEntity(putJSON);

        try {
            response = restClient.execute(putrequest);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String responseString = null;
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode >= 200 && statusCode < 300) {
            HttpEntity entity = response.getEntity();
            try {
                responseString = EntityUtils.toString(entity);
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            try {
                throw new ClientProtocolException("Unexpected response status: " + statusCode);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            }
        }

        return responseString;

    }

}
