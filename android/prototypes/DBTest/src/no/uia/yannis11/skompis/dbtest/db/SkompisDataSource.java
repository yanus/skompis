package no.uia.yannis11.skompis.dbtest.db;

import java.util.ArrayList;
import java.util.List;

import no.uia.yannis11.skompis.dbtest.model.Subject;
import no.uia.yannis11.skompis.dbtest.model.Topic;
import no.uia.yannis11.skompis.dbtest.model.Video;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SkompisDataSource
{
	public static final String LOGTAG = SkompisDataSource.class.getName();
	
	private SQLiteDatabase database;
	private SkompisDBHelper dbHelper;
	
	public SkompisDataSource(Context context)
	{
		dbHelper = new SkompisDBHelper(context);
	}
	
	public void open() throws SQLException
	{
		Log.i(LOGTAG, "Database opened");
		database = dbHelper.getWritableDatabase();
	}
	
	public void close()
	{
		Log.i(LOGTAG, "Database closed");
		dbHelper.close();
	}
	
	public List<Subject> getAllSubjects()
	{
		List<Subject> subjects = new ArrayList<Subject>();
		
		Cursor cursor = database.query(SubjectTable.TABLE_SUBJECT, null, null, null, null, null, null);
		
		while (cursor.moveToNext())
		{
			Subject subject = new Subject();
			subject.setId(cursor.getLong(cursor.getColumnIndexOrThrow(SubjectTable.COLUMN_ID)));
			subject.setName(cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.COLUMN_NAME)));
			subject.setImage(cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.COLUMN_IMAGE)));
			subjects.add(subject);
		}
		cursor.close();
		return subjects;
	}
	
	public List<Topic> getTopicsForSubject(Subject subject)
	{
		List<Topic> topics = new ArrayList<Topic>();
		
		// TODO: Replace hardcoded table/column names
		Cursor cursor = database.rawQuery("SELECT _id, navn FROM tema INNER JOIN fag_tema ON tema._id = fag_tema.tema_id WHERE fag.tema.fag_id = " + subject.getId() + ";", null);
		
		while (cursor.moveToNext())
		{
			Topic topic = new Topic();
			topic.setId(cursor.getLong(cursor.getColumnIndexOrThrow(TopicTable.COLUMN_ID)));
			topic.setName(cursor.getString(cursor.getColumnIndexOrThrow(TopicTable.COLUMN_NAME)));
			topics.add(topic);
		}
		cursor.close();
		return topics;
	}
	
	public List<Video> getVideosForTopic(Topic topic)
	{
		List<Video> videos = new ArrayList<Video>();
		
		Cursor cursor = database.query(VideoTable.TABLE_VIDEO, new String[]{ VideoTable.COLUMN_ID, VideoTable.COLUMN_NAME }, VideoTable.COLUMN_FK_TOPIC + " = " + topic.getId(), null, null, null, null);

		while (cursor.moveToNext())
		{
			Video video = new Video();
			video.setId(cursor.getLong(cursor.getColumnIndexOrThrow(VideoTable.COLUMN_ID)));
			video.setName(cursor.getString(cursor.getColumnIndexOrThrow(VideoTable.COLUMN_NAME)));
			video.setTopic(topic);
			videos.add(video);
		}
		cursor.close();
		return videos;
	}
}
