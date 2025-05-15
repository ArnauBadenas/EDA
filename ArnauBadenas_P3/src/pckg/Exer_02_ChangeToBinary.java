package pckg;

public class Exer_02_ChangeToBinary {

	
	public static void main (String [] args) {

		/* COMPLETE */
		//Perform some tests here
		// Casos base
		testBinaryFunction(0, "0");
		testBinaryFunction(1, "1");

		// Potències de 2
		testBinaryFunction(2, "10");
		testBinaryFunction(4, "100");
		testBinaryFunction(8, "1000");
		testBinaryFunction(16, "10000");

		// Valors grans
		testBinaryFunction(1023, "1111111111");
		testBinaryFunction(1024, "10000000000");

		// Nombres arbitraris
		testBinaryFunction(5, "101");
		testBinaryFunction(10, "1010");
		testBinaryFunction(1230, "10011001110");
		testBinaryFunction(255, "11111111");

	}
	
	
	public static String toBinaryString(int n) {
		/* COMPLETE */
		// Write the recursive function that creates the binary representation of n (n>=0)
		if(n < 2) return Integer.toString(n);
		return toBinaryString(n/2) + n%2;
	}

	public static void testBinaryFunction(int input, String expectedReturn){
		String result = toBinaryString(input);
		System.out.print("Input: " + input);
		System.out.print(", Expected: " + expectedReturn);
		System.out.print(", Returned value: " + result);

		if(result.equals(expectedReturn)){
			System.out.println(": Test passed!");
		}else{
			System.out.println(": Test failed");
		}
	}
	
	
}
