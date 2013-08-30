package uk.ac.ed.inf.lfcs.freebench;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.google.gson.Gson;

public class JSONBenchConfiguration extends BenchConfiguration{

//	@Override 
//	boolean resloveDomainName(){
//		return true;
//	}
	static public BenchConfiguration parse(String filepath) throws IOException{
    	File f = new File(filepath);        	
        byte[] bytes = Files.readAllBytes(f.toPath());
        String content = new String(bytes,"UTF-8");
        
        Gson gson = new Gson();
        BenchConfiguration config = gson.fromJson(content, BenchConfiguration.class);
        return config;                    
	}
}
