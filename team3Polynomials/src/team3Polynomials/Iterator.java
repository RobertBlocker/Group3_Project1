package team3Polynomials;

import java.util.NoSuchElementException;

/** A general iterator */
public interface Iterator<Term> {
    /** Tests whether there exists a next element at current iterator position.
        @return: {true} if there exists a next element at current iterator position; {false} otherwise
    */
    boolean hasNext();

    /** Moves the iterator forward and returns the element passed by.
        @return: element passed by in the iterator movement
        @throws NoSuchElementException: there is not a next element at current iterator position.
    */
    Term next();
    
    /** Moves the iterator backward and returns the element passed by.
    @return: element passed by in the iterator movement
    @throws NoSuchElementException: there is not a previous element at current iterator position.
    */
    Term previous();
    
    /** Replaces the previous element at current iterator position with the specified element.
    @param value: element with which to replace the previous element at current iterator position
    @throws NoSuchElementException: there is not a previous element at current iterator position.
     */
    void setPrevious(Term value);

    /** Returns the iterator's next element without moving the list forward, required for sortPolys()
    @return: iterator's nextNode.data
    @throws NoSuchElementException: there is not a next element at current iterator position.
    */
    Term getNext();
    
    /** Removes (and returns) the next element at current iterator position.
        @return: element removed
        @throws NoSuchElementException: there is not a next element at current iterator position.
    */
    Term removeNext();
    
    /** Inserts an element at the current iterator position.
    @param value: element to insert
     */
    void add(Term value);
}