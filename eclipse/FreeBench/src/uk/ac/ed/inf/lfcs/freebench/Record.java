package uk.ac.ed.inf.lfcs.freebench;

import java.util.Date;

public class Record {
	String tag;
	Date start;
	String url;	
	boolean responseRecieved;
	int responseCode;
	Date finish;
	String response;
	
	
	public Record(String tag){this.tag = tag;}
}
