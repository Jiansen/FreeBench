package uk.ac.ed.inf.lfcs.freebench;

import java.util.Date;

public class RecordReporter {	
	public static void reportStore(RecordStore rs){
		Date begin = rs.beginDate();
		Date end = rs.endDate();	
		System.out.println();
		System.out.println("Begin at: "+ begin);
		System.out.println("Finish at: "+ end);		
		System.out.println("Elapse: "+ (end.getTime()-begin.getTime() ) + " ms");		
		System.out.println();
		
		System.out.println("Number of Requests: "+ rs.recordes.size());		
		int s = rs.successrequests();
		int f = rs.failedrequests();
		System.out.println("Success Requests: "+s);
		System.out.println("Failed Requests: "+f);
		System.out.println("Availiability: "+s*100.0/(s+f)+" %");
		System.out.println("Throughput: "+rs.throughput()+"  trans/sec");		
		
		System.out.println();
		
		//
		System.out.println("Longest Response Time: "+rs.maxConnectionTime()+" ms");
		System.out.println("Shortest Response Time: "+rs.minConnectionTime()+" ms");		
		System.out.println("Average Response Time: "+rs.average()+" ms");
		System.out.println("std Response Time: "+rs.standarddeviation()+" ms");		
		System.out.println();
	}
	
	
	public static void reportSummery(RecordStore[] rss){		
		float[] throughputArr = new float[rss.length];
		for (int i = 0; i < rss.length; i++){
			throughputArr[i] = rss[i].throughput();
		}
		
		System.out.println();		
		System.out.println("# of Rounds: "+rss.length);	

		System.out.println("Average Throughput: "+average(throughputArr)+"  trans/sec");
		System.out.println("std Throughput: "+standarddeviation(throughputArr)+"  trans/sec");			
	}
	
	
	
	private static float average(float[] arr){
		float sum = 0;
		for(float f : arr){
			sum += f;		
		}
		if(arr.length==0){
			return 0.0f;	        
		}else{
			return sum*1.0f/arr.length;			
		}
	}
	
	private static double standarddeviation(float[] arr){
		float sum = 0;
		for(float f : arr){
			sum += f;
		}		
		if(arr.length==0){
			return 0.0;			
		}else{			
			double average = sum*1.0f/arr.length;
			
			double s2 = 0.0;
			
			for(float f : arr){
				double diff = (average-f)*(average-f);
//System.out.println(diff+" = "+average+" - "+x_r );					
				s2 += diff;
			}	
			return Math.sqrt(s2/arr.length);			
		}
	}
}
