regexp-trie
===========

[![Build Status](https://travis-ci.org/tokuhirom/regexp-trie.svg?branch=master)](https://travis-ci.org/tokuhirom/regexp-trie)

Create complex regular expression from tokens by Trie.

## SYNOPSIS in GROOVY

	@GrabResolver(name='tokuhirom', root='https://tokuhirom.github.io/maven/releases/')
	@Grab('me.geso:regexp-trie:0.0.2')
	import me.geso.regexp_trie.RegexpTrie;

	def trie = new RegexpTrie();
	["foobar", "fooxar", "foozap", "fooza"].forEach {
		trie.add(it);
	}
	println(trie.regexp()) // → (?:foo(?:bar|xar|zap?))

## DESCRIPTION

This module generates regular expression from list of tokens.
It builds a trie-ized regexp as above.

You can only add plain strings into regular expression. You can't use meta characters in it. `a+b` is treated as `a\+b`, not "more than one a's followed by b".

## Using with Maven

You need to add following snippet into your pom.xml.

	<repositories>
	  <repository>
		<id>tokuhirom</id>
		<url>https://tokuhirom.github.io/maven/releases/</url>
	  </repository>
	</repositories>
	<dependencies>
	  <dependency>
		<groupId>me.geso</groupId>
		<artifactId>regexp-trie</artifactId>
		<version>0.0.1</version>
	  </dependency>
	</dependencies>

## COPYRIGHT AND LICENSE

	The MIT License (MIT)
	Copyright © 2014 Tokuhiro Matsuno, http://64p.org/ <tokuhirom@gmail.com>

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the “Software”), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in
	all copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
	THE SOFTWARE.

## Thanks to

This module was ported from [@dankogai's Regexp::Trie](https://metacpan.org/pod/Regexp::Trie). Thanks to dankogai++.
