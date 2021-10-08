import org.junit.Test;

import junit.framework.TestCase;
import project2.LinkedStackTest;

public class LinkedJUnitTest extends TestCase
{
   @Test
   //Various assorted inputs and expected outputs.
   public void testConvertToPostfix()
   {
      String[] infixExpressions = {"a+b", "a^b-c+(a+d)^e", "a*b/(c-a)+d*e"};
      String[] expectedResults = {"ab+", "ab^c-ad+e^+", "ab*ca-/de*+"};
      for(int i = 0; i < infixExpressions.length; i++)
      {
         assertEquals("test", expectedResults[i], LinkedStackTest.convertToPostfix(infixExpressions[i]));
      }
   }

   @Test
   //Should be able to handle if null is passed into the method.
   public void testNull()
   {
      String infixExpression = null;
      String expectedResult = null;
      assertEquals(expectedResult, LinkedStackTest.convertToPostfix(infixExpression));
   }
   
   @Test
   //Should be able to handle if the expression is blank.
   public void testEmpty()
   {
      String infixExpression = "";
      String expectedResult = "";
      assertEquals(expectedResult, LinkedStackTest.convertToPostfix(infixExpression));
   }

   @Test
   //Should be able to handle if the expression has spaces in it.
   public void testWithSpaces()
   {
      String infixExpression = "a * b / (c - a) + d * e";
      String expectedResult = "ab*ca-/de*+";
      assertEquals(expectedResult, LinkedStackTest.convertToPostfix(infixExpression));
   }

   @Test
   //Should be able to handle if the expression has numbers instead of characters.
   public void testWithNumbers()
   {
      String infixExpression = "2*b/(c-2)+d*6";
      String expectedResult = "2b*c2-/d6*+";
      assertEquals(expectedResult, LinkedStackTest.convertToPostfix(infixExpression));
   }

   @Test
   //Should be able to handle if the expression has invalid operators mixed in.
   public void testWithInvalids()
   {
      String infixExpression = "a*& b /#@(c-$a)+ ?d<* e!";
      String expectedResult = "ab*ca-/de*+";
      assertEquals(expectedResult, LinkedStackTest.convertToPostfix(infixExpression));
   }
}