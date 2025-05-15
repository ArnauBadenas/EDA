package pckg;


import java.util.Random;

public class Exer_01_GCF {
	
	public static void main (String [] args) {
		/* COMPLETE */
		/* Write here a program that generates 100000 pairs of numbers
		between [1, 5000] to check that the function calculates the GCF.*/

		Random randomNumberGenerator = new Random();
		final int NUMBER_OF_PAIRS = 100000;
		for (int numberPairCounter = 0; numberPairCounter < NUMBER_OF_PAIRS; numberPairCounter++) {
			int num1 = randomNumberGenerator.nextInt(1,5001); //el segon paràmetre és excloent, [1,5001).
			int num2 = randomNumberGenerator.nextInt(1,5001);

			if (dijkstra(num1,num2) != iterativeGCF(num1,num2)){
				System.out.println("Operació incorrecte a números "+num1+" i "+num2);
				return;
			}
		}
		System.out.println("Operacions correctes, test acabat.");

	}
	
	
	/* COMPLETE */
	// write here a recursive function that calculates the GCF using Dijkstra's algorithm
	public static int dijkstra(int num1, int num2){
		if(num1 == num2){
			return num1;
		}
		if(num1 < num2){
			return dijkstra(num1, num2-num1);
		}
		return dijkstra(num2, num1-num2);

	}

	/*DO NOT MODIFY CODE BELOW*/
	public static int iterativeGCF(int x, int y) {
		// iterative gcf. Do not modify this function
		int inter;
		int small = Math.min(x, y);
		int great = Math.max(x, y);
		int remainder = great%small;
		while (remainder!=0) {
			inter = small;
			small = Math.min(small, remainder);
			great = Math.max(inter, remainder);
			remainder = great%small;
		}
		return small;
	}
	

}
