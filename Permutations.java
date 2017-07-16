import java.util.ArrayList;

/**
 * This class constructs all possible words and permutations
 * from the sequence of letters, using ALL the letters.
 * It uses the Dictionary object to find valid words.
 * 
 * @author Kelly Xie (kyx203)
 */

public class Permutations 
{

	// data fields
	private String letters = "";
	private ArrayList<String> perms = new ArrayList<String>();
	private ArrayList<String> words = new ArrayList<String>();
	
	
	/**
	 * This is a one param constructor that creates a Permutations
	 * object. It also tests the String argument for 
	 * illegal characters.
	 * 
	 * @param letters is a String object that contains the sequence
	 * of letters to be used
	 * @throws IllegalArgumentException if letters contains characters
	 * that are not (a-z)
	 * 
	 * @author Kelly Xie
	 */
	public Permutations(String letters) throws IllegalArgumentException 
	{
		// check if argument contains any illegal characters
		try
		{
			for (int i=0; i<letters.length(); i++) 
			{
				if (!Character.isLetter(letters.charAt(i))) // legal characters are letters (a-z)
				{
					throw new IllegalArgumentException();
				}
			}
		}
		catch (IllegalArgumentException e)
		{
			System.err.println("Error: you entered an invalid character; "
					+ "only letters can be accepted.");
			System.exit(1);
		}
		
		this.letters = letters; // store user input if valid
	}
		
	
	/**
	 * This method computes and returns a list of ALL POSSIBLE PERMUTATIONS
	 * of letters in this permutation object. The list should
	 * exclude duplicate words.
	 * 
	 * @return an ArrayList of Strings that contains all permutations of letters
	 * @throw OutOfMemoryError for objects that contain more than 10 letters
	 * 
	 * @author Kelly Xie
	 */
	public ArrayList<String> getAllPermutations() throws OutOfMemoryError
	{
		String prefix = "";
		// pass letters parameter to private helper method
		return getAllPermutations(prefix, this.letters); 
	}

	
	/**
	 * This is a recursive helper method that starts from the first 
	 * character of string (prefix), then recursively finds permutations 
	 * of the remaining characters.
	 * 
	 * @param Strings prefix and letters
	 * @return a String ArrayList of all possible unique permutations
	 * 
	 * @author Kelly Xie
	 */
	private ArrayList<String> getAllPermutations(String prefix, String letters) 
	{
	    if (letters.length() == 0) // base case
	    {
	    	if (!perms.contains(prefix))
	    		perms.add(prefix); // add this permutation to ArrayList IF it's not a duplicate
	    }
	    else 
	    {
	        for (int i = 0; i < letters.length(); i++) // recursive case
	        {
	        	// move a character over from letters to prefix
	        	String newPrefix = prefix + letters.charAt(i);
	        	String remainingLetters = letters.substring(0, i) + letters.substring(i+1);
	        	
	            getAllPermutations(newPrefix, remainingLetters); // recursive call
	        }
	    }
	    return perms;
	}
	
	
	
	/**
	 * This method computes and returns a list of ALL WORDS
	 * in the Dictionary object that can be created from 
	 * the letters in this Permutation object.
	 * The list should exclude duplicate words.
	 * 
	 * @param a Dictionary object containing all words from file
	 * @return an ArrayList of Strings that contains all words created
	 * from the given letters
	 * 
	 * @author Kelly Xie
	 */
	public ArrayList<String> getAllWords(Dictionary dictionary)
	{
		String prefix = "";
		// pass extra parameters to private helper method 
		this.perms = getAllWords(dictionary, prefix, this.letters);
		
		for (int i=0; i<perms.size(); i++) 
		{
			// if element is a word in the dictionary, add it to ArrayList words
			if (dictionary.isWord(perms.get(i)))
				words.add(perms.get(i));
		}
		return words;
	}
	
	
	/**
	 * This is a recursive helper method similar to getAllPermutations(),
	 * except that it implements the efficient approach, and only
	 * generates permutations to completion if they are in the dictionary.
	 * In this way, it uses the backtracking method.
	 * 
	 * @param Strings prefix and letters, and Dictionary object
	 * @return a String ArrayList of all possible unique permutations
	 * 
	 * @author Kelly Xie
	 */
	private ArrayList<String> getAllWords(Dictionary dictionary, String prefix, String letters)
	{
		if (letters.length() == 0) // base case
		{
	    	if (!perms.contains(prefix))
	    		perms.add(prefix); // add this permutation to ArrayList IF it's not a duplicate
		}
	    else 
	    {
	        for (int i = 0; i < letters.length(); i++) // recursive case
	        {
	        	// check if this prefix is a prefix to any of the words in dictionary
	    		// if not, stop generating this string and move on to next permutation!
	        	if (!dictionary.isPrefix(prefix))
	        	{
	        		if (prefix != "")
	        			continue;
	        	}
	        	// move a character over from letters to prefix
	        	String newPrefix = prefix + letters.charAt(i);
	        	String remainingLetters = letters.substring(0, i) + letters.substring(i+1);
	        	
	            getAllWords(dictionary, newPrefix, remainingLetters); // recursive call
	        }
	    }
		
	    return perms;
	}

}
