package no.uia.yannis11.skompis.dbtest.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SubjectTable
{
	public static final String TABLE_SUBJECT = "fag";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "navn";
	public static final String COLUMN_IMAGE = "bilde";
	
	private static final String SUBJECT_TABLE_CREATE = "create table "
			+ TABLE_SUBJECT + "("
			+ COLUMN_ID + " integer primary key, "
			+ COLUMN_NAME + " text not null, "
			+ COLUMN_IMAGE + " text not null"
			+ ");";
	
	public static void onCreate(SQLiteDatabase database)
	{
		database.execSQL(SUBJECT_TABLE_CREATE);
	}
	
	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
	{
		Log.w(SubjectTable.class.getName(), "Upgrading table '" + TABLE_SUBJECT + "' from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT);
		onCreate(database);
	}
}
