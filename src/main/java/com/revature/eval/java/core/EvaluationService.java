package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		
		phrase = phrase.replaceAll("-", " ");
		String[] phraseArray = phrase.split(" ");
		
		char[] acronArray = new char[phraseArray.length];
		
		for(int i=0; i<phraseArray.length;i++ ) {
			acronArray[i] = phraseArray[i].charAt(0);
		}
		
		String acron = new String(acronArray);
		
		return acron.toUpperCase();
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			if(this.sideOne == this.sideTwo && this.sideTwo == this.sideThree) {
				return true;
			}else {
				return false;				
			}
		}

		public boolean isIsosceles() {
			if(this.sideOne == this.sideTwo || this.sideTwo == this.sideThree || this.sideOne == this.sideThree) {
				return true;
			}else {
				return false;				
			}
		}

		public boolean isScalene() {
			if(this.sideOne != this.sideTwo && this.sideTwo != this.sideThree && this.sideOne != this.sideThree) {
				return true;
			}else {
				return false;				
			}
		}
	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	

//Just a method to get the letter score. There is probably an easier way, but this wasn't bad
	public int getLetterScore(char c) {
		if(c=='A'||c=='E'||c=='I'||c=='O'||c=='U'||c=='R'||c=='S'||c=='T'||c=='L'||c=='N') {
			return 1;				
		}
		if(c=='D'||c=='G') {
			return 2;
		}
		if(c=='B'||c=='C'||c=='M'||c=='P') {
			return 3;
		}
		if(c=='F'||c=='H'||c=='V'||c=='W'||c=='Y') {
			return 4;
		}
		if(c=='K') {
			return 5;
		}
		if(c=='J'||c=='X') {
			return 8;
		}
		if(c=='Q'||c=='Z') {
			return 10;
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	public int getScrabbleScore(String string) {
		
		string = string.toUpperCase();
		
		//Easier to work with a char array
		char[] charArray = string.toCharArray();
		
		
		int total = 0;//initialize
		
		//add up the score using the getLetterScore method
		for(int i = 0;i<charArray.length;i++) {
			total += getLetterScore(charArray[i]);
		}
		return total;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		string = string.replaceAll("[^0-9]", ""); //get rid of anything not a number
		
		//Remove country code 1 if present
		if(string.charAt(0)=='1') {
			string = string.replaceFirst("1", "");
		}
		
		//Check to make sure it's the right size
		if(string.length()!=10) {
			throw new IllegalArgumentException();
		}
		return string;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		
		//remove upper case and change newlines and commas to single spaces
		string = string.toLowerCase();
		string = string.replace("\n", " ");
		string = string.replace(",", " ");
		string = string.replace("  ", " ");
		
		//change it to a word array
		String[] wordArray = string.split(" ");
		
		//Create an Array without duplicates using a LinkedHashSet
        LinkedHashSet<String> wordSet = new LinkedHashSet<>( Arrays.asList(wordArray));
        String[] wordArrayNoDupe = wordSet.toArray(new String[] {});

        
		//initialize the HashMap and a counting variable
		Map<String,Integer> wordCount = new HashMap<String,Integer>();
		Integer count = 0;

		//Check the elements of the Non-dupe array against the full word array
		for(int i=0;i<wordArrayNoDupe.length;i++) {
			
			count=0;
			
			for(int j=0; j<wordArray.length; j++) {
				
				if(wordArrayNoDupe[i].equals(wordArray[j])) {
					count++;
				}
			}
			wordCount.put(wordArrayNoDupe[i], count);
				
				
		}
		
		return wordCount;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	@SuppressWarnings("rawtypes")
	static class BinarySearch<T extends Comparable> {
		private List<T> sortedList;

		@SuppressWarnings("unchecked")
		public int indexOf(T t) {
			
			//initialize index variables
			int minIndex = 0;
			int maxIndex = sortedList.size() - 1;
			int midIndex = (maxIndex + minIndex)/2;
			
			//Binary Search
			while(minIndex<=maxIndex) {
				if(t.compareTo(sortedList.get(midIndex))==0) {
					return midIndex;
				}else if(t.compareTo(sortedList.get(midIndex))>0) {
					minIndex = midIndex + 1;
				}else {
					maxIndex = midIndex - 1;
				}
				midIndex = (maxIndex + minIndex)/2;
			}
			
			return -1;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		
		//Initialize Vars and
		String[] wordArray = string.split(" ");
		StringBuilder pigLatinPhrase = new StringBuilder();

		//Create a regex pattern to look for vowels
		Pattern vowel = Pattern.compile("[aeoiu]");
		
		for(int i=0; i<wordArray.length;i++) {
			
			//find the first vowel
			Matcher vowelMatcher = vowel.matcher(wordArray[i]);
			vowelMatcher.find();
			int firstVowelIndex = vowelMatcher.start();
			
			
			//use the index of the first vowel to construct the piglatin word
			if(firstVowelIndex == 0) {
				pigLatinPhrase.append(" " + wordArray[i] + "ay");				
			}else{
				if(wordArray[i].charAt(firstVowelIndex - 1) == 'q' ) {//this if statement handles the weird "qu" sound
					firstVowelIndex++;
				}
				pigLatinPhrase.append(" " + wordArray[i].substring(firstVowelIndex)+wordArray[i].substring(0,firstVowelIndex)+"ay");
			}
		}
		
		pigLatinPhrase.deleteCharAt(0);
		return pigLatinPhrase.toString();
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {

		//Get the String representation of the number
		Integer integer = input;
		String inputString = integer.toString();
		
		//initialize variables and get number of digits N
		int armstrong = 0;
		int N = inputString.length();
		
		//Just one digit automatically returns true without wasting time on calculation
		if(N==1) {
			return true;
		}
		
		//calculate sum of digits to the power of N
		for(int i =0; i<inputString.length();i++) {
			double digit = (double) Double.valueOf(inputString.substring(i,i+1));
			armstrong += (int) Math.pow(digit,N);
		}
		
		//check the conditions for an armstrong number
		if(armstrong == input) {
        	return true;
        }else {
        	return false;        	
        }
		

	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	
	public List<Long> calculatePrimeFactorsOf(long l) {

		//initialize list
		List<Long> primeFactors = new ArrayList<Long>();
		
		
		
		//the highest number we need to check
		long N = (long) Math.sqrt(Double.valueOf(l));
		
		//run the algorithm
		for(long i=2 ;i <= N;i++) {
			while(l%i==0) {
				primeFactors.add(i);
				l/=i;
			}
		}
		
		//if we haven't found any primes by sqrt(l), then l must be prime
		if(primeFactors.size()==0) {
			primeFactors.add(l);
		}
		
		return primeFactors;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			
			//make a character array for messing with ascii codes
			char[] charArray = string.toCharArray();
			
			//make patterns and matchers for upper and lower case separately
			Pattern upperCase = Pattern.compile("[A-Z]");
			Matcher ucMatcher = upperCase.matcher(string);
			Pattern lowerCase = Pattern.compile("[a-z]");
			Matcher lcMatcher = lowerCase.matcher(string);
			
			
			//rotate uppercase in array using ascii code
			while(ucMatcher.find()==true) {
				int i = ucMatcher.start();
				int ascii = charArray[i];
				ascii = (ascii-65+key)%26+65;
				charArray[i] = (char) ascii;
			}
			
			//rotate lowercase in array using ascii code
			while(lcMatcher.find()==true) {
				int i = lcMatcher.start();
				int ascii = charArray[i];
				ascii = (ascii-97+key)%26+97;
				charArray[i] = (char) ascii;
			}
			
			//Convert the array to a string to finish
			string = new String(charArray);
			return string;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		
		//Just to pass the last test
		if(i<1) {
			throw new IllegalArgumentException();
		}
		
		//initialize variables
		int count=0;
		int num = 1;
		
		//exit the loop when count = i
		while(count < i) {
			num++;
			
			//The sqrt scheme does not work with 2 or 3 because j starts at 2
			if(num == 2 || num == 3) {
				count++;
				continue;
			}
			
			//only need to check as high as N=sqrt(num)
			int N = (int) Math.sqrt(Double.valueOf(num)); 

			//check if num is prime by going through every number 2<j<N
			for(int j=2;j<=N;j++) {
				
				//if not prime, break
				if(num%j == 0) {
					break;
				}
				
				//if prime, add it to the count
				if( j == N) {
					count++;
				}
			}
		}
		return num;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			
			string = string.replaceAll("[,. ]","");
			
			StringBuilder stringb = new StringBuilder();

			//make a character array for messing with ascii codes
			char[] charArray = string.toCharArray();
			
			//make patterns and matchers for upper and lower case separately
			Pattern pattern = Pattern.compile("[A-Z]");
			Matcher matcher = pattern.matcher(string);
			
			
			//rotate uppercase in array using ascii code
			while(matcher.find()==true) {
				int i = matcher.start();
				int ascii = charArray[i];
				ascii = 155 - ascii;
				charArray[i] = (char) ascii;
			}
			
			matcher.reset();
			pattern = Pattern.compile("[a-z]");
			matcher = pattern.matcher(string);
			
			//rotate lowercase in array using ascii code
			while(matcher.find()==true) {
				int i = matcher.start();
				int ascii = charArray[i];
				ascii = 219-ascii;
				charArray[i] = (char) ascii;
			}
			
			//Convert the array to a string
			string = new String(charArray).toLowerCase();
			
			int i=0;
			while(i<string.length()) {
				stringb.append(" ");
				if(i+5>=string.length()) {
					stringb.append(string.substring(i));
				}else {
					stringb.append(string.substring(i,i+5));
				}
				i+=5;
					
			}
			
			stringb.replace(0, 1, "");
			return stringb.toString();
		}


		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			string = AtbashCipher.encode(string);
			string = string.replace(" ","");
			
			
			return string;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		//remove all the dashes for easier parsing
		string=string.replace("-", "");
		
		//create a pattern and matcher for chars that don't belong
		Pattern pattern = Pattern.compile("[^0-9X]");
		Matcher matcher = pattern.matcher(string);
		
		//if something doesn't belong return false
		if(matcher.find()) {return false;}
		
		//initialize vars used in the loop
		int isbn=0;
		Character character;
		int val;
		
		//run isbn check function
		for(int i=0,j=10; i<10 && j>0; i++,j--) {
			character = string.charAt(i);
			
			//make sure X counts as 10
			if(character.equals('X')) {
				val=10;
			
			//make sure everything else counts as it's numerical value
			}else {
				val=(int) Integer.valueOf(character)-48;
			}
			isbn+= val*j;
		}
		
		//decide whether it's a valid isbn
		if(isbn%11==0) {
			return true;
		}else {
			return false;
			}
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		string = string.replace(" ", "");
		string = string.toLowerCase();
		
		char character;
		boolean containsChar;
		for(int i =97; i<=122;i++) {
			character =(char)  i;
			containsChar = string.contains(Character.toString(character));
			if(!containsChar) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		
		long seconds;

		//get the start date/time in epoch seconds
		if(given instanceof LocalDateTime) {
			LocalDateTime dateTime = (LocalDateTime) given;
			seconds = dateTime.toEpochSecond(ZoneOffset.of("Z"));
		}else if(given instanceof LocalDate) {
			LocalDate startDate = (LocalDate) given;
			long days = startDate.toEpochDay();
			seconds = days*24*3600;
		}else {throw new IllegalArgumentException();}
		
		
		//add a billion
		seconds+= 1000000000;
		
		//convert epoch seconds to LocalDateTime
		LocalDateTime date = LocalDateTime.ofEpochSecond(seconds,0,ZoneOffset.of("Z"));
		
		return date;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		
		//initialize variables
		int sum=0;
		int j = 1;
		
		//add j to sum if it's a multiple of a member of set
		while(j<i) {
			for(int k : set) {
				if(j%k==0) {
					sum+=j;
					break;  //break out of the second loop to prevent adding duplicates
				}
			}
			j++;
		}
		
		return sum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		
		string = string.replace(" ", "");
		
		//check for non-numbers;
		Pattern notnumber = Pattern.compile("[^0-9]");
		Matcher matcher = notnumber.matcher(string);
		if(matcher.find()) {
			return false;
		}
		
		int N = string.length();
		int sum=0;
		char character;
		
		for(int i=N-2;i>=0;i-=2) {
			character = string.charAt(i);			
			sum += Character.getNumericValue(character);
		}
		System.out.println(sum);

		
		for(int i=N-1;i>=0;i-=2) {
			character = string.charAt(i);
			int n = Character.getNumericValue(i);
			if(2*n > 9) {
				sum += 2*n-9;
				continue;
			}
			sum += 2*n;
		}
		System.out.println(sum);
		if(sum%10==0) {
			return true;
		}
		return false;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// TODO Write an implementation for this method declaration
		return 0;
	}

}
