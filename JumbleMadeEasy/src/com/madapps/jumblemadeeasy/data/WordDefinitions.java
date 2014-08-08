package com.madapps.jumblemadeeasy.data;

import java.util.ArrayList;
import java.util.List;


public class WordDefinitions {
	
	private String word;
	private List<ADefinition> aDef;
	
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public List<ADefinition> getaDef() {
		if(null == aDef){
			aDef = new ArrayList<ADefinition>();
		}
		return aDef;
	}

	public void setaDef(List<ADefinition> aDef) {
		this.aDef = aDef;
	}


	@Override
    public String toString() {
        return "ADefinition ["
            + (this.word != null ? "Word=" + this.word + ", " : "")
            + (this.aDef != null ? "Definitions=" + this.aDef : "");
    }

}
