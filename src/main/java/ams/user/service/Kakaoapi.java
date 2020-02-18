package ams.user.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Kakaoapi {
	private final static String CLIENT_ID = "718b94115712bb9ba0bde752892fae07";
	private final static String REDIRECT_URI = "http://localhost:8080/oauth";
	
	public JsonNode getAccessToken(String authorize_code, String state) throws UnsupportedEncodingException {
        final String RequestUrl = "https://kauth.kakao.com/oauth/token";
        final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
        postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
        postParams.add(new BasicNameValuePair("client_id", CLIENT_ID));
        postParams.add(new BasicNameValuePair("redirect_uri", REDIRECT_URI));
        postParams.add(new BasicNameValuePair("code", authorize_code));
        postParams.add(new BasicNameValuePair("state", state));
 
        final HttpClient client = HttpClientBuilder.create().build();
        final HttpPost post = new HttpPost(RequestUrl);
        JsonNode returnNode = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(postParams));
            final HttpResponse response = client.execute(post);
            ObjectMapper mapper = new ObjectMapper();
            returnNode = mapper.readTree(response.getEntity().getContent());
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	
        }
        return returnNode;
	}
	
	 public JsonNode getUserInfo(String accessToken) {
        final String RequestUrl = "https://kapi.kakao.com/v2/user/me";
        final HttpClient client = HttpClientBuilder.create().build();
        final HttpPost post = new HttpPost(RequestUrl);
 
        // add header
        post.addHeader("Authorization", "Bearer " + accessToken);
 
        JsonNode returnNode = null;
 
        try {
            final HttpResponse response = client.execute(post);
            final int responseCode = response.getStatusLine().getStatusCode();
            // JSON 형태 반환값 처리
            ObjectMapper mapper = new ObjectMapper();
            returnNode = mapper.readTree(response.getEntity().getContent());
 
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // clear resources
        }
        return returnNode;
    }
	 
	 public JsonNode postLogout(String accessToken) {
		 final String RequestUrl = "https://kapi.kakao.com/v1/user/logout";
	        final HttpClient client = HttpClientBuilder.create().build();
	        final HttpPost post = new HttpPost(RequestUrl);
	 
	        // add header
	        post.addHeader("Authorization", "Bearer " + accessToken);
	 
	        JsonNode returnNode = null;
	 
	        try {
	            final HttpResponse response = client.execute(post);
	            final int responseCode = response.getStatusLine().getStatusCode();
	            // JSON 형태 반환값 처리
	            ObjectMapper mapper = new ObjectMapper();
	            returnNode = mapper.readTree(response.getEntity().getContent());
	            
	            System.out.println(returnNode);
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            // clear resources
	        }
	        return returnNode;
	 }
	 
	 public JsonNode postSignout(String accessToken) {
		 final String RequestUrl = "https://kapi.kakao.com/v1/user/unlink";
	        final HttpClient client = HttpClientBuilder.create().build();
	        final HttpPost post = new HttpPost(RequestUrl);
	 
	        // add header
	        post.addHeader("Authorization", "Bearer " + accessToken);
	 
	        JsonNode returnNode = null;
	 
	        try {
	            final HttpResponse response = client.execute(post);
	            final int responseCode = response.getStatusLine().getStatusCode();
	            // JSON 형태 반환값 처리
	            ObjectMapper mapper = new ObjectMapper();
	            returnNode = mapper.readTree(response.getEntity().getContent());
	            
	            System.out.println(returnNode);
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            // clear resources
	        }
	        return returnNode;
	 }
	 
	 public JsonNode getAddressInfo(JsonNode location) {
		 final String RequestUrl = "https://dapi.kakao.com/v2/local/geo/coord2address.json?";
		 final HttpClient client = HttpClientBuilder.create().build();
		 final List<NameValuePair> getParams = new ArrayList<NameValuePair>();
		 getParams.add(new BasicNameValuePair("x", location.get("x").asText()));
		 getParams.add(new BasicNameValuePair("y", location.get("y").asText()));
		 
		 final HttpGet get = new HttpGet(RequestUrl + URLEncodedUtils.format(getParams, "UTF-8"));
		 
		 get.addHeader("Authorization", "KakaoAK " + CLIENT_ID);
		 
		 JsonNode returnNode = null;
		 
		 try {
			final HttpResponse response = client.execute(get);
			final int responseCode = response.getStatusLine().getStatusCode();
			// JSON 형태 반환값 처리
            ObjectMapper mapper = new ObjectMapper();
		    returnNode = mapper.readTree(response.getEntity().getContent());
		    
		    System.out.println(returnNode);
		 } catch (ClientProtocolException e) {
		    e.printStackTrace();
		 } catch (IOException e) {
		    e.printStackTrace();
		 } finally {
		    // clear resources
		 }
		 
		 return returnNode;
		 
	 }
 }