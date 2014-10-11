package ch.hackathon.eventplaner.logic;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.widget.Toast;

/**
 * Interface to the API (on the server)
 */
public class ApiConnector {
	private static final String baseurl = "";
	
	public HttpResponse sendRequest (String path, Context context) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGetRequest = new HttpGet(baseurl + path);
		
		try {
			return httpClient.execute(httpGetRequest);
		} catch (ClientProtocolException e) {
			Toast.makeText(context, "NETWORK ERROR", Toast.LENGTH_SHORT).show();
			return null;
		} catch (IOException e) {
			Toast.makeText(context, "IO ERROR", Toast.LENGTH_SHORT).show();
			return null;
		}
	}
}
