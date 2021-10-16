package project2;

public class ArrayStackTest{
   public static void main(String[] args){
      String postfixExpression = "ab*ca-/de*+";
      testEvaluatePostfix(postfixExpression);
   }
   
   private static void testEvaluatePostfix(String expression) {
      System.out.println("Postfix expression: " + expression + " where a = 2, b = 3, c = 4, d = 5, e = 6");// 
      expression = "23*42-/56*+";
      System.out.println("\nPostfix expression with variables substituted: " + expression + " = " + evaluatePostfix(expression));
   }

   public static int evaluatePostfix(String postfix)
   {
      if(postfix == null)
      {
         System.out.println("Error: Postfix expression is null.");
         return -1;
      }

      StackInterface<Integer> valueStack = new ResizableArrayStack<Integer>();
      char nextCharacter;
      int index = 0;

      while (index < postfix.length())
      {
         nextCharacter = postfix.charAt(index);
         if (Character.isSpaceChar(nextCharacter))
         {
            index++;
            continue;
         }
         else if(Character.isDigit(nextCharacter)){
            valueStack.push(Character.getNumericValue(nextCharacter));
            index++;
            continue;
         } 
         else {
            int operandTwo = valueStack.pop();
            int operandOne = valueStack.pop();
            int result = 0;
            switch (nextCharacter){
               case '+' : 
                  result = operandOne + operandTwo;
                  break;
               case '-' : 
                  result = operandOne - operandTwo;
                  break;
               case '*' : 
                  result = operandOne * operandTwo;
                  break;
               case '/' : 
                  try {
                     result = operandOne / operandTwo; 
                  }
                  catch (ArithmeticException e) {      // throw Exception
                     System.out.println("Error: attempting to divide by zero");
                  }
                     break;
               case '^' : 
                  result = (int)Math.pow(operandOne,operandTwo);
                  break;
               default: 
                  break; // Ignore unexpected characters
            }
         valueStack.push(result);
         index++;
         }
      }
      return valueStack.peek();
   }
}
