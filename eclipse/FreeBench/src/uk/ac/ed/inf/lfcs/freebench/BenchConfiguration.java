package uk.ac.ed.inf.lfcs.freebench;

public class BenchConfiguration {
	// runtime configuration
//	abstract boolean resloveDomainName(); // resolve domain every time
//	abstract String url();
//	abstract String[] multiurls();
//	
//	abstract int requests_per_round();
//	abstract int concurrency();// n tests in parallel
//	
//	
//	abstract int rounds(); // n rounds, [requests_per_round() X concurrency()]	
	String url;	
	String[] multiurls;	
	boolean download_content;
	int read_timeout; // milliseconds
	int connect_timeout; // milliseconds
	int requests_per_round;
	int concurrency;// n tests in parallel		
	int rounds; // n rounds, [requests_per_round() X concurrency()]		
}
