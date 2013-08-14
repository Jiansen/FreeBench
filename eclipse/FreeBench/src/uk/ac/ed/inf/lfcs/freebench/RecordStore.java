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
	
	public void report(BenchConfiguration configuration){
		Date begin = this.beginDate();
		Date end = this.endDate();		
//		System.out.println("Reporting Reporting");
		System.out.println("Number of tests: "+ this.recordes.size());
		System.out.println("Begin at: "+ begin);
		System.out.println("Finish at: "+ end);		
		System.out.println("Elapse: "+ (end.getTime()-begin.getTime() ) + " ms");		
	}
	
	private Date beginDate(){
		Date earliest = recordes.get(0).start;
		for(Record r : recordes){
			if(r.start.before(earliest)){
				earliest = r.start;
			}
		}
        return earliest; 
	}
	
	private Date endDate(){
		Date latest = recordes.get(0).end;
		for(Record r : recordes){
			if(r.end.after(latest)){
				latest = r.end;
			}
		}
        return latest; 
	}	
	
	
	
	
//	private void 
	
}
