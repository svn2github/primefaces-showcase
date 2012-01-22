package com.prime.showcase.integration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestingUtils {

	public static int getInteger(String str) {
		Pattern intsOnly = Pattern.compile("\\d+");
		Matcher makeMatch = intsOnly.matcher(str);
		makeMatch.find();
		String inputInt = makeMatch.group();
		return Integer.valueOf(inputInt);
	}
}
