/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.geso.regexp_trie;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;

class CharTrie extends TreeMap<String, CharTrie> {

	final static Pattern nonSpecialCharsPattern = Pattern.compile("\\A[A-Za-z0-9_]+\\z");

	String regexp() {
		if (this.containsKey("") && this.size() == 1) {
			// terminator.
			return null;
		}

		List<String> alt = new ArrayList<>();
		List<String> cc = new ArrayList<>(); // character class?
		boolean q = false;
		for (String c : this.keySet()) {
			String quoted = softQuote(c);
			if (this.get(c) != null) {
				String recurse = this.get(c).regexp();
				if (recurse != null) {
					alt.add(quoted + recurse);
				} else {
					cc.add(quoted);
				}
			} else {
				q = true;
			}
		}
		boolean cconly = alt.isEmpty();
		if (cc.size() > 0) {
			if (cc.size() == 1) {
				alt.add(cc.get(0));
			} else {
				StringBuilder buf = new StringBuilder();
				buf.append("[");
				for (String it : cc) {
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
			StringBuilder buf = new StringBuilder();
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
}

/**
 *
 * @author Tokuhiro Matsuno <tokuhirom@gmail.com>
 */
public class RegexpTrie {

	final CharTrie trie = new CharTrie();

	public void add(String str) {
		CharTrie ref = trie;
		for (int i = 0; i < str.length(); ++i) {
			String c = "" + str.charAt(i);
			if (!ref.containsKey(c)) {
				ref.put(c, new CharTrie());
			}
			ref = ref.get(c);
		}
		ref.put("", null); // [ "": null ] as terminator
	}

	public String regexp() {
		return trie.regexp();
	}
}
