package me.geso.regexp_trie;

import org.junit.Test;
import me.geso.regexp_trie.RegexpTrie;

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

