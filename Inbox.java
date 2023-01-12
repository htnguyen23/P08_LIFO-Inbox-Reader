//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Inbox.java
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

public class Inbox {

  private MessageStack readMessageBox; // stack which stores read messages
  private MessageStack unreadMessageBox; // stack which stores unread messages

  /**
   * This no-argument constructor creates a new empty inbox and initializes its instance fields.
   * Both unreadMessageBox and readMessageBox stacks of this inbox must be initially empty.
   */
  public Inbox() {
    this.readMessageBox = new MessageStack();
    this.unreadMessageBox = new MessageStack();
  }

  /**
   * Reads the message at the top of the unreadMessageBox. Once read, the message must be moved from
   * the unreadMessageBox to the readMessageBox.
   * 
   * @return the string representation of the message at the top of the unreadMessageBox, or
   *         "Nothing in Unread" if the unreadMessageBox of this inbox is empty.
   */
  public String readMessage() {
    if (unreadMessageBox.isEmpty())
      return "Nothing in Unread";
    Message message = unreadMessageBox.pop();
    readMessageBox.push(message);
    return message.toString();
  }

  /**
   * Returns the string representation of the message at the top of the readMessageBox.
   *
   * @return the string representation of the message at the top of readMessageBox and "Nothing in
   *         Read" if the readMessageBox is empty.
   */
  public String peekReadMessage() {
    if (readMessageBox.isEmpty())
      return "Nothing in read";
    return readMessageBox.peek().toString();
  }

  /**
   * Marks all messages in the unread message box as read.
   * 
   * @return the total number of messages marked as read.
   */
  public int markAllMessagesAsRead() {
    int messageCount = 0;
    for (int i = unreadMessageBox.size(); i > 0; i--) {
      readMessageBox.push(unreadMessageBox.pop());
      messageCount++;
    }
    return messageCount;
  }

  /**
   * Pushes a newMessage into the unread message box.
   * 
   * @param newMessage represents a reference to the received message
   */
  public void receiveMessage(Message newMessage) {
    unreadMessageBox.push(newMessage);
  }

  /**
   * Removes permanently all the messages from the readMessageBox
   * 
   * @return the total number of the removed messages
   */
  public int emptyReadMessageBox() {
    int messageCount = 0;
    for (int i = readMessageBox.size(); i >= 0; i--) {
      readMessageBox.pop();
      messageCount++;
    }
    return messageCount;
  }

  /**
   * Gets the statistics of this inbox.
   * 
   * @return a String formatted as follows: "Unread (size1)" + "\n" + "Read (size2)", where size1
   *         and size2 represent the number of unread and read messages respectively.
   */
  public String getStatistics() {
    String result =
      "Unread (" + unreadMessageBox.size() + ")\nRead (" + readMessageBox.size() + ")";
    return result;
  }

  /**
   * Traverses all the unread messages and return a list of their ID + " " + SUBJECT, as a string.
   * Every string representation of a message is provided in a new line.
   * 
   * @return a String representation of the contents of the unread message box with the following
   *         format: Unread(unreadMessageBox_size)\n + list of the messages in unreadMessageBox (ID
   *         + " " + SUBJECT) each in a line
   */
  public String traverseUnreadMessages() {
    String result = "Unread(" + unreadMessageBox.size() + ")\n";
    Iterator<Message> iterator = unreadMessageBox.iterator();
    Message currMessage;
    for (int i = 1; i < unreadMessageBox.size(); i++) {
      if (iterator.hasNext()) {
        currMessage = iterator.next();
        result += currMessage.getID() + " " + currMessage.getSUBJECT() + "\n";
      }
    }
    return result;
  }

  /**
   * Traverses all the read messages and return a list of their string representations, ID + " " +
   * SUBJECT, each per new line, as a string
   * 
   * @return a String representation of the contents of the read message box with the following
   *         format: Read(readMessageBox_size)\n + list of the messages in readMessageBox (ID + " "
   *         + SUBJECT) each in a line.
   */
  public String traverseReadMessages() {
    String result = "Unread(" + readMessageBox.size() + ")\n";
    Iterator<Message> iterator = readMessageBox.iterator();
    Message currMessage;
    for (int i = 1; i < readMessageBox.size(); i++) {
      if (iterator.hasNext()) {
        currMessage = iterator.next();
        result += currMessage.getID() + " " + currMessage.getSUBJECT() + "\n";
      }
    }
    return result;
  }
}
