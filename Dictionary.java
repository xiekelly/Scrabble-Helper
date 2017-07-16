import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the collection of words parsed from the 
 * input dictionary file. It stores these words in an ArrayList<String> object.
 * The class also performs queries in the dictionary.
 * 
 * @author Kelly Xie (kyx203)
 */

public class Dictionary 
{

	// data field
	private ArrayList<String> dict = new ArrayList<String>();
	
	
	/**
	 * This is a one param constructor that processes a File object.
	 * The Dictionary object should store all words found in the file.
	 * 
	 * @param a File object that represents the input file
	 * @throws IllegalArgumentException if the file does not exist or is not readable
	 * 
	 * @author Kelly Xie
	 */
	public Dictionary(File f) throws IllegalArgumentException, FileNotFoundException
	{
		// input validation
		try
		{
			if (!f.exists() || f == null || !f.canRead())
				throw new IllegalArgumentException();
		}
		catch (IllegalArgumentException e)
		{
			System.err.println("Error: the file " + f + " does not exist.");
			System.exit(1);
		}
		
		Scanner text = new Scanner(f);
		while (text.hasNext()) { // read each line in file
			String word = text.nextLine();
			dict.add(word); // add each word to ArrayList
		}
		
		text.close();
	}
	
	
	/**
	 * This method determines if the String argument is a word
	 * stored in this dictionary, using recursive binary search.
	 * 
	 * @param a String that represents a permutation/sequence of letters
	 * @return a boolean representing whether the String is a word in the
	 * dictionary
	 * 
	 * @author Kelly Xie
	 */
	public boolean isWord(String str)
	{
		int index = WordBinarySearch(dict, str); // call recursive method
		if (index >= 0) // if we found a match
			return true;
		return false; // if no matches found
	}
	
	
	/**
	 * This method determines if the String argument is a prefix 
	 * for at least one of the words stored in this dictionary, 
	 * using recursive binary search.
	 * 
	 * @param a String that represents a permutation/sequence of characters
	 * @return a boolean that represents whether the String is a prefix
	 * for at least one of the dictionary words
	 * @throws UnsupportedOperationException if the operation is not supported
	 * 
	 * @author Kelly Xie
	 */
	public boolean isPrefix(String str) throws UnsupportedOperationException
	{
		int index = PrefixBinarySearch(dict, str); // call recursive method
		if (index >= 0) // if str is a prefix for at least one word in dictionary
			return true;
		return false; // if str is NOT a prefix for any of the words
	}
	
	
	/**
	 * These are private helper methods called by the isWord() method that 
	 * provide the actual recursion implementation of binary search to find word matches. 
	 * 
	 * @param dict: an ArrayList of strings that represents entire dictionary
	 * @param key: the word we are searching for
	 * @return an integer representing the index of the word that matches the key
	 * 
	 * @author Kelly Xie
	 */
	private int WordBinarySearch(ArrayList<String> dict, String key) 
	{
		int low = 0;
		int high = dict.size() - 1;
		return WordBinarySearch(dict, key, low, high); // call helper method
	}
	
	private int WordBinarySearch(ArrayList<String> dict, String key, int low, int high) 
	{
		if (low > high) // no match exists
			return -low - 1;
		
		int mid = (low + high) / 2;
		
		if (key.equals(dict.get(mid))) // base case: found a match!
			return mid;
		else if (key.compareTo(dict.get(mid)) < 0)
			return WordBinarySearch(dict, key, low, mid - 1); // recursive call
		else
			return WordBinarySearch(dict, key, mid + 1, high); // recursive call
			
	}
	
	
	/**
	 * These are private helper methods called by the isPrefix() method that 
	 * provide the actual recursion implementation of binary search to find prefix matches. 
	 * 
	 * @param dict: an ArrayList of strings that represents entire dictionary
	 * @param key: the prefix we are searching for
	 * @return an integer representing the index of the word that matches the key
	 * 
	 * @author Kelly Xie
	 */
	private int PrefixBinarySearch(ArrayList<String> dict, String key) 
	{
		int low = 0;
		int high = dict.size() - 1;
		return PrefixBinarySearch(dict, key, low, high); // call helper method
	}
	
	private int PrefixBinarySearch(ArrayList<String> dict, String key, int low, int high) 
	{
		if (low > high) // no match exists
			return -low - 1;
		
		int mid = (low + high) / 2; 
		
		if ((dict.get(mid)).startsWith(key)) // base case: found a prefix match!
			return mid;
		else if (key.compareTo(dict.get(mid)) < 0)
			return PrefixBinarySearch(dict, key, low, mid - 1); // recursive call
		else
			return PrefixBinarySearch(dict, key, mid + 1, high); // recursive call
			
	}

}
