package com.wings.intentbased.Intent.services;
import java.awt.List;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.glassfish.jersey.client.ClientConfig;
import org.omg.CORBA.portable.OutputStream;

import com.google.gson.Gson;

public class Send_HTTP_Post_Request {
	
	public Send_HTTP_Post_Request() {
		
	}
	
	public void post(Pojo pojo) {
		Pojo response = null;	
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		URI baseURI = UriBuilder.fromUri("http://192.168.12.82:8080/BSAgent/").build();
		WebTarget service = client.target(baseURI);
		response = service
				.path("rest")
				.path("config")
				.path("action")
				.request(MediaType.APPLICATION_JSON)
				.put(Entity
						.entity(pojo, MediaType.APPLICATION_JSON),
						Pojo.class);
	}
}