//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: MessageStackIterator.java
// Course: CS 300 Fall 2020
//
// Author: Huong Nguyen
// Email: htnguyen23@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: (identify each by name and describe how they helped)
// Online Sources: (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MessageStackIterator implements Iterator<Message> {
  private LinkedNode<Message> element; // keep track of the next element in the iteration

  /**
   * Constructor of iterator that takes a LinkedNode<Message> object as only input parameter.
   * 
   * @param top represents the top of the stack
   */
  public MessageStackIterator(LinkedNode<Message> top) {
    element = top;
  }

  /**
   * Checks if there is a Message object after the current one.
   * 
   * @return true if there is a next object, false if not
   */
  @Override
  public boolean hasNext() {
    if (this.element != null)
      return true;
    return false;
  }

  /**
   * Gets the next Message object after the current one.
   * 
   * @return the next Message object
   * @throws NoSuchElementException if the stack is exhausted and the current element in the
   *                                iteration does not have a next item
   */
  @Override
  public Message next() throws NoSuchElementException {
    if (this.element == null)
      throw new NoSuchElementException("Empty stack");
    Message currElement = this.element.getData();
    this.element = this.element.getNext();
    return currElement;
  }
}

