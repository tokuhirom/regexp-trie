/*
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
 */
package me.geso.regexp_trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Pattern;

class CharTrie {

	private final static Pattern nonSpecialCharsPattern = Pattern
			.compile("\\A(?:[^\\\\x00-\\\\x7F]|[A-Za-z0-9_])+\\z");
	private final Map<String, CharTrie> data = new TreeMap<>();

	String regexp() {
		if (this.data.containsKey("") && this.data.size() == 1) {
			// terminator.
			return null;
		}

		final List<String> alt = new ArrayList<>();
		final List<String> cc = new ArrayList<>(); // character class?
		boolean q = false;
		for (final Entry<String, CharTrie> entry : this.data.entrySet()) {
			final String quoted = this.softQuote(entry.getKey());
			if (entry.getValue() != null) {
				final String recurse = entry.getValue().regexp();
				if (recurse != null) {
					alt.add(quoted + recurse);
				} else {
					cc.add(quoted);
				}
			} else {
				q = true;
			}
		}
		final boolean cconly = alt.isEmpty();
		if (cc.size() > 0) {
			if (cc.size() == 1) {
				alt.add(cc.get(0));
			} else {
				final StringBuilder buf = new StringBuilder();
				buf.append("[");
				for (final String it : cc) {
					buf.append(it);
				}
				buf.append("]");
				alt.add(buf.toString());
			}
		}
		String result;
		if (alt.size() == 1) {
			result = alt.get(0);
		} else {
			final StringBuilder buf = new StringBuilder();
			buf.append("(?:");
			for (int i = 0; i < alt.size(); ++i) {
				buf.append(alt.get(i));
				if (i != alt.size() - 1) {
					buf.append("|");
				}
			}
			buf.append(")");
			result = buf.toString();
		}
		if (q) {
			if (cconly) {
				return result + "?";
			} else {
				return "(?:" + result + ")?";
			}
		}
		return result;
	}

	private String softQuote(String s) {
		if (nonSpecialCharsPattern.matcher(s).matches()) {
			// Do not quote basic alphanumeric characters for redability.
			return s;
		} else {
			return Pattern.quote(s);
		}
	}

	public boolean containsKey(String key) {
		return this.data.containsKey(key);
	}

	public void put(String c, CharTrie charTrie) {
		this.data.put(c, charTrie);
	}

	public CharTrie get(String c) {
		return this.data.get(c);
	}
}

public class RegexpTrie {

	private final CharTrie trie = new CharTrie();

	public void add(String str) {
		CharTrie ref = this.trie;
		for (int i = 0; i < str.length(); ++i) {
			final String c = "" + str.charAt(i);
			if (!ref.containsKey(c)) {
				ref.put(c, new CharTrie());
			}
			ref = ref.get(c);
		}
		ref.put("", null); // [ "": null ] as terminator
	}

	public String regexp() {
		return this.trie.regexp();
	}
}
