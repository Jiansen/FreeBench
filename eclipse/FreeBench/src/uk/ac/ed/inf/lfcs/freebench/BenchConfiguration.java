package uk.ac.ed.inf.lfcs.freebench;

public abstract class BenchConfiguration {
	// runtime configuration
	abstract boolean resloveDomainName(); // resolve domain every time
	abstract String url();
	abstract String[] multiurls();
	
	abstract int requests_per_round();
	abstract int concurrency();// n tests in parallel
	
	
	abstract int rounds(); // n rounds, [requests_per_round() X concurrency()]
	// report configuration
	
	
}
