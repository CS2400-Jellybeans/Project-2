import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.TestCase;
import project2.LinkedStack;

public class LinkedJUnitTest extends TestCase
{
   String[] infixExpressions = {"a+b"};
   String[] expectedResults = {"ab+"};
   @Override
   protected void setUp() throws Exception
   {
   }

   @Test
   public void testConvertToPostfix()
   {
      for(int i = 0; i < infixExpressions.length; i++)
      {
         assertEquals(expectedResults[i], LinkedStack.convertToPostfix(infixExpressions[i]));
      }
   }
}