package uk.ac.ed.inf.lfcs.freebench;

public class DefaultConfiguration extends BenchConfiguration {
	// runtime configuration
	@Override  boolean resloveDomainName() {return true;} // resolve domain every time
	@Override String url() {return "http://lb-1002311470.eu-west-1.elb.amazonaws.com:9000/json";}		
//	@Override String url() {return "http://ec2-54-216-114-116.eu-west-1.compute.amazonaws.com:9000/json";}	
//	@Override String url() {return "http://www.google.com";}
	
	@Override String[] multiurls() {
		String[] urls = {
				"http://ec2-54-216-114-116.eu-west-1.compute.amazonaws.com:9000/json",
//				"http://ec2-54-216-52-218.eu-west-1.compute.amazonaws.com:9000/json",
//				"http://ec2-54-216-219-71.eu-west-1.compute.amazonaws.com:9000/json",
//				"http://ec2-54-247-143-40.eu-west-1.compute.amazonaws.com:9000/json"
				};
		
		return urls;
    }	
	
    @Override int requests_per_round() {return 100;} 
    @Override int concurrency() {return 400;} // n tests in parallel
	
	
    @Override int rounds() {return 10;}  // n rounds, [requests_per_round() X concurrency()]    
	// report configuration

}
