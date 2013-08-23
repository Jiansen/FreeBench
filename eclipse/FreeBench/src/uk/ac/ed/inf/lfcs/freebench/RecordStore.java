package uk.ac.ed.inf.lfcs.freebench;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class RecordStore {
	List<Record> recordes;
	
	public RecordStore(){
		this.recordes = new LinkedList<Record>();
	}
	
	
	public synchronized void addRecord(Record r)  {
		this.recordes.add(r);
	}
	
	public synchronized void addRecordArray(Record[] rs)  {
		this.recordes.addAll(Arrays.asList(rs));
	}
	
	
	// 
	public Date beginDate(){
		Date earliest = recordes.get(0).start;
		for(Record r : recordes){
			if(r.start.before(earliest)){
				earliest = r.start;
			}
		}
        return earliest; 
	}
	
	public Date endDate(){
		Date latest = recordes.get(0).finish;
		for(Record r : recordes){
			if(r.finish.after(latest)){
				latest = r.finish;
			}
		}
        return latest; 
	}	
	
	
	public int successrequests(){
		int s = 0;
		for(Record r : recordes){
			if(r.responseRecieved){
				s++;
			}
		}
        return s;
	}
	
	public int failedrequests(){
		int f = 0;
		for(Record r : recordes){
			if(!r.responseRecieved){
				f++;
			}
		}
        return f;
	}
	
	
	public float throughput(){
//		int s = this.successrequests();
//		return s*1000.0f/(this.endDate().getTime()-this.beginDate().getTime());
		
		long sumtime = 0;
		int successcount = 0;
		for(Record r : recordes){
			if(r.responseRecieved){
				sumtime += (r.finish.getTime() - r.start.getTime());
				successcount ++;
			}
		}
		if(successcount>0){
	        return successcount*1000.0f/sumtime;			
		}else{
			return 0.0f;
		}
		
	}
	
	public float average(){
		long sum = 0;
		int count = 0;
		for(Record r : recordes){
			if(r.responseRecieved){
				sum += (r.finish.getTime() - r.start.getTime());
				count++;
			}
		}
		if(count>0){
	        return sum*1.0f/count;			
		}else{
			return 0.0f;
		}
	}
	
	public double standarddeviation(){
		long sum = 0;
		int count = 0;
		for(Record r : recordes){
			if(r.responseRecieved){
				sum += (r.finish.getTime() - r.start.getTime());
				count++;
			}
		}		
		if(count==0){
			return 0.0;			
		}else{
			
			double average = sum*1.0f/count;
			
			double s2 = 0.0;
			
			for(Record r : recordes){
				if(r.responseRecieved){
					long x_r = (r.finish.getTime() - r.start.getTime());
					double diff = (average-x_r)*(average-x_r);
//System.out.println(diff+" = "+average+" - "+x_r );					
					s2 += diff;
				}
			}	
			return Math.sqrt(s2/count);			
		}
	}
	
	public long maxConnectionTime(){
		long t = 0;
		for(Record r : recordes){
			if(r.responseRecieved){
				long rt= r.finish.getTime() - r.start.getTime();
				if(rt > t){t = rt;}
			}
		}
		return t;
	}
	
	public long minConnectionTime(){
		long t = 0;
		for(Record r : recordes){
			if(r.responseRecieved){
				long rt= r.finish.getTime() - r.start.getTime();
				if(t==0 || rt<t ){t=rt;}
			}
		}
		return t;
	}
}
