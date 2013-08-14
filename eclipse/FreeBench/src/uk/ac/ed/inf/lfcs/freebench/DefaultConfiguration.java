package uk.ac.ed.inf.lfcs.freebench;

public class DefaultConfiguration extends BenchConfiguration {
	// runtime configuration
	@Override  boolean resloveDomainName() {return true;} // resolve domain every time
	@Override String url() {return "http://www.google.com";}// concurrency level
	
    @Override int requests_per_round() {return 100;} 
    @Override int concurrency() {return 1;} // n tests in parallel
	
	
    @Override int rounds() {return 2;}  // n rounds, [requests_per_round() X concurrency()]    
	// report configuration

}
