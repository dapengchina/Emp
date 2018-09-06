package com.ability.emp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	
	public static String replaceBlank(String str,String match) {
		
		        String dest = "";
		
		        if (str!=null) {
		
		            //Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		        	Pattern p = Pattern.compile(match);
		
		            Matcher m = p.matcher(str);
		
		            dest = m.replaceAll("");
		
		        }
		
		        return dest;
		
		    }
	
		    

}
