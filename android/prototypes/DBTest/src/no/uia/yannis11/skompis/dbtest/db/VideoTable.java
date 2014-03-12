package no.uia.yannis11.skompis.dbtest.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class VideoTable
{
	public static final String TABLE_VIDEO = "video";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "navn";
	public static final String COLUMN_FK_TOPIC = "tema_id";
	
	private static final String VIDEO_TABLE_CREATE = "create table "
			+ TABLE_VIDEO + "("
			+ COLUMN_ID + " integer primary key, "
			+ COLUMN_NAME + " text not null, "
			+ COLUMN_FK_TOPIC + " integer not null"
			+ ");";
	
	public static void onCreate(SQLiteDatabase database)
	{
		database.execSQL(VIDEO_TABLE_CREATE);
	}
	
	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
	{
		Log.w(SubjectTable.class.getName(), "Upgrading table '" + TABLE_VIDEO + "' from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_VIDEO);
		onCreate(database);
	}
}
