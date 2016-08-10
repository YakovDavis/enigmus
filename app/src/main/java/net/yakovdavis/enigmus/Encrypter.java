package net.yakovdavis.enigmus;

public class Encrypter
{
	private static String symbolTable = "";
	private static int ALPHABET_LENGTH;
	
	public static void setSymbolTable(String name)
	{
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
			KeyString key = new KeyString(k);
			String res = "";
			
			for(int i = 0; i < str.length(); i++)
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
		KeyString key = new KeyString(k);
		String res = "";

		for(int i = 0; i < str.length(); i++)
		{
			int tmp = getCharCode(str.charAt(i)) - getCharCode(key.getNextChar());
			if(tmp < 0)
				tmp = tmp + ALPHABET_LENGTH;
			res = res + getCharFromCode(tmp);
		}

		return res;
	}
	
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
