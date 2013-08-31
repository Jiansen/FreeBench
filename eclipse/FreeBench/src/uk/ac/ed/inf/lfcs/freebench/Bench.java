package uk.ac.ed.inf.lfcs.freebench;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.io.IOUtils;

public final class Bench {
	public static RecordStore[] run(BenchConfiguration configuration){
		System.out.println("Benchmarking on: \n ");
		for(String url : configuration.multiurls){
			System.out.println("\t "+url);
		}
		
		RecordStore[] stores = new RecordStore[configuration.rounds];
		for (int i=0; i<configuration.rounds; i++){
			System.out.println("Round "+(i+1)+" ...");
			stores[i] = executeOnce(configuration, i+1);
		}
		
		return stores;
	}
	

	private static RecordStore executeOnce(BenchConfiguration configuration, int round){
//		Record r = new Record();
//System.out.println(configuration.concurrency());		
		ExecutorService executor = Executors.newFixedThreadPool(configuration.concurrency);
		RecordStore store = new RecordStore();
		List<GETRequest> requests = new LinkedList<GETRequest>();
		for(int i=0; i<configuration.concurrency; i++){requests.add(new GETRequest(configuration, "Round_"+round));}		
		
		try {
			List<Future<Record[]>> records = executor.invokeAll(requests);
			for (Future<Record[]> frs : records){
				try {
				  Record[] rs = frs.get();	
				  store.addRecordArray(rs);
			    } catch (InterruptedException | ExecutionException e) {
//			    	Record r = new Record();
//			    	r.responseRecieved=false;
//			    	store.addRecord(r);			    	
			    }
		    }			
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			executor.shutdown();				
		}
		return store;		
	}
	 
	private static class GETRequest implements Callable<Record[]>{
		BenchConfiguration configuration;
		String tag;

		public GETRequest(BenchConfiguration configuration, String tag) {
			this.configuration = configuration;
			this.tag = tag;
		}			
		@Override
	    public Record[] call() {
			Record[] records = new Record[configuration.requests_per_round];			
	    	for (int i=0; i<configuration.requests_per_round; i++){
	    		records[i] = fetchonce(tag,configuration);		    		
	    	}
	    	return records;
	    }
	}
		
	private static Record fetchonce(String tag, BenchConfiguration configuration){
		Record record = new Record(tag);
		InputStream is = null;
        try {					        	
            // fetch result        	
            URL url;
            
            int num_url =configuration.multiurls.length; 
            if(num_url==0){
            	url = new URL(configuration.url);            	
            }else{
            	int ri =  (new Random()).nextInt(num_url);
//System.out.println(ri);            	
            	url = new URL(configuration.multiurls[ri]);
            }            

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(configuration.read_timeout);
            conn.setConnectTimeout(configuration.connect_timeout);

            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            
//String host = url.getHost();
//InetAddress address = InetAddress.getByName(host);
//String ip = address.getHostAddress();            
            
//System.out.println("made connection to: " + ip);
            record.start = new Date();
            int responsecode = conn.getResponseCode();
            record.finish = new Date();
            if (responsecode == HttpURLConnection.HTTP_OK) {
                record.responseCode = responsecode;
                record.responseRecieved = true;
                
                if(configuration.download_content){
                	// Convert the InputStream into a string                
                	is = conn.getInputStream();
                	record.response = readIt(is);  
//                	System.out.println("hello");                	
                }else{
                	record.response = "";
                }
            }else{
            	System.out.println(responsecode);
            }
        }catch(Exception e) {
        	e.printStackTrace();
        	record.finish = new Date();
        	record.responseRecieved=false;
        }
//System.out.println(record.responseCode);	//        
//System.out.println(record.response);	//        
//System.out.println();
		return record;
	}
	// Reads an InputStream and converts it to a String.
	private static String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
		StringWriter writer = new StringWriter();
		IOUtils.copy(stream, writer, "UTF-8");
	    return writer.toString();
	}		
}
