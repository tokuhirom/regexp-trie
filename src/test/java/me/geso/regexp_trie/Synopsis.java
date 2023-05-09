package me.geso.regexp_trie;

import org.junit.jupiter.api.Test;

public class Synopsis {
	@Test
	public void test() {
		RegexpTrie trie = new RegexpTrie();
		trie.add("foobar");
		trie.add("fooxar");
		trie.add("foozap");
		trie.add("fooza");
		System.out.println(trie.regexp());
		// → (?:foo(?:bar|xar|zap?))
	}
}

