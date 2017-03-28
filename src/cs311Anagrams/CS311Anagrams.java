package cs311Anagrams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CS311Anagrams {
	public static void main(String[] args) {
		//check args
		if(args.length != 1) {
			System.err.println("I dont understand your args");
			System.exit(0);
		}
		//load dictionary
		ArrayList<DictEntry> dictionary = readDictionary(args[0]);
		System.out.println("Loaded " + dictionary.size() + " dictionary entries");
		
		//sort each dictEntry orgWord to sortedWord
		for(DictEntry de : dictionary) {
			de.sortedWord = sortString(de.orgWord);
			System.out.println(de.orgWord + " -> " + de.sortedWord);
		}
		System.out.println("sorted dictEntry's");
		
		//sort array list
		
	}
	
	public static ArrayList<DictEntry> sortDict(ArrayList<DictEntry> dict) {
		quicksortDict(dict,0,dict.size());
		return dict;
	}
	
	public static void quicksortDict(ArrayList<DictEntry> dict, int p, int r) {
		if(p<r){
			int q=partition2(dict,p,r);
			quicksortDict(dict,p,q);
			quicksortDict(dict,q+1,r);
		}
	}
	
	public static int partition2(ArrayList<DictEntry> dict, int p, int r) {
		return 0;
	}
	
	public static String sortString(String word){
		char[] wordAsChar = word.toCharArray();
		quicksortString(wordAsChar,0,word.length());
		return new String(wordAsChar); //is passed by reference
	}
	
	//from the book
	public static void quicksortString(char[] word, int p, int r){
		if(p<r) {
			int q= partition(word,p,r);
			quicksortString(word,p,q);
			quicksortString(word,q+1,r);
		}
	}
	
	//the same method from the book
	public static int partition(char[] word, int p, int r) {
		//char x = word[r-1]; //cast to int for easy comparisons
		int i = p - 1;
		for(int j=p; j<= r-1; j++) {
			if(word[j] < word[r-1]) {
				i++;
				//swap word[i] with word[j]
				char temp = word[i];
				word[i] = word[j];
				word[j] = temp;
			} 
		}
		//swap word[i+1] with word[r]
		char temp = word[i+1];
		word[i+1] = word[r-1];
		word[r-1] = temp;
		return i+1;
	}
	
	public static ArrayList<DictEntry> readDictionary(String filename) {
		ArrayList<DictEntry> dictionary = new ArrayList<DictEntry>();  //this will hold our dicEntries
		System.out.println("reading " + filename);
		
		try { 
			//load words from dictionary to dictEntry array list
			File file = new File(filename);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				dictionary.add(new DictEntry(line));  // create new entry and append
			}
			fileReader.close();
		} catch (Exception e) {
			System.out.println("failed to read file");
			System.exit(0);
		}
		
		System.out.println("Finished reading " + filename);
		
		return dictionary;
	}
}
