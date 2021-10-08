import org.junit.Test;

import junit.framework.TestCase;
import project2.LinkedStack;

public class LinkedJUnitTest extends TestCase
{
   @Test
   //Various assorted inputs and expected outputs.
   public void testConvertToPostfix()
   {
      String[] infixExpressions = {"a+b", "a*c+b-(e-d)", "a*b/(c-a)+d*e"};
      String[] expectedResults = {"ab+", "ac*b+ed--", "ab*ca-/de*+"};
      for(int i = 0; i < infixExpressions.length; i++)
      {
         assertEquals("test", expectedResults[i], LinkedStack.convertToPostfix(infixExpressions[i]));
      }
   }
   @Test
   //Should be able to handle if the expression is blank.
   public void testEmpty()
   {
      String infixExpression = "";
      String expectedResult = "";
      assertEquals(expectedResult, LinkedStack.convertToPostfix(infixExpression));
   }

   @Test
   //Should be able to handle if the expression has spaces in it.
   public void testWithSpaces()
   {
      String infixExpression = "a * b / (c - a) + d * e";
      String expectedResult = "ab*ca-/de*+";
      assertEquals(expectedResult, LinkedStack.convertToPostfix(infixExpression));
   }

   @Test
   //Should be able to handle if the expression has numbers instead of characters.
   public void testWithNumbers()
   {
      String infixExpression = "2*b/(c-2)+d*6";
      String expectedResult = "2b*c2-/d6*+";
      assertEquals(expectedResult, LinkedStack.convertToPostfix(infixExpression));
   }

   @Test
   //Should be able to handle if the expression has invalid operators mixed in.
   public void testWithInvalids()
   {
      String infixExpression = "a*& b /#@(c-$a)+ ?d<* e!";
      String expectedResult = "ab*ca-/de*+";
      assertEquals(expectedResult, LinkedStack.convertToPostfix(infixExpression));
   }
}