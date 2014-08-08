package com.madapps.jumblemadeeasy;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.android.app.jumblemadeeasy.executor.TaskExecutor;
import com.madapps.jumblemadeeasy.data.WordDefinitions;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class JMEMainActivity extends ActionBarActivity {
	

	private EditText editText1;
	private TextView textView2;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jmemain);
		editText1 = (EditText) this.findViewById(R.id.editText1);
		textView2 = (TextView) this.findViewById(R.id.textView2);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jmemain, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void solveJumble(View viewObj) {

		// final Button buttonObj = (Button)viewObj;
		// buttonObj.setText(editText1.getText());
		String word = editText1.getText().toString();
		//Vector dictSerResp = null;
		//TaskExecutor.executeTask(word, "DefineInDict");
		//DictionaryService dictService = new DictionaryWebServiceImpl();
		String word1="";
		
	
		//new TaskExecutor("DefineInDict").execute(word, "DefineInDict") ;
		
		File f = new File("NewFile.xml");
	
		FileReader rr;
		try {
			rr = new FileReader(f);
		
		
		XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory
				.newInstance();
		XmlPullParser myparser = xmlFactoryObject.newPullParser();
		myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);

			myparser.setInput(rr);
				WordDefinitions wordDef = new TaskExecutor("DefineInDict").parseDictXML(myparser);
				textView2.setText(wordDef+" is the word returned");
	
				
				
		
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	


		
		
	}
	
	
	protected void onPostExecute(String results) {
		if (results!=null) {
			textView2.setText(results+" is the word returned");

		}
	}
	
}
