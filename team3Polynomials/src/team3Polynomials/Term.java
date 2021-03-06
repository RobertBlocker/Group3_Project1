package team3Polynomials;

public class Term implements Comparable<Term> {

	public Integer coefficient;
    public Integer exponent;
    
    //A constructor! each Term is constructed with coefficient and exponent integer values
    public Term(int c, int x) {
        this.coefficient = c;
        this.exponent = x;
    }
    
 /**
     * @return: coefficient variable
     */
    public int getCoefficient() {return coefficient;}
    
    /**
     * @return: exponent variable
     */
    public int getExponent() {return exponent;}
	
  //This compareTo works the same as default for int, comparing the Term's exponent values
    @Override
    public int compareTo(Term term) {
    	if (this.exponent == 5000) { this.exponent = 0; }
    	if (term.exponent == 5000) { term.exponent = 0; }
    	
    	int answer = 0;
    	if (this.exponent > term.exponent) { answer = 1; }
    	else if (this.exponent < term.exponent) { answer = -1; }
    	else { answer = 0; }
    	
    	return answer;

    }//end compareTo()
    
   /**
     * prints polynomials in mathematical format
     */
    @Override
    public String toString() {
      
        final String number = getNumber (coefficient);// calls getNumber()
        final String exponentStr = getExponentStr (coefficient, exponent);// calls getExponentStr()

        return String.format ("%s%s", number, exponentStr);// returns a formatted string 
    }
    
    /**
     * Determines if the polynomial needs a {x^} or just {x}
     * @param coefficient: number in front of the x
     * @param exponent: number to the upper right of the x
     * @return:{"x^"} if exponent are not 0 or 1;{"x"} otherwise;{""} if exponent or coefficient are 0 or 1
     */
    private String getExponentStr( int coefficient,  int exponent) {
        if (coefficient == 0 || exponent == 0) {
            return "";
        }
        if (exponent == 1) {
            return "x";
        }
        return "x^" + exponent;
    }
    
    /**
     * converts int value to string
     * @param value: variable to be converted
     * @return: {""} if value is equal to 1; converts value to string otherwise 
     */
    private String getNumber( int value) {
        final int newValue = value;

        return newValue == 1 ? "" : Integer.toString (newValue);
    }
    
}//end Term