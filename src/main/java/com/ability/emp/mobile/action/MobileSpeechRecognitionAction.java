package com.ability.emp.mobile.action;




import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.apache.poi.util.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ability.emp.util.voicerecognition.VoiceRecognitionUtil;
import com.baidu.aip.util.Util;

import javazoom.spi.mpeg.sampled.convert.MpegFormatConversionProvider;
import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;



@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/speech")
public class MobileSpeechRecognitionAction {
	
	
	@RequestMapping(value = "/recognition")
	@ResponseBody
    public Object speechReco(HttpServletRequest request) throws IOException {
		
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
       
        
       
      //使用输入流读取前台的file文件              
        InputStream is=file.getInputStream();

        //循环读取输入流文件内容，通过输出流将内容写入新文件
        OutputStream os=new FileOutputStream("C://1.mp3");  
        byte buffer[]=new byte[1024];  
        int cnt=0;  
        while((cnt=is.read(buffer))>0){  
            os.write(buffer, 0, cnt);  
        }  
        //关闭输入输出流
        os.close();
        is.close();  
        try {
            mp3Convertpcm();
            byte[] data = Util.readFileByBytes("C://testzp.pcm");
            JSONObject resultJson = VoiceRecognitionUtil.asr(data);
            System.out.println(resultJson.getJSONArray("result").get(0).toString());
            if (null != resultJson && resultJson.getInt("err_no") == 0) {
                return resultJson.getJSONArray("result").get(0).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * @Description MP3转换pcm
     *
     */
    public static void mp3Convertpcm() throws Exception {
//    	File mp3 = new File("D://test2.mp3");
//		File pcm = new File("D://testzp.pcm");
		AudioInputStream audioInputStream = getPcmAudioInputStream("C://1.mp3");
        AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File("C://testzp.pcm"));
    }

    

    private static AudioInputStream getPcmAudioInputStream(String mp3filepath) {
        File mp3 = new File(mp3filepath);
        AudioInputStream audioInputStream = null;
        AudioFormat targetFormat = null;
        try {
           // = null;
            MpegAudioFileReader mp = new MpegAudioFileReader();
            AudioInputStream in = mp.getAudioInputStream(mp3);
            AudioFormat baseFormat = in.getFormat();
            targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
                    baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
            audioInputStream = AudioSystem.getAudioInputStream(targetFormat, in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return audioInputStream;
    }

    






}
