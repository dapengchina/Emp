package com.ability.emp.mobile.action;




import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.*;
import com.ability.emp.util.voicerecognition.VoiceRecognitionUtil;
import com.baidu.aip.util.Util;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;



@CrossOrigin//解决跨域请求
@Controller
@RequestMapping("/mobile/speech")
public class MobileSpeechRecognitionAction {
	
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	
	private File mp3DirFile;
	
	private boolean mp3bFile;
	
	private String mp3path;
	
	
    private static File pcmDirFile;
	
	private static boolean pcmbFile;
	
	private static String pcmpath;
	
	
	@RequestMapping(value = "/recognition")
	@ResponseBody
    public Object speechReco(HttpServletRequest request) throws IOException {
		
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
        
        /**
         * 创建文件夹
         */
        mp3DirFile = new File("C:/upload_mp3/"+sdf2.format(new Date()));
        mp3bFile   = mp3DirFile.exists();
        if( mp3bFile == true )
        {
          //System.out.println("The folder exists.");
        }
        else
        {
          //System.out.println("The folder do not exist,now trying to create a one...");
          mp3bFile = mp3DirFile.mkdirs();
        }
        mp3path = "C:/upload_mp3/"+sdf2.format(new Date())+"/"+(sdf.format(new Date()))+".mp3";
        //使用输入流读取前台的file文件              
        InputStream is=file.getInputStream();
        //循环读取输入流文件内容，通过输出流将内容写入新文件
        OutputStream os=new FileOutputStream(mp3path);  
        byte buffer[]=new byte[1024];  
        int cnt=0;  
        while((cnt=is.read(buffer))>0){  
            os.write(buffer, 0, cnt);  
        }  
        //关闭输入输出流
        os.close();
        is.close();  
        try {
            mp3Convertpcm(mp3path);
            byte[] data = Util.readFileByBytes(pcmpath);
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
    public static void mp3Convertpcm(String mp3path) throws Exception {
    	/**
         * 创建文件夹
         */
        pcmDirFile = new File("C:/convert_pcm/"+sdf2.format(new Date()));
        pcmbFile   = pcmDirFile.exists();
        if( pcmbFile == true )
        {
          //System.out.println("The folder exists.");
        }
        else
        {
          //System.out.println("The folder do not exist,now trying to create a one...");
          pcmbFile = pcmDirFile.mkdirs();
        }
        pcmpath = "C:/convert_pcm/"+sdf2.format(new Date())+"/"+(sdf.format(new Date()))+".pcm";
		AudioInputStream audioInputStream = getPcmAudioInputStream(mp3path);
        AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File(pcmpath));
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
