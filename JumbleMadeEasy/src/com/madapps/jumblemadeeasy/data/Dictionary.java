package com.madapps.jumblemadeeasy.data;


public class Dictionary {
	String dictID;
	String dict_Name;

	public String getDictID() {
		return dictID;
	}

	public void setDictID(String dictID) {
		this.dictID = dictID;
	}

	public String getDict_Name() {
		return dict_Name;
	}
	public void setDict_Name(String dict_Name) {
		this.dict_Name = dict_Name;
	}
	
	@Override
    public String toString() {
        return "Dictionary ["
            + (dictID != null ? "Id=" + dictID + ", " : "")
            + (dict_Name != null ? "Name=" + dict_Name : "") + "]";
    }
}
