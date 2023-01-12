//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: MessageStack.java
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

public class MessageStack implements StackADT<Message>, Iterable<Message>{
  private LinkedNode<Message> top; // refers to the top of the linked stack
  private int size = 0; // keeps track of the total number of Message objects stored in the stack

  @Override
  public void push(Message element) {
    if (element == null || element.getSUBJECT() == null || element.getTEXT() == null) {
      System.out.println("Invalid message - null fields");
      return;
    }
    if (this.size() == 0) {
      this.top = new LinkedNode<Message>(element);
      size++;
      return;
    } else {
      LinkedNode<Message> temp = this.top;
      this.top = new LinkedNode<Message>(element, temp);
      size++;
    }
  }

  @Override
  public Message pop() throws EmptyStackException {
    if (this.isEmpty())
      throw new EmptyStackException();
    Message returnMessage;
    if (this.top.getNext() == null) {
      returnMessage = top.getData();
      this.top = null;
      size = 0;
      return returnMessage;
    } else {
      LinkedNode<Message> temp = this.top;
      returnMessage = this.top.getData();
      this.top = temp.getNext();
      size --;
      return returnMessage;
    }
  }

  @Override
  public Message peek() {
    if (this.isEmpty())
      throw new EmptyStackException();
    return this.top.getData();
  }

  @Override
  public boolean isEmpty() {
    if (this.size == 0)
      return true;
    return false;
  }

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public Iterator<Message> iterator() {
    MessageStackIterator iterator = new MessageStackIterator(this.top);
    return iterator;
  }


}
