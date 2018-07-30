package com.ability.emp.util.voicerecognition;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.util.Util;

public class VoiceRecognitionUtil {
	
	
    //  填写网页上申请的appkey 如 $apiKey="g8eBUMSokVB1BHGmgxxxxxx"
    private final static String appKey = "4E1BG9lTnlSeIf1NQFlrSq6h";

    // 填写网页上申请的APP SECRET 如 $secretKey="94dc99566550d87f8fa8ece112xxxxx"
    private final static String secretKey = "544ca4657ba8002e3dea3ac2f5fdd241";

    // 需要识别的文件
    private final String filename = "D://16k.pcm";

    // 文件格式
    private final String format = ".pcm";

    //  1537 表示识别普通话，使用输入法模型。1536表示识别普通话，使用搜索模型。 其它语种参见文档
    private final int dev_pid = 1537;

    private static String cuid = "10962256";
    
    public static void main(String args[]) throws JSONException{
    	// 初始化一个AipSpeech
    	AipSpeech  client = new AipSpeech(cuid, appKey, secretKey);
        // 调用接口
        JSONObject res = client.asr("D:\\16k.pcm", "pcm", 16000, null);
        System.out.println(res.toString(2));
    }
    
    public void asr(AipSpeech client) throws IOException
    {
        // 对本地语音文件进行识别
        String path = "D:\16k.pcm";
        JSONObject asrRes = client.asr(path, "pcm", 16000, null);
        System.out.println(asrRes);

        // 对语音二进制数据进行识别
        byte[] data = Util.readFileByBytes(path);     //readFileByBytes仅为获取二进制数据示例
        JSONObject asrRes2 = client.asr(data, "pcm", 16000, null);
        System.out.println(asrRes2);

    }

}
