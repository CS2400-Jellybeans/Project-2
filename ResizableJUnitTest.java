import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;
import project2.ArrayStackTest;

public class ResizableJUnitTest extends TestCase
{
   @Test
   //Various assorted inputs and expected outputs.
   public void testEvaluatePostfix()
   {
      String[] postfixExpressions = {"2 3 +", "2 3^4-2 5+6^+", "2 3*4 2-/5 6*+"};
      String[] expectedResults = {"5", "13", "33"};
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
      String expectedResult = null;
      assertEquals(expectedResult, ArrayStackTest.evaluatePostfix((postfixExpression));
   }
   
   @Test
   //Should be able to handle if the expression is blank.
   public void testEmpty()
   {
      String postfixExpression = "";
      String expectedResult = "";
      assertEquals(expectedResult, ArrayStackTest.evaluatePostfix((postfixExpression));
   }

   @Test
   //Should be able to handle if the expression has invalid operators mixed in.
   public void testWithInvalids()
   {
      String postfixExpression = "!2 3#*4 2$%-/5 &6*+$";
      String expectedResult = "33";
      assertEquals(expectedResult, ArrayStackTest.evaluatePostfix((postfixExpression));
   }
}