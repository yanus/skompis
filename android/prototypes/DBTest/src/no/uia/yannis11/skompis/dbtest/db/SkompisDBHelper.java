package no.uia.yannis11.skompis.dbtest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SkompisDBHelper extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME = "skompis.db";
	private static final int DATABASE_VERSION = 1;
	
	public SkompisDBHelper(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database)
	{
		SubjectTable.onCreate(database);
		TopicTable.onCreate(database);
		VideoTable.onCreate(database);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
	{
		SubjectTable.onUpgrade(database, oldVersion, newVersion);
		TopicTable.onUpgrade(database, oldVersion, newVersion);
		VideoTable.onUpgrade(database, oldVersion, newVersion);
	}
}
