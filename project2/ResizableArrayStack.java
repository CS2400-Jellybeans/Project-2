package project2;

import java.util.EmptyStackException;
import java.util.Arrays;

public class ResizableArrayStack<T> implements StackInterface<T>
{
	private T[] stack;    // Array of stack entries
	private int topIndex; // Index of top entry
   private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
  
   public ResizableArrayStack()
   {
      this(DEFAULT_CAPACITY);
   } // end default constructor
  
   /**
   * Creates an array stack with a specified starting capacity.
   * @param initialCapacity The starting length of the array.
   */
   public ResizableArrayStack(int initialCapacity)
   {
      integrityOK = false;
      checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempStack = (T[])new Object[initialCapacity];
      stack = tempStack;
		topIndex = -1;
      integrityOK = true;
   } // end constructor

   @Override
   /**
   * Adds a new entry to the top of the stack.
   * @param newEntry The item to add to the stack.
   */
   public void push(T newEntry)
   {
      checkIntegrity();
      ensureCapacity();
      stack[topIndex + 1] = newEntry;
      topIndex++;
   } // end push
   
   /**
   * Ensures that the array is long enough. If capacity is exceeded, length is doubled.
   */
   private void ensureCapacity()
   {
      if (topIndex >= stack.length - 1) // If array is full, double its size
      {
         int newLength = 2 * stack.length;
         checkCapacity(newLength);
         stack = Arrays.copyOf(stack, newLength);
      } // end if
   } // end ensureCapacity

   @Override
   /**
   * Removes the item at the top of the stack.
   * @return The item that was removed.
   */
   public T pop()
   {
      checkIntegrity();
      if (isEmpty())
         throw new EmptyStackException();
      else
      {
         T top = stack[topIndex];
         stack[topIndex] = null;
         topIndex--;
         return top;
      } // end if
   } // end pop

   @Override
   /**
   * @throws EmptyStackException If the stack is empty.
   * @return The item currently at the top of the stack.
   */
   public T peek() throws EmptyStackException
   {
      checkIntegrity();
      if (isEmpty())
         throw new EmptyStackException();
      else
         return stack[topIndex];
   } // end peek

   @Override
   /**
   * Checks if the stack is currently empty.
   * @return True if the stack is empty. False otherwise.
   */
   public boolean isEmpty()
   {
      return topIndex < 0;
   } // end isEmpty

   @Override
   /**
   * Removes all items from the stack by looping through the array and deleting each entry.
   */
   public void clear()
   {
      checkIntegrity();
      
      // Remove references to the objects in the stack,
      // but do not deallocate the array
      while (topIndex > -1)
      {
         stack[topIndex] = null;
         topIndex--;
      } // end while
      //Assertion: topIndex is -1
   } // end clear

   /**
   * Enusres that the array has not becom corrupt.
   */
   private void checkIntegrity()
   {
      if(!integrityOK)
      {
         throw new SecurityException("ArrayStack object is corrupt.");
      }
   }
   
   /**
   * Ensures that the capacity of the array does not excced the maximum allowed capacity.
   */
   private void checkCapacity(int capacity)
   {
      if (capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempted to create a stack whose capacity exceeds " +
                                         "allowed maximum of " + MAX_CAPACITY);
   }
} // end ArrayStack
