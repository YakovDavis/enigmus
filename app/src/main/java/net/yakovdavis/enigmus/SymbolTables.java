package net.yakovdavis.enigmus;

public abstract class SymbolTables
{
	private static final String BASIC_TEMP = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!?@#$%^&*()_-=+[]{}/\'`№АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя ";
	private static final String HANDWRITING_TEMP = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!?@#$%^&*()_-=+[]{}/\'№АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя¹²³⁴<>";
	private static final String EXTENDED_TEMP = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!?@#$%^&*()_-=+[]{}/\'`№АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя ½⅓¼⅛⅔¾⅜¡¹²³⁴¢‰⅝¿⅞*•★∞ⁿ≈≠±×≤≥ñ©§°Ø£Ө√₴¥™®€";
	public static final String BASIC = BASIC_TEMP + '"' + ';' + ':' + '|';
	public static final String HANDWRITING = HANDWRITING_TEMP +  + '"' + ';' + ':' + '|';
	public static final String EXTENDED = EXTENDED_TEMP + '"' + ';' + ':' + '|';
}
