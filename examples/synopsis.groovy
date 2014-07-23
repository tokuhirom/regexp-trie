import me.geso.regexp_trie.RegexpTrie;

def trie = new RegexpTrie();
["foobar", "fooxar", "foozap", "fooza"].forEach {
	trie.add(it);
}
println(trie.regexp()) // → foo(?:bar|xar|zap?)
