package no.uia.yannis11.skompis.dbtest.db;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.Xml;

public class XMLParser
{
	private static final String ns = null; // we're not using namespaces
	
	private final SQLiteDatabase database;
	
	public XMLParser(SQLiteDatabase db)
	{
		database = db;
	}
	
	public void parse(InputStream in) throws XmlPullParserException, IOException
	{
		try
		{
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(in, null);
			parser.nextTag();
			readEntries(parser);
		}
		finally
		{
			in.close();
		}
	}
	
	private void readEntries(XmlPullParser parser) throws XmlPullParserException, IOException
	{
		// Read root-tag, we don't need to hold on to this, as this is only the plural of the table name
		parser.require(XmlPullParser.START_TAG, ns, null);
		while (parser.next() != XmlPullParser.END_TAG)
		{
			if (parser.getEventType() != XmlPullParser.START_TAG)
				continue;
			// Get name of the start tag, which is the table name
			String name = parser.getName();
			readEntry(parser, name);
		}
	}
	
	private void readEntry(XmlPullParser parser, String tableName) throws XmlPullParserException, IOException
	{
		ContentValues values = new ContentValues();
		// Loop until the entry's end tag is encountered
		while (parser.next() != XmlPullParser.END_TAG)
		{
			if (parser.getEventType() != XmlPullParser.START_TAG)
				continue;
			String name = parser.getName();
			String value = readText(parser);
			values.put(name, value);
		}
		Log.i(XMLParser.class.getName(), "Table: " + tableName);
		Log.i(XMLParser.class.getName(), "Values: " + values);
		database.insert(tableName, null, values);
	}
	
	private String readText(XmlPullParser parser) throws XmlPullParserException, IOException
	{
		String result = "";
		if (parser.next() == XmlPullParser.TEXT)
		{
			result = parser.getText();
			parser.nextTag();
		}
		return result;
	}
}
