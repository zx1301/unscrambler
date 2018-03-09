/*Anthony Kim
cs220-01
10/26/17
Project #2
*/

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

//This program uses a file called words.txt and uses a random word from the file as its input.
//Then, it scrambles the word randomly.
//After, The user is given an option to try to unscramble the word until the word matches its original state.
public class Unscrambler{
	//Method for scrambling the original random word
	public static String scrambler(String randFileWord){
	int wordLength = randFileWord.length();
	char wordArray[] = randFileWord.toCharArray();
	Random randGen = new Random();
	for(int i = 0;i < wordLength;i++){
		int y = randGen.nextInt(wordLength);
		char temp = wordArray[i];
		wordArray[i] = wordArray[y];
		wordArray[y] = temp;
	}
	wordArray.toString();
	return new String(wordArray);
	}

	//Method for swaping letters when user inputs two indexes
	public static String Swap(String scrambledWord, int index1, int index2){
		char scrambledArray[] = scrambledWord.toCharArray();
		char temp1 = scrambledWord.charAt(index1);
		scrambledArray[index1] = scrambledArray[index2];
		scrambledArray[index2] = temp1;

		return new String(scrambledArray);
	}
	public static void main (String [] args) throws IOException{
		//Declare variable, create objects
		FileInputStream fileByteStream = null;
		Scanner inFS = null;
		int lines = 0;
		String afterSwap = "";
		String randFileWord = "";
		Random randGen = new Random();
		boolean done = false;
		Scanner scnr = new Scanner(System.in);

		//Use file as input and count lines
		System.out.println("Opening file words.txt.");
		fileByteStream = new FileInputStream("words.txt");
		inFS = new Scanner(fileByteStream);
		while (inFS.hasNextLine()){
			inFS.nextLine();
			lines++;
		}

		//Choose random word from text file
		int randLine = randGen.nextInt(lines);
		fileByteStream = new FileInputStream("words.txt");
		inFS = new Scanner(fileByteStream);
		for(int b = 0;b <= randLine; b++){
			randFileWord = inFS.nextLine();
		}
		//unscrambling game
		String scrambledWord = scrambler(randFileWord);
		while(!done){
			for(int a=0;a < scrambledWord.length();a++){
				System.out.print(a);
			}
			System.out.println("\n" + scrambledWord);
			System.out.println("Enter 1 to swap a pair of letters");
			System.out.println("Enter 2 to solve");
			System.out.println("Enter 3 to quit");
			int numInput = scnr.nextInt();
			if(numInput == 1){
				System.out.println("Enter the indexes seperated by spaces");
				int index1 = scnr.nextInt();
				if(index1 >= scrambledWord.length() || index1 < 0){
					System.out.println("Invalid first index");
					continue;
				}
				int index2 = scnr.nextInt();
				if(index2 >= scrambledWord.length() || index2 < 0){
					System.out.println("Invalid second index");
					continue;
				}
				scrambledWord = Swap(scrambledWord,index1,index2);
				if(scrambledWord.equals(randFileWord)){
					done = true;
					System.out.println("Congratulations! You unscrambled the word!");
				}
			}
			if(numInput == 2){
				System.out.println(randFileWord);
				done = true;
			}
			if(numInput == 3){
				System.out.println("Program has ended");
				done = true;
			}
		}
	}
}