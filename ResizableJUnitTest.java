import static org.junit.Assert.assertThrows;

import java.util.EmptyStackException;

import org.junit.Test;

import junit.framework.TestCase;
import project2.ArrayStackTest;

public class ResizableJUnitTest extends TestCase
{
   @Test
   //Various assorted inputs and expected outputs.
   public void testEvaluatePostfix()
   {
      String[] postfixExpressions = {"23+", "23^4-25+6^+", "23*42-/56*+"};
      int[] expectedResults = {5, 117653, 33};
      for(int i = 0; i < postfixExpressions.length; i++)
      {
         assertEquals(expectedResults[i], ArrayStackTest.evaluatePostfix(postfixExpressions[i]));
      }
   }

   @Test
   //Should be able to handle if null is passed into the method.
   public void testNull()
   {
      String postfixExpression = null;
      int expectedResult = -1;
      assertEquals(expectedResult, ArrayStackTest.evaluatePostfix((postfixExpression)));
   }
   
   @Test
   //Should be able to handle if the expression is blank.
   public void testEmpty()
   {
      String postfixExpression = "";
      int expectedResult = -1;
      assertEquals(expectedResult, ArrayStackTest.evaluatePostfix((postfixExpression)));
   }

   @Test
   //Should be able to handle if the expression has unexpected characters.
   //Note that the format must still be valid.
   public void testUnexpected()
   {
      String postfixExpression = "23@*42-&/56!*#+";
      int expectedResult = 33;
      assertEquals(expectedResult, ArrayStackTest.evaluatePostfix((postfixExpression)));
   }
   
   @Test
   //Should throw an EmptyStackException if the postfix expression does not make sense.
   public void testInvalids()
   {
      String postfixExpression = "5+*";
      assertThrows(EmptyStackException.class, () -> {ArrayStackTest.evaluatePostfix((postfixExpression));});
   }
}