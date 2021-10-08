package project2;

public class LinkedStackTest
{
   public static void main(String[] args)
   {
      String[] infixExpressions = {"a+b", "a*c+b-(e-d)", "a*b/(c-a)+d*e"};
      testExpressions(infixExpressions);
   } // end main

   /**
   * Takes an array of infix expressions and converts them
   * to postfix, displaying the results as it goes along.
   * @param expressions An array of strings to be turned to postfix.
   */
   private static void testExpressions(String[] expressions)
   {
      for(int i = 0; i < expressions.length; i++)
      {
         System.out.println((i+1) + ":\nInfix expression: " + expressions[i]
                            + "\nExpression converted to postfix: "
                            + convertToPostfix(expressions[i]) + "\n");
      }
   }
   
   /**
   * Takes an infix expression and converts it to postfix.
   * @param infix A string containing the infix expression to be converted.
   * @return A string that is the postfix conversion of the infix expression.
   *         If infix is null, returns null.
   */
   public static String convertToPostfix(String infix)
   {
      if(infix == null)
      {
         return null;
      }
      StackInterface<Character> operatorStack = new LinkedStack<Character>();
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
   *         If the character is not a valid operator, returns -1.
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
}
