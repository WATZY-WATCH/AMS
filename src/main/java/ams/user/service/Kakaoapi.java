package ams.user.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class Kakaoapi {
	private final static String CLIENT_ID = "";
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
	
	 public HashMap<String, Object> getUserInfo (String access_Token) {
	       
	       //    �슂泥��븯�뒗 �겢�씪�씠�뼵�듃留덈떎 媛�吏� �젙蹂닿� �떎瑜� �닔 �엳湲곗뿉 HashMap���엯�쑝濡� �꽑�뼵
	       HashMap<String, Object> userInfo = new HashMap<>();
	       String reqURL = "https://kapi.kakao.com/v2/user/me";
	       try {
	           URL url = new URL(reqURL);
	           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	           conn.setRequestMethod("POST");
	           
	           //    �슂泥��뿉 �븘�슂�븳 Header�뿉 �룷�븿�맆 �궡�슜
	           conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	           
	           int responseCode = conn.getResponseCode();
	           System.out.println("responseCode : " + responseCode);
	           
	           BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF8"));
	           
	           String line = "";
	           String result = "";
	           
	           while ((line = br.readLine()) != null) {
	               result += line;
	           }
	           System.out.println("response body : " + result);
	           JsonParser parser = new JsonParser();
	           JsonElement element = parser.parse(result);
	           
	           JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
	           JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
	           
	           String nickname = properties.getAsJsonObject().get("nickname").getAsString();
	           String email = kakao_account.getAsJsonObject().get("email").getAsString();
	           
	           userInfo.put("nickname", nickname);
	           userInfo.put("email", email);
	           
	       } catch (IOException e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
	       }
	       
	       return userInfo;
	   }

}