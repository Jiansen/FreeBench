package uk.ac.ed.inf.lfcs.freebench;


public class FreeBench {

	public static void main(String[] args){
		BenchConfiguration config = new DefaultConfiguration();
		RecordStore result = Bench.run(config);
		result.report(config);
		
//		int[] list = new int[1000];
//		for(int i=0; i<1000; i++){
//			list[i] = (int)(Math.random()*1000);
//		}
//		
//		System.out.println(Arrays.toString(list));
//		Arrays.sort(list);
//		
//		System.out.println(Arrays.toString(list));
		
		
		
	}
}
