package S20190922;

public class Study001 {
	
	public static void main(String[] args) {
		int num = fibonacci(18);
		//System.out.println(3);
	}
	
	
	
	private static int fibonacci(int n) {
		int a = 1;
		int b = 0;
		int c = 0;
		for (int i = 0; i < n; i++) {
			c = a + b;
			a = b;
			b = c;
			if(i != 0 && i%5 == 0) {
				System.out.println();
			}
			System.out.print(c + "\t");
		}
		
		return c;
	} 

}
