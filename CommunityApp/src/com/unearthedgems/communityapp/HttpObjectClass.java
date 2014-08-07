package com.unearthedgems.communityapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

public class HttpObjectClass extends Activity{
private	ConnectivityManager connMngr;
NetworkInfo netInfo ;
private boolean isConnection;

public void HttpObjClass(){
	connMngr= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	netInfo = connMngr.getActiveNetworkInfo();
	if (netInfo==null){
		networkConnectionAlert();
	}
	else{
		
	}


}

public void networkConnectionAlert() {
	// TODO Auto-generated method stub
	
}

private class Send extends AsyncTask<InfoClass, Void, String> {
	InputStream inputstream =null;
	@Override
	protected String doInBackground(InfoClass...param) {
		InfoClass info = new InfoClass();
		 BufferedReader in = null;
	        String baseUrl = "http://web.unearthedgems.com/community/";
	        
	        // 1. create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(baseUrl);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            try {
				jsonObject.accumulate("latitude", info.getLat());
				  jsonObject.accumulate("lontitude", info.getLon());
		            jsonObject.accumulate("description", info.getDesc());
		            
		            
		            json= jsonObject.toString();
		            
		            StringEntity se = new StringEntity(json);
		            
		            httpPost.setEntity(se);
		            
		            // 7. Set some headers to inform server about the type of the content   
		            httpPost.setHeader("Accept", "application/json");
		            httpPost.setHeader("Content-type", "application/json");
		            
		         // 8. Execute POST request to the given URL
		            HttpResponse httpResponse = httpclient.execute(httpPost);

		            
		         // 9. receive response as inputStream
		            inputstream = httpResponse.getEntity().getContent();
		            
		            if(inputstream != null)
		                result = convertInputStreamToString(inputstream);
		            else
		                result = "Did not work!";


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("httpObject","Failure in json construction");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("json", "error in encoding process");
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d("httpResponse", "error in the sending process");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          

		return null;
	}
}
	
}

