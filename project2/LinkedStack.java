package project2;

import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T>
{
   private Node topNode; // References the first node in the chain

   public LinkedStack()
   {
      topNode = null;
   } // end constructor
   
   /**
   * Takes an infix expression and converts it to postfix.
   * @param infix A string containing the infix expression to be converted.
   * @return A string that is the postfix conversion of the infix expression.
   */
   public static String convertToPostfix(String infix)
   {
      LinkedStack<Character> operatorStack = new LinkedStack<Character>();
      String postfix = "";
      int index = 0;
      Character nextCharacter;
      Character topOperator;
      while (index < infix.length())
      {
         nextCharacter = infix.charAt(index);
         if (Character.isSpaceChar(nextCharacter))
         {
            index++;
            continue;
         }
         if(Character.isLetter(nextCharacter) || Character.isDigit(nextCharacter))
         {
            postfix = postfix + nextCharacter;
            index++;
            continue;
         }
         switch (nextCharacter)
         {
            case '^' :
               operatorStack.push(nextCharacter);
               break;
            case '+' : case '-' : case '*' : case '/' :
               while (!operatorStack.isEmpty() &&
               getPrecedence(nextCharacter) <= getPrecedence(operatorStack.peek()))
               {
                  postfix = postfix + operatorStack.peek();
                  operatorStack.pop();
               }
               operatorStack.push(nextCharacter);
               break;
            case '(' :
               operatorStack.push(nextCharacter);
               break;
            case ')' : // Stack is not empty if infix expression is valid
               topOperator = operatorStack.pop();
               while (topOperator != '(')
               {
                  postfix = postfix + topOperator;
                  topOperator = operatorStack.pop();
               }
               break;
            default: break; // Ignore unexpected characters
         }
         index++;
      }
      while (!operatorStack.isEmpty())
      {
         topOperator = operatorStack.pop();
         postfix = postfix + topOperator;
      }
      return postfix;
   }
   
   /**
   * Gets the precedence of the operator in question.
   * @param operator A character containing the operator in question.
   * @return 1 for addition and subtraction and 2 for multiplication and division.
   *         If the character is not a valid operator, return -1.
   */
   private static int getPrecedence(char operator)
   {
      switch (operator)
      {
         case '+' : case '-' :
            return 1;
         case '*' : case '/' :
            return 2;
         default: break;
      }
      return -1;
   }

   @Override
   /**
   * Adds a new entry to the top of the stack.
   * @param newEntry The item to add to the stack.
   */
   public void push(T newEntry)
   {
      Node newNode = new Node(newEntry, topNode);
      topNode = newNode;
   } // end push

   @Override
   /**
   * Removes the item at the top of the stack.
   * @return The item that was removed.
   */
   public T pop()
   {
      T top = peek(); // Might throw EmptyStackException

      // Assertion: topNode != null
      topNode = topNode.getNextNode();

      return top;
   } // end pop

   @Override
   /**
   * @throws EmptyStackException If the stack is empty.
   * @return The item currently at the top of the stack.
   */
   public T peek()
   {
      if (isEmpty())
         throw new EmptyStackException();
      else
         return topNode.getData();
   } // end peek

   @Override
   /**
   * Checks if the stack is currently empty.
   * @return True if the stack is empty. False otherwise.
   */
   public boolean isEmpty()
   {
      return topNode == null;
   } // end isEmpty

   @Override
   /**
   * Removes all items from the stack by removing the reference to the
   * first node and allowing the garbage collector to take care of the rest.
   */
   public void clear()
   {
      topNode = null;
   } // end clear

   // A class for each node in the linked stack chain.
	private class Node
	{
      private T    data; // Entry in stack
      private Node next; // Link to next node
      
      private Node(T dataPortion)
      {
         this(dataPortion, null);
      } // end constructor
      
      private Node(T dataPortion, Node linkPortion)
      {
         data = dataPortion;
         next = linkPortion;
      } // end constructor

      /**
      * @return The data in this node.
      */
      private T getData()
      {
         return data;
      } // end getData

      /**
      * Sets the data in this node.
      * @param setData The data to put in the node.
      */
      private void setData(T newData)
      {
         data = newData;
      } // end setData

      /**
      * @return The next node in the chain.
      */
      private Node getNextNode()
      {
         return next;
      } // end getNextNode

      /**
      * Sets the reference to the next node in the chain.
      * @param nextNode The node to set as the next in the chain.
      */
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
	} // end Node
}
// end LinkedStack