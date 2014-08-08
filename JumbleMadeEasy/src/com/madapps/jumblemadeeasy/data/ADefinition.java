package com.madapps.jumblemadeeasy.data;

public class ADefinition {
	private String word;
	private Dictionary dict;
	private String wordDefinition;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Dictionary getDict() {
		if (null == this.dict) {
			this.dict = new Dictionary();
		}
		return this.dict;
	}

	public void setDict(Dictionary dict) {
		this.dict = dict;
	}

	public String getWordDefinition() {
		return wordDefinition;
	}

	public void setWordDefinition(String wordDefinition) {
		this.wordDefinition = wordDefinition;
	}

	@Override
    public String toString() {
        return "ADefinition ["
            + (word != null ? "Word=" + word + ", " : "")
            + (dict != null ? "Dictionary=" + dict +", " : "")
            + (wordDefinition != null ? "WordDefinition=" + wordDefinition : "") + "]";
    }
}
