package net.yakovdavis.enigmus;

/*
Main encryption class

Only should exist in one instance, because of how symbol tables
switching works
*/

public class Encrypter
{
	private static String symbolTable = "";
	private static int ALPHABET_LENGTH;
	
	public static void setSymbolTable(String name)
	{
		//Static method for switching symbol tables, unicode default
		
		switch(name)
		{
			case("basic"):
				symbolTable = SymbolTables.BASIC;
				ALPHABET_LENGTH = symbolTable.length();
				break;
			case("extended"):
				symbolTable = SymbolTables.EXTENDED;
				ALPHABET_LENGTH = symbolTable.length();
				break;
			case("hand_writing"):
				symbolTable = SymbolTables.HANDWRITING;
				ALPHABET_LENGTH = symbolTable.length();
				break;
			default:
				symbolTable = "";
				ALPHABET_LENGTH = 65536;
				break;
		}
	}
	
	public String encrypt(String str, String k)
		{
			//Encryption method
			
			KeyString key = new KeyString(k);//initializing object for easy key operations
			String res = "";//for result
			
			
			for(int i = 0; i < str.length(); i++)//main work loop
			{
				int tmp = getCharCode(str.charAt(i)) + getCharCode(key.getNextChar());
				if(tmp >= ALPHABET_LENGTH)
					tmp = tmp - ALPHABET_LENGTH;
				res = res + getCharFromCode(tmp);
			}
			
			return res;
		}
	
	public String decrypt(String str, String k)
	{
		//Decryption method
		
		KeyString key = new KeyString(k);//initializing object for easy key operations
		String res = "";//for result

		for(int i = 0; i < str.length(); i++)//main work loop
		{
			int tmp = getCharCode(str.charAt(i)) - getCharCode(key.getNextChar());
			if(tmp < 0)
				tmp = tmp + ALPHABET_LENGTH;
			res = res + getCharFromCode(tmp);
		}

		return res;
	}
	
	//Methods for retrieving symbol codes and symbols from codes from specified tables
	
	private int getCharCode(char c)
	{
		if(symbolTable.equals(""))
			return (int) c;
		else
			return symbolTable.indexOf(c);
	}
	
	private char getCharFromCode(int i)
	{
		if(symbolTable.equals(""))
			return (char) i;
		else
			return symbolTable.charAt(i);
	}
	
	private class KeyString
	{
		//Sub-class for easy key operations
		
		private String key;
		private int pos;
		
		public KeyString(String k)
		{
			key = k;
			pos = 0;
		}
		
		public char getNextChar()
			{
				if(pos == key.length())
				{
					pos = 0;
				}
				pos++;
				return key.charAt(pos - 1);
			}
	}
}
