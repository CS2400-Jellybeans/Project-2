import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T> {
   private Node topNode; // References the first node in the chain


   public LinkedStack() {
      topNode = null;
   }

   public void push(T newEntry) {
      Node newNode = new Node(newEntry, topNode);
      topNode = newNode;
   } // end push

   public T pop() {
      T top = peek(); // Might throw EmptyStackException

      // Assertion: topNode != null
      topNode = topNode.getNextNode();

      return top;
   } // end pop

   public T peek() {
      if (isEmpty())
         throw new EmptyStackException();
      else
         return topNode.getData();
   } // end peek

   public boolean isEmpty() {
      return topNode == null;
   } // end isEmpty

   public void clear()
   {
      topNode = null;
   } // end clear

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
      
      private T getData()
      {
         return data;
      } // end getData
      
      private void setData(T newData)
      {
         data = newData;
      } // end setData
      
      private Node getNextNode()
      {
         return next;
      } // end getNextNode
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
	} // end Node
}
// end LinkedStack