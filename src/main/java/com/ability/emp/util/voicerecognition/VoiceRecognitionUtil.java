package com.ability.emp.util.voicerecognition;

import java.io.IOException;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.aip.speech.AipSpeech;

public class VoiceRecognitionUtil {
	
    
    private final static String appKey = "4E1BG9lTnlSeIf1NQFlrSq6h";

    private final static String secretKey = "544ca4657ba8002e3dea3ac2f5fdd241";
    
    private static String cuid = "10962256";
    
    private final static String format = "pcm";

    //1537 表示识别普通话，使用输入法模型。1536表示识别普通话，使用搜索模型。 其它语种参见文档
    private final static int dev_pid = 1737;

    
    public static JSONObject asr(byte[] data) throws IOException, JSONException
    {
    	// 初始化一个AipSpeech
    	AipSpeech  client = new AipSpeech(cuid, appKey, secretKey);
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("dev_pid", dev_pid);
        JSONObject asrRes2 = client.asr(data, format, 16000,map);
       
        return asrRes2;

    }

}
