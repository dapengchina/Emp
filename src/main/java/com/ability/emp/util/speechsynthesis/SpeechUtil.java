package com.ability.emp.util.speechsynthesis;


import com.ability.emp.util.speechsynthesis.common.ConnUtil;
import com.ability.emp.util.speechsynthesis.common.TokenHolderUtil;

/**
 * 语音合成
 * @author Devin
 * @since 2018-3-21
 */
public class SpeechUtil {
	
	//填写网页上申请的appkey
    private final String appKey = "4E1BG9lTnlSeIf1NQFlrSq6h";
    //填写网页上申请的APP SECRET
    private final String secretKey = "544ca4657ba8002e3dea3ac2f5fdd241";
    //text的内容为使用百度语音合成的urlencode,utf-8 编码
    //private final String text = "sun";

    // 发音人选择, 0为普通女声，1为普通男生，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声
    private final int per = 4;
    // 语速，取值0-9，默认为5中语速
    private final int spd = 5;
    // 音调，取值0-9，默认为5中语调
    private final int pit = 5;
    // 音量，取值0-9，默认为5中音量
    private final int vol = 9;

    public final String url = "https://tsn.baidu.com/text2audio"; //可以使用https

    //百度应用ID
    private String cuid = "10962256";

//    public static void main(String[] args) throws Exception {
//    	convert("It's been more than 2 years");
//    }
    public static String convert(String text) throws Exception{
    	return (new SpeechUtil()).run(text);
    }

    private String run(String text) throws Exception {
        TokenHolderUtil holder = new TokenHolderUtil(appKey, secretKey, TokenHolderUtil.ASR_SCOPE);
        holder.resfresh();
        String token = holder.getToken();

        String url2 = url + "?tex=" + ConnUtil.urlEncode(text);
        url2 += "&per=" + per;
        url2 += "&spd=" + spd;
        url2 += "&pit=" + pit;
        url2 += "&vol=" + vol;
        url2 += "&cuid=" + cuid;
        url2 += "&tok=" + token;
        url2 += "&lan=zh&ctp=1";
        //System.out.println(url2); // 反馈请带上此url，浏览器上可以测试
        return url2;//此url2用来在小程序中调用，用来播放语音
        
        /**
         * 以下代码为往磁盘写入音频数据,小程序无须此操作
         */
        /*HttpURLConnection conn = (HttpURLConnection) new URL(url2).openConnection();
        conn.setConnectTimeout(5000);
        String contentType = conn.getContentType();
        if (contentType.contains("mp3")) {
            byte[] bytes = ConnUtil.getResponseBytes(conn);
            File file = new File("D:/speech/result.mp3"); //打开mp3文件即可播放
            //System.out.println( file.getAbsolutePath());
            FileOutputStream os = new FileOutputStream(file);
            os.write(bytes);
            os.close();
            System.out.println("mp3 file write to " + file.getAbsolutePath());
        } else {
            System.err.println("ERROR: content-type= " + contentType);
            String res  = ConnUtil.getResponseString(conn);
            System.err.println(res);
        }*/
    }
}
