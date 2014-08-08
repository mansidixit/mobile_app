package com.android.app.jumblemadeeasy.executor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.xmlpull.v1.*;

import com.madapps.jumblemadeeasy.data.ADefinition;
import com.madapps.jumblemadeeasy.data.Dictionary;
import com.madapps.jumblemadeeasy.data.WordDefinitions;

import android.os.AsyncTask;

public class TaskExecutor extends AsyncTask<String, Void, Object> {

	private static final String WEB_SERVICE_URL = "http://services.aonaware.com/DictService/DictService.asmx/";
	public static final String DEFINE_IN_DICT = "DefineInDict";
	public static final String DICT_ID = "gcide";
	private HttpClient dictHttpClient;
	private HttpContext dictLocalContext;
	private HttpGet httpGet;
	private HttpResponse dictServiceResp;
	private HttpEntity respEntity;
	private InputStream xmlInputStream;

	// static TaskExecutor taskExecutor = null;

	public TaskExecutor(String taskName) {
		if (taskName == "DefineInDict") {
			dictHttpClient = new DefaultHttpClient();
			dictLocalContext = new BasicHttpContext();
			// dictService = new DictionaryWebServiceImpl();
		}
	}

	@Override
	protected Object doInBackground(String... params) {
		String dictWord = "";
		if (params[1] == "DefineInDict") {
			dictWord = (String) getDictWords(params[0]);
		}
		return dictWord;
	}

	private Object getDictWords(String word) {
		httpGet = new HttpGet(WEB_SERVICE_URL + DEFINE_IN_DICT + "?dictId="
				+ DICT_ID + "&word=" + word);
		String respWord = "";
		try {
			dictServiceResp = dictHttpClient.execute(httpGet, dictLocalContext);
			respEntity = dictServiceResp.getEntity();
			xmlInputStream = respEntity.getContent();
			
			
				
			XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory
					.newInstance();
			XmlPullParser myparser = xmlFactoryObject.newPullParser();
			myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);

			myparser.setInput(xmlInputStream, null);

			WordDefinitions wordDef = parseDictXML(myparser);

			
			if (null != wordDef && null != wordDef.getaDef()) {
				if (!wordDef.getaDef().isEmpty()) {
					respWord = wordDef.getWord();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return respWord;
	}

	public WordDefinitions parseDictXML(XmlPullParser dictXMLParser)
			throws XmlPullParserException, IOException {
		WordDefinitions wordDefinitionObj = null;
		List<ADefinition> aDefList = null;
		ADefinition aDefObj = null;
		Dictionary dictObj = null;
		int eventType = dictXMLParser.getEventType();
		String tagName = "";
		while (eventType != XmlPullParser.END_DOCUMENT) {
			tagName = dictXMLParser.getName();
			switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					wordDefinitionObj = new WordDefinitions();
				break;
				case XmlPullParser.START_TAG:
					if (tagName.equalsIgnoreCase("Word")
							&& dictXMLParser.getDepth() == 1) {
						wordDefinitionObj.setWord(dictXMLParser.nextText());
					} else if (tagName.equalsIgnoreCase("Definition")) {
						aDefList = new ArrayList<ADefinition>();
						aDefObj = new ADefinition();
					} else if (tagName.equalsIgnoreCase("Word")) {
						if (null != aDefObj) {
							aDefObj.setWord(dictXMLParser.nextText());
						}
					} else if (tagName.equalsIgnoreCase("Dictionary")) {
						dictObj = new Dictionary();
					} else if (tagName.equalsIgnoreCase("Id")) {
						if (null != dictObj) {
							dictObj.setDictID(dictXMLParser.nextText());
						}
					} else if (tagName.equalsIgnoreCase("Name")) {
						if (null != dictObj) {
							dictObj.setDict_Name(dictXMLParser.nextText());
						}
					} else if (tagName.equalsIgnoreCase("WordDefinition")
							&& dictXMLParser.getDepth() > 1) {
						if (null != aDefObj) {
							aDefObj.setWordDefinition(dictXMLParser.nextText());
						}
					}
				break;
				case XmlPullParser.END_TAG:
					if (tagName.equalsIgnoreCase("Dictionary") && null != dictObj
							&& null != aDefObj) {
						aDefObj.setDict(dictObj);
					} else if (tagName.equalsIgnoreCase("Definition")
							&& null != aDefObj && null != aDefList) {
						aDefList.add(aDefObj);
					} else if (tagName.equalsIgnoreCase("WordDefinition")
							&& dictXMLParser.getDepth() == 1) {
						if (null != aDefList && null != wordDefinitionObj) {
							wordDefinitionObj.setaDef(aDefList);
						}
					}	
				break;
			}
			eventType = dictXMLParser.getEventType();
		}
		return wordDefinitionObj;
	}
		
}
