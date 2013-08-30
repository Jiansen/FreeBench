package uk.ac.ed.inf.lfcs.freebench;

import java.io.IOException;


public class FreeBench {

	public static void main(String[] args) throws IOException{
//		BenchConfiguration config = new TAkkaConfiguration();

		BenchConfiguration config = JSONBenchConfiguration.parse("./res/TAkkaConfigure.json");
		RecordStore[] results = Bench.run(config);
		
		RecordReporter.reportStore(results[0]);		
		RecordReporter.reportSummery(results);
	}
}
