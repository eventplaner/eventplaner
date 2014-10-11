package ch.hackathon.eventplaner.logic;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;

/**
 * Interface to the API (on the server)
 */
public class ApiConnector {
	private static final String baseurl = "http://jan-bucher.ch:4000/api";
	
	public HttpResponse sendGetRequest (String path, Context context) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGetRequest = new HttpGet(baseurl + path);
		
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			return httpClient.execute(httpGetRequest);
		} catch (ClientProtocolException e) {
			Toast.makeText(context, "NETWORK ERROR", Toast.LENGTH_SHORT).show();
			return null;
		} catch (IOException e) {
			Toast.makeText(context, "IO ERROR", Toast.LENGTH_SHORT).show();
			return null;
		} catch (Exception ex) {
			Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
			return null;
		}
	}
	
	public HttpResponse sendPostRequest (String path, List<NameValuePair> postData, Context context) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPostRequest = new HttpPost(baseurl + path);
		
		try {
			httpPostRequest.setEntity(new UrlEncodedFormEntity(postData));
			return httpClient.execute(httpPostRequest);
		} catch (ClientProtocolException e) {
			Toast.makeText(context, "NETWORK ERROR", Toast.LENGTH_SHORT).show();
			return null;
		} catch (IOException e) {
			Toast.makeText(context, "IO ERROR", Toast.LENGTH_SHORT).show();
			return null;
		}
	}
	
	public JSONArray getJsonFromGet (String path, Context context) {
		HttpResponse response = sendGetRequest(path, context);
		if (response == null) {
			return null;
		}
		HttpEntity entity = response.getEntity();
		String responseString = "";
		try {
			responseString = EntityUtils.toString(entity, "UTF-8");
			return new JSONArray(responseString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
