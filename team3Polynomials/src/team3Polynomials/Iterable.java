package team3Polynomials;

/** An iterable class can be iterated by iterators. */
public interface Iterable<Term> {
    /** Generates an iterator at the beginning of the data structure.
        @return: an iterator at the beginning of the data structure
    */
    Iterator<Term> iterator();
}