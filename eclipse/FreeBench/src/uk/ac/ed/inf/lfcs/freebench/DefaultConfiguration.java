package uk.ac.ed.inf.lfcs.freebench;

public class DefaultConfiguration extends BenchConfiguration {
	// runtime configuration
	@Override  boolean resloveDomainName() {return true;} // resolve domain every time
	@Override String url() {return "http://lb-1002311470.eu-west-1.elb.amazonaws.com:9000/json";}		
//	@Override String url() {return "http://ec2-54-216-114-116.eu-west-1.compute.amazonaws.com:9000/json";}	
//	@Override String url() {return "http://www.google.com";}
	
	@Override String[] multiurls() {
		String[] urls = {
//				"http://www.google.com",
//				"http://www.google.com",
//				"http://www.google.com",
//				"http://www.google.com",
//				"http://www.google.com",
//				"http://www.google.com",
//				"http://www.google.com",
//				"http://www.google.com",
//				"http://www.google.com",
//				"http://www.google.com",				
				"http://ec2-54-228-138-54.eu-west-1.compute.amazonaws.com:8888/json",	
				"http://ec2-54-247-46-105.eu-west-1.compute.amazonaws.com:8888/json",
				"http://ec2-54-216-253-197.eu-west-1.compute.amazonaws.com:8888/json",
				"http://ec2-54-216-204-193.eu-west-1.compute.amazonaws.com:8888/json",
				"http://ec2-54-216-219-218.eu-west-1.compute.amazonaws.com:8888/json",
				"http://ec2-54-217-59-49.eu-west-1.compute.amazonaws.com:8888/json",
				"http://ec2-54-246-66-201.eu-west-1.compute.amazonaws.com:8888/json",
				"http://ec2-54-216-222-2.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-216-11-200.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-216-195-135.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-217-26-33.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-176-34-220-94.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-217-14-247.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-228-3-124.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-216-81-242.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-46-137-146-210.eu-west-1.compute.amazonaws.com:8888/json"								
				};
		
		return urls;
    }	
	
    @Override int requests_per_round() {return 100;} 
    @Override int concurrency() {return 200;} // n tests in parallel
	
	
    @Override int rounds() {return 10;}  // n rounds, [requests_per_round() X concurrency()]    
	// report configuration

}
