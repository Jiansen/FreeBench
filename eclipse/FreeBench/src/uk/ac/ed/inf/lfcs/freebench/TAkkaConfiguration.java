package uk.ac.ed.inf.lfcs.freebench;

public class TAkkaConfiguration extends BenchConfiguration {
	// runtime configuration
	public TAkkaConfiguration(){
		this.url = "http://lb-1002311470.eu-west-1.elb.amazonaws.com:9000/json";
		
		this.download_content = true;
		String[] urls = {
				"http://www.amazon.com",
				"http://www.amazon.com",
				"http://www.amazon.com",
			
//				"http://ec2-54-228-138-54.eu-west-1.compute.amazonaws.com:8888/json",	
//				"http://ec2-54-247-46-105.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-216-253-197.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-216-204-193.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-216-219-218.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-217-59-49.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-246-66-201.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-216-222-2.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-216-11-200.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-216-195-135.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-217-26-33.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-176-34-220-94.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-217-14-247.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-228-3-124.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-54-216-81-242.eu-west-1.compute.amazonaws.com:8888/json",
//				"http://ec2-46-137-146-210.eu-west-1.compute.amazonaws.com:8888/json"								
				};
		
		this.multiurls = urls;
		this.requests_per_round = 10;
		this.concurrency = 1;// n tests in parallel		
		this.rounds = 1; // n rounds, [requests_per_round() X concurrency()]
		
	}
}
