package project2;

public class LinkedStackTest
{
   public static void main(String[] args)
   {
      String[] infixExpressions = {"a+b", "a-b", "a*c+b-(e-d)"};
      testExpressions(infixExpressions);
   }
   private static void testExpressions(String[] expressions)
   {
      for(int i = 0; i < expressions.length; i++)
      {
         System.out.println((i+1) + ":\nInfix expression: " + expressions[i]
                            + "\nExpression converted to postfix: "
                            + LinkedStack.convertToPostfix(expressions[i]) + "\n");
      }
   }
}
