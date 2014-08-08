package com.madapps.jumblemadeeasy.service;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.stream.StreamSource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.madapps.jumblemadeeasy.data.WordDefinitions;


public class DictionaryWebServiceImpl implements DictionaryService {
	private HttpClient dictHttpClient;
	private HttpContext dictLocalContext;
	private HttpGet httpGet;
	private HttpResponse dictServiceResp;
	private HttpEntity respEntity;
	private InputStream xmlInputStream;

	@Override
	public Object getDictWords(String word){
		dictHttpClient = new DefaultHttpClient();
		dictLocalContext = new BasicHttpContext();
		httpGet = new HttpGet(WEB_SERVICE_URL + DEFINE_IN_DICT + "?dictId="
				+ DICT_ID + "&word=" + word);
		String respWord="";
		try {
			dictServiceResp = dictHttpClient.execute(httpGet, dictLocalContext);
			respEntity = dictServiceResp.getEntity();
			xmlInputStream = respEntity.getContent();
			//word = Utility.parseXML(xmlInputStream);
		//	XMLInputFactory xif = XMLInputFactory.newFactory();
	      //  xif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);
	        //StreamSource source = new StreamSource(xmlInputStream);
	        //XMLStreamReader xsr = xif.createXMLStreamReader(source);
	        
	        //JAXBContext jaxbContext = JAXBContext.newInstance(WordDefinitions.class);
			//Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			//WordDefinitions wordDef = (WordDefinitions)unmarshaller.unmarshal(xsr);
		
			//if(null!=wordDef && null!=wordDef.getaDef()){
				//if(!wordDef.getaDef().isEmpty()){
					//respWord = wordDef.getWord();
				//}
			//}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respWord;
	}

}
