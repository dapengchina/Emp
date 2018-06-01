package com.ability.emp.util;

import org.json.JSONObject;

import com.ability.emp.util.speechsynthesis.common.ConnUtil;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 使用登录凭证 code 获取 session_key 和 openid
 * 其中 session_key 是对用户数据进行加密签名的密钥
 * 为了自身应用安全，session_key 不应该在网络上传输
 * 
 * @author Devin
 * @since 2018-3-23
 */
public class LoginUtil {

	//小程序唯一标识
    public static final String APPID = "wx78d9aed7f986807f";
    //小程序的 app secret
    public static final String SECRET = "0cb0d7fb35ab8772ff7884da70c459b4";
    
    
    //填写为 authorization_code
    public static final String GRANT_TYPE ="authorization_code";
    //获取accesstoken的granttype
    public static final String GRANT_TYPE_ACC = "client_credential";

    //URL
    private static final String url = "https://api.weixin.qq.com/sns/jscode2session";
    //获取access_token Url
    private static final String GET_ACCESS_TOKENURL = "https://api.weixin.qq.com/cgi-bin/token";
    //发送模块消息Url
    private static final String TEMPLETE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send";
    
    
    public static String resfresh(String JS_CODE) throws Exception {
        String getOpenidURL = url + "?appid="+APPID
                + "&secret=" + SECRET + "&js_code=" + JS_CODE
                + "&grant_type="+GRANT_TYPE;
        
        URL url = new URL(getOpenidURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        String result = ConnUtil.getResponseString(conn);
        String session_key = parseJson(result);
        return session_key;
    }

    
    private static String parseJson(String result) throws Exception {
        JSONObject json = new JSONObject(result);
        
        @SuppressWarnings("unused")
		String openid = json.getString("openid");
        String session_key = json.getString("session_key");
        return session_key;
    }
    
    /**
     * 获取access_token
     * @throws Exception 
     */
    public static String getAccessToken() throws Exception{
    	String getAccessTokenURL = GET_ACCESS_TOKENURL + "?grant_type="+GRANT_TYPE_ACC
                + "&appid=" + APPID + "&secret=" + SECRET;
    	URL url = new URL(getAccessTokenURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        String result = ConnUtil.getResponseString(conn);
        JSONObject json = new JSONObject(result);
        String access_token = json.getString("access_token");
        //System.out.println(access_token);
    	return access_token;
    }
    /**
     * 发送模块消息
     * @param args
     * @throws Exception
     */
    public static String sendTempleteMessage(String temp) throws Exception{
    	String access_token = getAccessToken();
    	String sendTemMessageUrl = TEMPLETE_MESSAGE_URL + "?access_token="+access_token;
    	URL url = new URL(sendTemMessageUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    	conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");
        // 设置文件类型:
        conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
        // 设置接收类型否则返回415错误
        //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
        conn.setRequestProperty("accept","application/json");
        byte[] writebytes = temp.getBytes();
        // 设置文件长度
        conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
        
        OutputStream outwritestream = conn.getOutputStream();
        outwritestream.write(temp.getBytes("UTF-8"));
        outwritestream.flush();
        outwritestream.close();
        String result = ConnUtil.getResponseString(conn);
        return result;
    }
//    public static void main(String args[]) throws Exception{
//    	getAccessToken();
//    }
}
