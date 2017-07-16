import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;


/**
 * This class contains the main method that parses 
 * the command line arguments, creates the Dictionary 
 * and Permutations objects, and displays output to the console.
 * 
 * @author Kelly Xie (kyx203)
 */

public class ScrabbleHelper 
{
	// from command line, first input is the file name
	// and second input is a String of letters 
	public static void main(String[] args) throws IllegalArgumentException, FileNotFoundException
	{
		
		// ============== input validation ============== 
		
		// check if user entered any arguments at all
		try 
		{
			args[0].equals("");	
		}
		catch (ArrayIndexOutOfBoundsException e) 
		{
			// program terminates if user enters no argument
			System.err.println("Error: no arguments were entered.");
			System.exit(1);
		}
		
		// next, check if user entered a file name
		try 
		{
			if (args[0].indexOf(".txt") < 0)
				throw new IllegalArgumentException();	
		}
		catch (IllegalArgumentException e) 
		{
			// program terminates if user did not enter file name
			System.err.println("Error: missing name of the input file.");
			System.exit(1);
		}
		
		// finally, check if user entered a sequence of characters
		try 
		{
			args[1].equals("");
		} 
		catch(ArrayIndexOutOfBoundsException e) 
		{ 
			// program terminates if user did not enter any characters
			System.err.println("Error: no letters provided, cannot compute any words.");
			System.exit(1);
		}
		
		
		// ============== processing inputs ============== 
			
		File file = new File(args[0]); // create File object
		Dictionary dict = new Dictionary(file); // create a Dictionary object
		
		String letters = args[1].toLowerCase(); // make user input all lowercase
		Permutations perm = new Permutations(letters); // create a Permutations object

		output(dict, perm); // get results

	}
	
	
	/**
	 * This method uses the Dictionary and Permutations objects
	 * created in the main method to format and print results.
	 * 
	 * @param Dictionary object
	 * @param Permutations object
	 * 
	 * @author Kelly Xie
	 */
	public static void output(Dictionary d, Permutations p)
	{
		ArrayList<String> allWords = new ArrayList<String>(); // new ArrayList for storing matching words
		allWords = p.getAllWords(d); // call getAllWords() method on Permutations object
		Collections.sort(allWords); // sort ArrayList alphabetically
		
		int count = allWords.size();
		if (count < 1)
		{
			System.out.println("No words found");
		}
		else 
		{
			if (count == 1)
				System.out.println("Found 1 word: ");
			else 
				System.out.println("Found " + count + " words: ");
			
			for (int i = 0; i < allWords.size(); i++)
				System.out.println("   " + allWords.get(i)); // formatting output
			System.out.println();
		}
			
			
	}

}
