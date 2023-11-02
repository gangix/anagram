package com.beyonnex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

public class AnagramServiceTest {

	@Test
	public void checkAnagram_GivenTwoAnagramString_ThenReturnsTrue() {
		char[] str1 = "ABCDEFGHJSHSGSTSAWE".toCharArray();
		char[] str2 = "GHJASBHSCGSDTESFAWE".toCharArray();
		boolean actual = AnagramService.isAnagram(str1, str2);
		assertTrue(actual);
	}

	@Test
	public void checkAnagram_GivenTwoNotAnagramString_ThenReturnsFalse() {
		char[] str1 = "ABCDEFGHJSHSGSTSAWE".toCharArray();
		char[] str2 = "ABCDEFGHJSHSGSTSAWF".toCharArray();
		boolean actual = AnagramService.isAnagram(str1, str2);
		assertFalse(actual);
	}

	@Test
	public void checkAnagram_GivenOneNullString_ThenReturnsFalse() {
		char[] str1 = "ABCDEFGHJSHSGSTSAWE".toCharArray();
		boolean actual = AnagramService.isAnagram(str1, null);
		assertFalse(actual);
	}

	@Test
	public void checkAnagram_GivenBothNullString_ThenReturnsFalse() {
		boolean actual = AnagramService.isAnagram(null, null);
		assertFalse(actual);
	}

	@Test
	public void addStringToMap_GivenStringList_ThenShouldHaveProperMap() {
		AnagramService.addStringsToMap("ABCDEFGHJSHSGSTSAWE", "GHJASBHSCGSDTESFAWE", "ABCDEFGXJSHSGSTSAWF", "ABCDEFGFJSHSGSTSAWX",
				"GFJSHSGSTSAWXABCDEF");

		assertEquals(
				Set.of("GFJSHSGSTSAWXABCDEF", "ABCDEFGFJSHSGSTSAWX", "ABCDEFGHJSHSGSTSAWE", "ABCDEFGXJSHSGSTSAWF", "GHJASBHSCGSDTESFAWE"),
				AnagramService.get_ANAGRAM_MAP().keySet());
	}
}