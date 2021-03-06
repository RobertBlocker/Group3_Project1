package team3Polynomials;
import java.util.Scanner;
public class team3Polynomials {
	public static Linked_List<Term> combinedSortedList = new Linked_List<>();
	public static Linked_List<Term> termList = new Linked_List<>();

	public static void main(String[] args) {
		System.out.println();
		System.out.println("WELCOME TO POLYNOMIAL ADDITION CALCULATOR");
		System.out.println();
		
		Scanner console = new Scanner(System.in);
		String option = "e";
		String poly1 = "";
		String poly2 = "";
		
		//the menu is in a while loop until the option selected is q
		//the menu only reads the first character of the input as the option
		while (option.charAt(0) != 'q') {
			System.out.println("MENU");
			System.out.println("f - Enter first polynomial");
	        System.out.println("s - Enter second polynomial");
	        System.out.println("r - Show results");
	        System.out.println("q - Quit");
	        System.out.println("");
	        System.out.println("Choose an option: ");
			option = console.next();
			
			//calls convertString() to store the polynomials in a list of Term objects
			if (option.charAt(0) == 'f') {
				System.out.println("Enter first polynomial: ");
				poly1 = console.next();
				convertString(poly1);
				System.out.println();
			}
			if (option.charAt(0) == 's') {
				System.out.println("Enter second polynomial: ");
				poly2 = console.next();
				convertString(poly2);
			}
			
			//decided to skip something like "first polynomial + second polynomial = result" for
			//a list of all polynomials going down, then the added result below
			if (option.charAt(0) == 'r') {
				System.out.println();
				System.out.println("All the polynomials you entered, in the order you entered them\n"
						+ "base number with exponent of 1 will appear here with a placeholder exponent of x^5000");
				Iterator<Term> it4 = termList.iterator();
				while (it4.hasNext()) {
					System.out.println(it4.next().toString());
				}
				
				System.out.println();
				System.out.println("Your added polynomial is: ");
				combinedSortedList = sortPolys();
				Iterator<Term> it3 = combinedSortedList.iterator();
				while (it3.hasNext()) {
					System.out.print(it3.next().toString());
					if (it3.hasNext()) { System.out.print(" + "); }
				}
				System.out.println();
			}
			System.out.println();
		}//end while loop
		
		System.out.println("Have a nice day, PROGRAM TERMINATED");
	}//end main
	
	//this method loops through each character in the string, and whenever it detects
	//the end of a term, it appends a new Term object to the combined list of both polys entered
	//I used an exponent of 5000 to represent a base number, exponent of 0 cannot work because anything
	//to the exponent of 0 is 1, we remove the 5000 before the final result
	public static void convertString(String poly) {
		 
     	boolean minus = true;
			String exponent = "";
			String coefficient = "";
			for (int i = 0; i < poly.length(); i++) {
				/*while iterating through the string, if + or - is found before an x is found, then the term must be
				just a number */
				if (i > 0 && !(coefficient == "") && (poly.charAt(i) == '+' || poly.charAt(i) == '-')) {
					if (coefficient.equals("-")) { coefficient = "-1"; }
					if (coefficient.equals("+") || coefficient.equals("")) { coefficient = "1"; }
					exponent = "5000";
					Term newTerm = new Term(Integer.parseInt(coefficient), Integer.parseInt(exponent));
					termList.addLast(newTerm);
					coefficient = "";
					exponent = "";
				}
				//if the current character is not x, it is part of the coefficient
				if (!(poly.charAt(i) == 'x')) { coefficient += poly.charAt(i); }
				
				/*if the current character is not x and is also the end of the string, then the term must be
				just a number */
				if (!(poly.charAt(i) == 'x') && i+1 == poly.length()) { 
					if (coefficient.equals("-")) { coefficient = "-1"; }
					if (coefficient.equals("+") || coefficient.equals("")) { coefficient = "1"; }
					exponent = "5000";
					Term newTerm = new Term(Integer.parseInt(coefficient), Integer.parseInt(exponent));
					termList.addLast(newTerm);
					coefficient = "";
					exponent = "";
				}
				
				//if the current character is x, then we must find the exponent that lies after it
				if (poly.charAt(i) == 'x') {
					//if the current character is the end of the string, then the exponent of x is 1
					if (i + 1 == poly.length()) {
						exponent = "1";
						if (coefficient.equals("-")) { coefficient = "-1"; }
						if (coefficient.equals("+") || coefficient.equals("")) { coefficient = "1"; }
						if (exponent.equals("0")) {
							coefficient = "1";
							exponent = "5000";
						}
						if (!(coefficient.equals("0"))) { 
							Term newTerm = new Term(Integer.parseInt(coefficient), Integer.parseInt(exponent));
							termList.addLast(newTerm);
						}
						coefficient = "";
						exponent = "";
					}
					/*if the current character is not the end of the string, and a '^' follows it, then an exponent
					 * lies after the '^' */
					else if (i < poly.length()-1 && poly.charAt(i+1) == '^') {
						/* if character after '^' is '-', then set a boolean minus to true, skip the '-' for now, and add
						 '-' to the exponent at the end */
						if (poly.charAt(i+2) == '-') { minus = true; }
						else { minus = false; }	
						if (minus) { i = i + 3;  }
						else { i = i + 2; }
						//while loop that finds the exponent by adding characters to the right of ^ until a +, -, or end of string is found
						while ((i < poly.length()) && !(poly.charAt(i) == '+') && !(poly.charAt(i) == '-')) {
							exponent += poly.charAt(i++);
						}
						i--;
						if (minus) { exponent = "-" + exponent; }
						/* Special cases here,
						 * if coefficient is only '-', it is -1
						 * if coefficient is either only '+' or nothing, it is 1
						 * if the exponent is 0, the term is just 1
						 * if the coefficient is 0, ignore term */
						if (coefficient.equals("-")) { coefficient = "-1"; }
						if (coefficient.equals("+") || coefficient.equals("")) { coefficient = "1"; }
						if (exponent.equals("0")) {
							coefficient = "1";
							exponent = "5000";
						}
						if (!(coefficient.equals("0"))) { 		
							Term newTerm = new Term(Integer.parseInt(coefficient), Integer.parseInt(exponent));
							termList.addLast(newTerm);
						}
						//reset values before finding the next term
						coefficient = "";
						exponent = "";
					}
					else {
						//if the character x is the end of the string, then the exponent is 1
						exponent = "1";
						if (coefficient.equals("-")) { coefficient = "-1"; }
						if (coefficient.equals("+") || coefficient.equals("")) { coefficient = "1"; }
						if (exponent.equals("0")) {
							coefficient = "1";
							exponent = "5000";
						}
						if (!(coefficient.equals("0"))) { 
							System.out.println(Integer.parseInt(coefficient));
							System.out.println(exponent);
							
							Term newTerm = new Term(Integer.parseInt(coefficient), Integer.parseInt(exponent));
							termList.addLast(newTerm);
						}
						coefficient = "";
						exponent = "";
					}
				}
			}//end loop through string
     }//end convertString() 


	/** This is the most important method, takes the list from convertString and 
	 * uses Term's compareTo() to make a copy of the list
	 * this does BOTH the sorting and adding of polynomials
	 * returns the new list
	 */
	public static Linked_List<Term> sortPolys() {
		Linked_List<Term> returnList = new Linked_List<>();
		
		//this first iterator iterates through the list sortPolys returns,
		//it will send each Term to the nested while loop, which finds where to put it in the new list
		Iterator<Term> it = termList.iterator();
		//before the loop, add the first term, start from the 2nd term
		returnList.addFirst(it.next());
		while (it.hasNext()) {
			//grabs the Term to be inserted and moves to the next term for next loop
			Term temp = it.next();
			
			//this iterator iterates through the new list inside this while loop until it finds where to put the current Term
			Iterator<Term> it2 = returnList.iterator();
			while (it2.hasNext()) {
				int result = temp.compareTo(it2.next());
				//if result is 1, temp is greater than current Term in new list
				if (result == 1) {
					it2.previous();
					it2.add(temp);
					break;
				}
				//if result is -1, temp is less than
				else if (result == -1) {
					if (!it2.hasNext()) { returnList.addLast(temp); }
				}
				//else, they are the same exponent, so make a new Term obj with their coefficients added and 
				//setPrevious to it
				else {
				    Term p = new Term(it2.previous().coefficient + temp.coefficient, temp.exponent);
				    it2.next();
				    it2.setPrevious(p);
					break;
				} 
			}
		}//end while
		return returnList;
	}//end sortPolys() method

}//end class