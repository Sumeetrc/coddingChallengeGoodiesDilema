package org.goodiesdilema.goodiesApp;

import java.io.*;
import java.util.*;

/**
 * The type Products.
 */
class products {
	/**
	 * The Name.
	 */
	String name;
	/**
	 * The Price.
	 */
	int price;

	/**
	 * Instantiates a new Products.
	 *
	 * @param name  the name
	 * @param price the price
	 */
	public products(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String toString() {
		return this.name + ": " + this.price;
	}
}

/**
 * The type Products class.
 */
class ProductsClass {
	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("C:\\Users\\Patils\\eclipse-workspace\\Goodies dilema\\src\\org\\goodiesdilema\\goodiesApp\\input.txt");
		Scanner sc = new Scanner(fis);
		int number_of_employees = Integer.parseInt(sc.nextLine().split(": ")[1]);
		sc.nextLine();
		sc.nextLine();
		sc.nextLine();

		ArrayList<products> goodies_products = new ArrayList<products>();

		while (sc.hasNextLine()) {
			String current[] = sc.nextLine().split(": ");
			goodies_products.add(new products(current[0], Integer.parseInt(current[1])));
		}
		sc.close();

		Collections.sort(goodies_products, new Comparator<products>() {
			public int compare(products a, products b) {
				return a.price - b.price;
			}
		});

		int min_diff = goodies_products.get(goodies_products.size() - 1).price;
		int min_index = 0;
		for (int i = 0; i < goodies_products.size() - number_of_employees + 1; i++) {
			int diff = goodies_products.get(number_of_employees + i - 1).price - goodies_products.get(i).price;

			if (diff <= min_diff) {
				min_diff = diff;
				min_index = i;
			}
		}


		FileWriter fw = new FileWriter("C:\\Users\\Patils\\eclipse-workspace\\Goodies dilema\\src\\org\\goodiesdilema\\goodiesApp\\output.txt");
		fw.write("The goodies selected for distribution are:\n\n");
		for (int i = min_index; i < min_index + number_of_employees; i++) {
			fw.write(goodies_products.get(i).toString() + "\n");
		}

		fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_diff);
		fw.close();
	}
}
