package com.beyonnex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AnagramService {

	private static final int CHAR_SIZE = 256;
	private static Map<String, List<String>> ANAGRAM_MAP = new HashMap<>();
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		while (true) {
			System.out.print("Feature Number to proceed: ");
			String lineFeature = scan.nextLine();
			if (!lineFeature.equals("1") && !lineFeature.equals("2") && !lineFeature.equals("q")) {
				System.out.println("Feature number should be 1 or 2! Enter 'q' to exit.");
				continue;
			}
			if (lineFeature.equalsIgnoreCase("q")) {
				break;
			}

			switch (lineFeature) {
			case "1" -> executeFeature1();
			case "2" -> executeFeature2();
			}
		}
	}

	private static void executeFeature2() {
		String lineString = getScannedString(scan, "Enter String : ");
		List<String> list = ANAGRAM_MAP.get(lineString);
		if (list == null) {
			addStringsToMap(lineString);
			list = ANAGRAM_MAP.get(lineString);
		}
		System.out.println(Arrays.toString(list.toArray()));
	}

	private static void executeFeature1() {
		String lineString1 = getScannedString(scan, "Enter First String :");
		String lineString2 = getScannedString(scan, "Enter Second String :");
		if (isAnagram(lineString1.toCharArray(), lineString2.toCharArray())) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		addStringsToMap(lineString1, lineString2);
	}

	private static String getScannedString(Scanner scan, String command) {
		System.out.print(command);
		return scan.nextLine();
	}

	public static void addStringsToMap(String... stringArray) {
		if(stringArray == null) {
			return;
		}
		for (String string : stringArray) {
			List<String> list = ANAGRAM_MAP.get(string);
			if (list != null && list.size() > 0) {
				list.add(string);
				return;
			}
			List<String> anagramList = ANAGRAM_MAP.keySet().stream().filter(key -> isAnagram(key.toCharArray(), string.toCharArray()))
					.collect(Collectors.toList());
			if (anagramList.size() > 0) {
				ANAGRAM_MAP.put(string, anagramList);
				anagramList.forEach(str -> ANAGRAM_MAP.get(str).add(string));
			} else {
				ANAGRAM_MAP.put(string, new ArrayList<>());
			}
		}
	}

	public static boolean isAnagram(char[] charArray1, char[] charArray2) {
		if (charArray1 == null || charArray2 == null) {
			return false;
		}
		if (charArray1.length != charArray2.length) {
			return false;
		}

		int length1[] = new int[CHAR_SIZE];
		Arrays.fill(length1, 0);
		int length2[] = new int[CHAR_SIZE];
		Arrays.fill(length2, 0);
		int i;
		for (i = 0; i < charArray1.length; i++) {
			length1[charArray1[i]]++;
			length2[charArray2[i]]++;
		}

		for (i = 0; i < CHAR_SIZE; i++) {
			if (length1[i] != length2[i]) {
				return false;
			}
		}

		return true;
	}

	public static Map<String, List<String>> get_ANAGRAM_MAP() {
		return new HashMap<>(ANAGRAM_MAP);
	}
}
