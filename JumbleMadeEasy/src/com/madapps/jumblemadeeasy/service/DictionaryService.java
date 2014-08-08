package com.madapps.jumblemadeeasy.service;


public interface DictionaryService{

public static final String DICT_ID="gcide";
public static final String WEB_SERVICE_URL = "http://services.aonaware.com/DictService/DictService.asmx/";
public static final String DEFINE_IN_DICT = "DefineInDict";

public Object getDictWords(String word);

}
