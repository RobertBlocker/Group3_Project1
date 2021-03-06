package team3Polynomials;

import java.util.NoSuchElementException;

public interface List_Iterator<Term> extends Iterator<Term> {
    /** Tests whether there exists a previous element at current iterator position.
        @return: {true} if there exists a previous element at current iterator position; {false} otherwise
    */
    boolean hasPrevious();
    
    /** Returns the iterator's next element without moving the list forward, required for sortPolys()
    @return: iterator's nextNode.data
    @throws NoSuchElementException: there is not a next element at current iterator position.
    */
    Term getNext();

    /** Moves the iterator backward and returns the element passed by.
        @return: element passed by in the iterator movement
        @throws NoSuchElementException: there is not a previous element at current iterator position.
    */
    Term previous();

    /** Removes (and returns) the previous element at current iterator position.
        @return: element removed
        @throws NoSuchElementException: there is not a previous element at current iterator position.
    */
    Term removePrevious();

    /** Replaces the next element at current iterator position with the specified element.
        @param value: element with which to replace the next element at current iterator position
        @throws NoSuchElementException: there is not a next element at current iterator position.
    */
    void setNext(Term value);

    /** Replaces the previous element at current iterator position with the specified element.
        @param value: element with which to replace the previous element at current iterator position
        @throws NoSuchElementException: there is not a previous element at current iterator position.
    */
    void setPrevious(Term value);

    /** Inserts an element at the current iterator position.
        @param value: element to insert
    */
    void add(Term value);
}