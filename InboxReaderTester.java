//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: InboxReaderTester.java
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

/**
 * This class implements unit test methods to check the correctness of the implementation of the
 * MessageStack, Inbox, and MessageStackIterator classes defined in the CS300 Fall 2020 - P08 LIFO
 * Inbox Reader programming assignment.
 *
 */
public class InboxReaderTester {

  /**
   * Calls your tester methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    if (runInboxReaderTestSuite()) {
      System.out.println("A test failed :(");
    } else {
      System.out.println("All tests passed :)");
    }
  }

  /**
   * Checks for the correctness of the constructor of the MessageStack, MessageStack.push(),
   * MessageStack.peek(), MessageStack.isEmpty(), and MessageStack.size() methods. This method must
   * consider at least the test scenarios provided in the detailed description of these javadocs.
   * (1) Create a new MessageStack object. The new created stack must be empty and its size must be
   * zero. (2) You can consider calling peek method on the empty stack. An EmptyStackException is
   * expected to be thrown by the peek method call. (3) Then, push a Message onto the stack and then
   * use peek to verify that the correct item is at the top of the stack. Make sure also to check
   * that isEmpty() must return false and the size of the stack is one. The peek() method call
   * should not make any change to the contents of the stack. (4) You can further consider pushing
   * other elements onto the stack. Then, each call of peek() method should return the correct
   * Message object that should be at the top of the stack. After pushing a new message to the stack
   * double check that the size of the stack was incremented by one.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testStackConstructorIsEmptyPushPeek() {
    // create a new MessageStack object
    MessageStack messageStack = new MessageStack();
    if (!messageStack.isEmpty() || messageStack.size() != 0)
      return true;
    // call peek method on the empty stack
    try {
      messageStack.peek();
      return true;
    } catch (EmptyStackException ese) {
      System.out.println("Exception caught - can't call peek() on an empty stack");
    }
    // push a Message onto the stack
    Message message1 = new Message("Grades", "My CS300 grades aren't looking so hot.");
    messageStack.push(message1);
    if (!messageStack.peek().equals(message1) || messageStack.isEmpty()) {
      System.out.println("failed to push message on empty stack");
      return true;
    }
    // pushing other elements onto the stack
    Message message2 = new Message("Working hard", "Comp sci is difficult but I'm working hard.");
    Message message3 = new Message("Motivation", "I want to be a woman of color in STEM.");
    messageStack.push(message2);
    messageStack.push(message3);
    if (!messageStack.peek().equals(message3) || messageStack.isEmpty()
      || messageStack.size() != 3) {
      System.out.println("failed to push message on stack with items in it");
      return true;
    }
    return false;
  }


  /**
   * Checks for the correctness of MessageStack.pop(). It calls MessageStack.push() and
   * MessageStack.peek() methods. This method must consider at least the test scenarios provided in
   * the detailed description of these javadocs. (1) Try to create a new empty MessageStack. Then,
   * try calling pop method on the empty stack. An EmptyStackException is expected to be thrown as a
   * result. (2) Try to push a message to the stack. Then, try to call the pop method on the stack
   * which contains only one element. Make sure that the pop() operation returned the expected
   * message, and that the stack is empty and its size is zero. (3) Then, try to push at least three
   * messages, then call pop method on the stack which contains 3 elements, the element at the top
   * of the stack must be returned and removed from the stack and its size must be decremented by
   * one. You can further keep popping the elements of the stack one by one until it becomes empty
   * and check each time that the pop operation performs appropriately.This test method must return
   * false if it detects at least one defect.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testStackPop() {
    // calling pop method on the empty stack
    MessageStack messageStack = new MessageStack();
    try {
      messageStack.pop();
      return true;
    } catch (EmptyStackException ese) {
      System.out.println("Exception caught - can't call peek() on an empty stack");
    }
    // call pop method on the stack which contains only one element
    Message.resetIdGenerator();
    Message message1 = new Message("Grades", "My CS300 grades aren't looking so hot.");
    messageStack.push(message1);
    Message popMessage = messageStack.pop();
    if (!popMessage.equals(message1) || !messageStack.isEmpty() || messageStack.size() != 0)
      return true;
    // call pop method on the stack which contains 3 elements
    Message message2 = new Message("Working hard", "Comp sci is difficult but I'm working hard.");
    Message message3 = new Message("Motivation", "I want to be a woman of color in STEM.");
    messageStack.push(message1);
    messageStack.push(message2);
    messageStack.push(message3);
    popMessage = messageStack.pop();
    if (!popMessage.equals(message3) || messageStack.size() != 2)
      return true;
    popMessage = messageStack.pop();
    if (!popMessage.equals(message2) || messageStack.size() != 1)
      return true;
    popMessage = messageStack.pop();
    if (!popMessage.equals(message1) || messageStack.size() != 0)
      return true;
    return false;
  }

  /**
   * Checks for the correctness of the Inbox.ReadMessage() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxReadMessage() {
    // read when Inbox.unreadMessageBox is empty
    Inbox inbox = new Inbox();
    String readMessage = inbox.readMessage();
    if (!readMessage.equals("Nothing in Unread")) {
      System.out.println("FAILED case 1: read when Inbox.unreadMessageBox is empty");
      return true;
    }
    // read when Inbox.unreadMessageBox is not empty
    Message.resetIdGenerator();
    Message message1 = new Message("Grades", "My CS300 grades aren't looking so hot.");
    Message message2 = new Message("Working hard", "Comp sci is difficult but I'm working hard.");
    Message message3 = new Message("Motivation", "I want to be a woman of color in STEM.");
    inbox.receiveMessage(message1);
    inbox.receiveMessage(message2);
    inbox.receiveMessage(message3);
    readMessage = inbox.readMessage();
    String expected = "[3] Motivation: I want to be a woman of color in STEM.";
    if (!readMessage.equals(expected) || !inbox.peekReadMessage().equals(expected)
      || !inbox.getStatistics().equals("Unread (2)\nRead (1)")) {
      System.out.println("FAILED case 2: read when Inbox.unreadMessageBox is not empty");
      System.out.println(readMessage);
      return true;
    }
    return false;
  }


  /**
   * Checks for the correctness of the Inbox.ReceiveMessage() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxReceiveMessage() {
    // receive a null message
    Inbox inbox = new Inbox();
    inbox.receiveMessage(null);
    // receive message on an empty inbox
    Message.resetIdGenerator();
    Message message1 = new Message("Grades", "My CS300 grades aren't looking so hot.");
    inbox.receiveMessage(message1);
    if (!inbox.getStatistics().equals("Unread (1)\nRead (0)"))
      return true;
    // receive message on non-empty inbox
    Message message2 = new Message("Working hard", "Comp sci is difficult but I'm working hard.");
    inbox.receiveMessage(message2);
    if (!inbox.getStatistics().equals("Unread (2)\nRead (0)"))
      return true;
    return false;
  }

  /**
   * Checks for the correctness of the Inbox.markAllMessagesAsRead() method
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testInboxMarkAllMessagesAsRead() {
    Inbox inbox = new Inbox();
    // mark messages on an empty unreadMessageBox
    int messageCount = inbox.markAllMessagesAsRead();
    if (messageCount != 0) {
      System.out.println("FAILED case 1: mark messages on an empty unreadMessageBox");
      return true;
    }
    // mark messages on non-empty unreadMessageBox
    Message.resetIdGenerator();
    Message message1 = new Message("Grades", "My CS300 grades aren't looking so hot.");
    Message message2 = new Message("Working hard", "Comp sci is difficult but I'm working hard.");
    Message message3 = new Message("Motivation", "I want to be a woman of color in STEM.");
    inbox.receiveMessage(message1);
    inbox.receiveMessage(message2);
    inbox.receiveMessage(message3);
    messageCount = inbox.markAllMessagesAsRead();
    if (messageCount != 3
      || !inbox.peekReadMessage().equals("[1] Grades: My CS300 grades aren't looking so hot.")
      || !inbox.getStatistics().equals("Unread (0)\nRead (3)")) {
      System.out.println("FAILED case 2: mark messages on a non-empty unreadMessageBox");
      return true;
    }
    return false;
  }

  /**
   * Checks for the correctness of MessageStackIterator.hasNext() and MessageStackIterator.next()
   * methods. You can rely on the constructor of the LinkedNode class which takes two input
   * parameters (setting both data and next instance fields) to create a chain of linked nodes (at
   * least 3 linked nodes) which carry messages as data fields. Then, create a new
   * MessageStackIterator() and pass it the head of the chain of linked nodes that you created. We
   * recommend that you consider at least the following test scenarios in this tester method. (1)
   * Try to call next() on the iterator. The first call of next() must return the message at the
   * head of your chain of linked nodes. (2) Try to call hasNext() on your iterator, it must return
   * true. (3) The second call of next() must return the message which has been initially at index 1
   * of your chain of linked nodes. (4) The third call of next() on your iterator must return the
   * message initially at index 2 of your chain of linked nodes. (5) If you defined a chain of 3
   * linked nodes in this scenario, hasNext() should return false, and the fourth call of next() on
   * the iterator must throw a NoSuchElementException.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise.
   */
  public static boolean testMessageStackIterator() {
    // Try to call next() on the iterator
    MessageStackIterator iterator = new MessageStackIterator(null);
    Message testMessage;
    try {
      testMessage = iterator.next();
      return true;
    } catch (NoSuchElementException nse) {
      System.out.println("NoSuchElementException - " + nse.getMessage());
    }
    Message.resetIdGenerator();
    Message message1 = new Message("Grades", "My CS300 grades aren't looking so hot.");
    Message message2 = new Message("Working hard", "Comp sci is difficult but I'm working hard.");
    Message message3 = new Message("Motivation", "I want to be a woman of color in STEM.");
    LinkedNode<Message> linkedNode1 = new LinkedNode<Message>(message1,
      new LinkedNode<Message>(message2, new LinkedNode<Message>(message3)));
    iterator = new MessageStackIterator(linkedNode1);
    testMessage = iterator.next();
    if (!testMessage.equals(message1)) {
      System.out.println("FAILED case 1: try to call next() on iterator");
      return true;
    }
    // Try to call hasNext() on your iterator
    if (!iterator.hasNext()) {
      System.out.println("FAILED case 2: try to call hasNext() on iterator");
      return true;
    }
    // The second call of next() must return the message which has been initially at index 1 of your
    // chain of linked nodes
    testMessage = iterator.next();
    if (!testMessage.equals(message2)) {
      System.out.println("FAILED case 3: try to call next() on iterator a second time");
      return true;
    }
    // The third call of next() on your iterator must return the message initially at index 2 of
    // your chain of linked nodes
    testMessage = iterator.next();
    if (!testMessage.equals(message3)) {
      System.out.println("FAILED case 3: try to call next() on iterator a third time");
      return true;
    }
    // If you defined a chain of 3 linked nodes in this scenario, hasNext() should return false, and
    // the fourth call of next() on the iterator must throw a NoSuchElementException.
    if (iterator.hasNext()) {
      System.out.println(
        "FAILED case 5: try to call hasNext() on iterator after 3 nodes are iterated through");
      return true;
    }
    try {
      testMessage = iterator.next();
      System.out.println("FAILED case 6: call next() on iterator after 3 nodes are iterated through");
      return true;
    } catch (NoSuchElementException nse) {
      System.out.println("NoSuchElementException - " + nse.getMessage());
    }
    return false;
  }

  public static boolean runInboxReaderTestSuite() {
    if (testStackConstructorIsEmptyPushPeek()) {
      System.out.println("testStackContstructorIsEmptyPushPeek(): failed");
      return true;
    }
    System.out.println("---");
    if (testStackPop()) {
      System.out.println("testStackPop(): failed");
      return true;
    }
    System.out.println("---");
    if (testInboxReadMessage()) {
      System.out.println("testInboxReadMessage() failed");
      return true;
    }
    System.out.println("---");
    if (testInboxReceiveMessage()) {
      System.out.println("testInboxReceiveMessage() failed");
      return true;
    }
    System.out.println("---");
    if (testInboxMarkAllMessagesAsRead()) {
      System.out.println("testInboxMarkAllMessagesAsRead() failed");
      return true;
    }
    System.out.println("---");
    if (testMessageStackIterator()) {
      System.out.println("testMessageStackIterator() failed");
      return true;
    }
    System.out.println("---");
    System.out.println("OMG Done Testing!");
    return false;
  }

}
