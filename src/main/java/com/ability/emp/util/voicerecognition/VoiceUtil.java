package com.ability.emp.util.voicerecognition;



import org.json.JSONException;
import org.json.JSONObject;

import com.ability.emp.util.voicerecognition.common.ConnUtil;
import com.ability.emp.util.voicerecognition.common.DemoExceptionUtil;
import com.ability.emp.util.voicerecognition.common.TokenHolderUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class VoiceUtil {

    public static void main(String[] args) throws IOException, DemoExceptionUtil, JSONException {
        VoiceUtil demo = new VoiceUtil();
        // 填写下面信息
        String result = demo.run();
        System.out.println("识别结束：结果是：");
        System.out.println(result);
    }

    //  填写网页上申请的appkey 如 $apiKey="g8eBUMSokVB1BHGmgxxxxxx"
    private final String appKey = "4E1BG9lTnlSeIf1NQFlrSq6h";

    // 填写网页上申请的APP SECRET 如 $secretKey="94dc99566550d87f8fa8ece112xxxxx"
    private final String secretKey = "544ca4657ba8002e3dea3ac2f5fdd241";

    // 需要识别的文件
    private final String filename = "D://16k.pcm";

    // 文件格式
    private final String format = ".pcm";

    //  1537 表示识别普通话，使用输入法模型。1536表示识别普通话，使用搜索模型。 其它语种参见文档
    private final int dev_pid = 1537;

    private String cuid = "10962256";
 
 // 采样率固定值
    private final int rate = 16000;
	
    public boolean methodRaw = false; // 默认以json方式上传音频文件

    private final String url = "https://vop.baidu.com/server_api"; // 可以改为https

    public String run() throws IOException, DemoExceptionUtil, JSONException {
        TokenHolderUtil holder = new TokenHolderUtil(appKey, secretKey, TokenHolderUtil.TTS_SCOPE);
        holder.resfresh();
        String token = holder.getToken();
        String result = null;
        if (methodRaw) {
            result = runRawPostMethod(token);
        } else {
            result = runJsonPostMethod(token);
        }
        return result;
    }

    private String runRawPostMethod(String token) throws IOException, DemoExceptionUtil {
        String url2 = url + "?cuid=" + ConnUtil.urlEncode(cuid) + "&dev_pid=" + dev_pid + "&token=" + token;
        //System.out.println(url2);
        byte[] content = getFileContent(filename);
        HttpURLConnection conn = (HttpURLConnection) new URL(url2).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Content-Type", "audio/" + format + "; rate=" + rate);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.getOutputStream().write(content);
        conn.getOutputStream().close();
        String result = ConnUtil.getResponseString(conn);
        return result;
    }

    public String runJsonPostMethod(String token) throws DemoExceptionUtil, IOException, JSONException {

        byte[] content = getFileContent(filename);
        String speech = base64Encode(content);

        JSONObject params = new JSONObject();
        params.put("dev-pid", dev_pid);
        params.put("format", format);
        params.put("rate", rate);
        params.put("token", token);
        params.put("cuid", cuid);
        params.put("channel", "1");
        params.put("len", content.length);
        params.put("speech", speech);

        // System.out.println(params.toString());
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conn.setDoOutput(true);
        conn.getOutputStream().write(params.toString().getBytes());
        conn.getOutputStream().close();
        String result = ConnUtil.getResponseString(conn);
        return result;
    }

    private byte[] getFileContent(String filename) throws DemoExceptionUtil, IOException {
        File file = new File(filename);
        if (!file.canRead()) {
            System.err.println("文件不存在或者不可读: " + file.getAbsolutePath());
            throw new DemoExceptionUtil("file cannot read: " + file.getAbsolutePath());
        }
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            return ConnUtil.getInputStreamContent(is);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private String base64Encode(byte[] content) {
        /**
         Base64.Encoder encoder = Base64.getEncoder(); // JDK 1.8  推荐方法
         String str = encoder.encodeToString(content);
         **/

        char[] chars = Base64Util.encode(content); // 1.7 及以下，不推荐，请自行跟换相关库
        String str = new String(chars);

        return str;
    }

}
