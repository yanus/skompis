package no.uia.yannis11.skompis.dbtest.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TopicTable
{
	public static final String TABLE_TOPIC = "tema";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "navn";
	
	public static final String TABLE_SUBJECT_TOPIC = "fag_tema";
	public static final String COLUMN_FK_SUBJECT = "fag_id";
	public static final String COLUMN_FK_TOPIC = "tema_id";
	
	private static final String TOPIC_TABLE_CREATE = "create table "
			+ TABLE_TOPIC + "("
			+ COLUMN_ID + " integer primary key, "
			+ COLUMN_NAME + " text not null"
			+ ");";
	
	private static final String SUBJECT_TOPIC_TABLE_CREATE = "create table "
			+ TABLE_SUBJECT_TOPIC + "("
			+ COLUMN_FK_SUBJECT + " integer not null, "
			+ COLUMN_FK_TOPIC + " integer not null, "
			+ "primary key (" + COLUMN_FK_SUBJECT + ", " + COLUMN_FK_TOPIC + ")"
			+ ");";
	
	public static void onCreate(SQLiteDatabase database)
	{
		database.execSQL(TOPIC_TABLE_CREATE);
		database.execSQL(SUBJECT_TOPIC_TABLE_CREATE);
	}
	
	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
	{
		Log.w(SubjectTable.class.getName(), "Upgrading table '" + TABLE_TOPIC + "' from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_TOPIC);
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT_TOPIC);
		onCreate(database);
	}
}
