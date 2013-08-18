package uk.ac.ed.inf.lfcs.freebench;


public class FreeBench {

	public static void main(String[] args){
		BenchConfiguration config = new DefaultConfiguration();
		RecordStore[] results = Bench.run(config);
		RecordReporter.reportSummery(results, config);
	}
}
