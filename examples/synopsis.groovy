@GrabResolver(name='tokuhirom', root='https://tokuhirom.github.io/maven/releases/')
@Grab('me.geso:regexp-trie:0.0.1')

import me.geso.regexp_trie.RegexpTrie;

def trie = new RegexpTrie();
["foobar", "fooxar", "foozap", "fooza"].forEach {
	trie.add(it);
}
println(trie.regexp()) // → foo(?:bar|xar|zap?)
