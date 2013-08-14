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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.io.IOUtils;

public final class Bench {
	public static RecordStore run(BenchConfiguration configuration){
		System.out.println("Running Running");
		
		RecordStore store = executeOnce(configuration);
//		
//		System.out.println("begin:"+r.start.getTime());
//		System.out.println("end:"+r.end.getTime());
//		System.out.println("elapse:"+(r.end.getTime()-r.start.getTime()));		
		
		return store;
	}
	

	private static RecordStore executeOnce(BenchConfiguration configuration){
//		Record r = new Record();
//System.out.println(configuration.concurrency());		
		ExecutorService executor = Executors.newFixedThreadPool(configuration.concurrency());
		RecordStore store = new RecordStore();
		List<GETRequest> requests = new LinkedList<GETRequest>();
		for(int i=0; i<configuration.concurrency(); i++){requests.add(new GETRequest(configuration));}		
		
		try {
			List<Future<Record[]>> records = executor.invokeAll(requests);
			for (Future<Record[]> frs : records){
				try {
				Record[] rs = frs.get();
//				System.out.println(r);
//				System.out.println("begin:"+r.start.getTime());
//				System.out.println("end:"+r.end.getTime());
//				System.out.println("elapse:"+(r.end.getTime()-r.start.getTime()));		
//				System.out.println();			
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

		public GETRequest(BenchConfiguration configuration) {
			this.configuration = configuration;
		}			
		@Override
	    public Record[] call() {
			Record[] records = new Record[configuration.requests_per_round()];			
	    	for (int i=0; i<configuration.requests_per_round(); i++){
	    		records[i] = fetchonce("haha",configuration);		    		
	    	}
	    	return records;
	    }
	}
		
	private static Record fetchonce(String tag, BenchConfiguration configuration){
		Record record = new Record(tag);
		InputStream is = null;
        try {					        	
            // fetch result
            URL url = new URL(configuration.url());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);

            conn.setDoOutput(true);
            conn.setRequestMethod("GET");

            record.start = new Date();
            int responsecode = conn.getResponseCode();
            record.responseCode = responsecode;
            record.end = new Date();
            if (responsecode == HttpURLConnection.HTTP_OK) {
            	// Convert the InputStream into a string
            	is = conn.getInputStream();
            	record.response = readIt(is);            	
            }
        }catch(Exception e) {
        	e.printStackTrace();
        	record.end = new Date();
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
