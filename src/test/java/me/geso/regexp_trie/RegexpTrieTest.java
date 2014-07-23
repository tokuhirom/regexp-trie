/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package me.geso.regexp_trie;

import java.util.regex.Pattern;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tokuhiro Matsuno <tokuhirom@gmail.com>
 */
public class RegexpTrieTest {

	public RegexpTrieTest() {
	}

	@Test
	public void test() {
		RegexpTrie trie = new RegexpTrie();
		trie.add("foobar");
		trie.add("fooxar");
		trie.add("foozap");
		trie.add("fooza");
		String re = trie.regexp();
		System.out.println(re);
		assertEquals("foo(?:bar|xar|zap?)", re);

		Pattern pattern = Pattern.compile(re);
		assertFalse(pattern.matcher("foo").matches());
		assertTrue(pattern.matcher("foobar").matches());
		assertTrue(pattern.matcher("fooxar").matches());
		assertTrue(pattern.matcher("foozap").matches());
		assertTrue(pattern.matcher("fooza").matches());
		assertFalse(pattern.matcher("fooz").matches());
	}

	@Test
	public void test2() {
		RegexpTrie trie = new RegexpTrie();
		trie.add("a");
		trie.add("b");
		trie.add("c");
		trie.add("cca");
		String re = trie.regexp();
		System.out.println(re);
		assertEquals("(?:c(?:ca)?|[ab])", re);
	}

	@Test
	public void testJapanese() {
		RegexpTrie trie = new RegexpTrie();
		trie.add("山田太郎");
		trie.add("山田孝之");
		trie.add("山田農園");
		String re = trie.regexp();
		System.out.println(re);
		assertEquals("山田(?:太郎|孝之|農園)", re);
	}

}
