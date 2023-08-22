package dd;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class cl {

	
	public static void main(String[] args) {
		
		
		String[] arr = new String[]{"a", "b", "c"};
		int[] arr2 = new int[] {1,2,3};
		Stream<String> stream = Arrays.stream(arr);
		IntStream stream2 = Arrays.stream(arr2);
		
		Stream<String> streamOfArrayPart =  Arrays.stream(arr, 1, 3); 
		
//		System.out.println(stream2.count());
//		System.out.println(stream2.max());
//		System.out.println(stream.toList());
		System.out.println(stream.count());
		System.out.println(stream2.limit(3));
		
		
		
		
	}
}
