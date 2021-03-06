package no.uia.yannis11.skompis.dbtest.db;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import no.uia.yannis11.skompis.dbtest.R;
import no.uia.yannis11.skompis.dbtest.model.Subject;
import no.uia.yannis11.skompis.dbtest.model.Topic;
import no.uia.yannis11.skompis.dbtest.model.Video;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
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
			subject.setColor(cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.COLUMN_COLOR)));
			//subject.setImage(cursor.getString(cursor.getColumnIndexOrThrow(SubjectTable.COLUMN_IMAGE)));
			subjects.add(subject);
		}
		cursor.close();
		return subjects;
	}
	
	public List<Topic> getTopicsForSubject(Subject subject)
	{
		List<Topic> topics = new ArrayList<Topic>();
		
		// TODO: Replace hardcoded table/column names
		Cursor cursor = database.rawQuery("SELECT _id, navn, vanskelighetsgrad FROM tema INNER JOIN fag_tema ON tema._id = fag_tema.tema_id WHERE fag_tema.fag_id = " + subject.getId() + ";", null);
		
		while (cursor.moveToNext())
		{
			Topic topic = new Topic();
			topic.setId(cursor.getLong(cursor.getColumnIndexOrThrow(TopicTable.COLUMN_ID)));
			topic.setName(cursor.getString(cursor.getColumnIndexOrThrow(TopicTable.COLUMN_NAME)));
			topic.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(TopicTable.COLUMN_LEVEL)));
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
	
	public void insertSubject(Subject subject)
	{
		ContentValues values = new ContentValues();
		values.put(SubjectTable.COLUMN_ID, subject.getId());
		values.put(SubjectTable.COLUMN_NAME, subject.getName());
		values.put(SubjectTable.COLUMN_COLOR, subject.getColor());
		//values.put(SubjectTable.COLUMN_IMAGE, subject.getImage());
		database.insert(SubjectTable.TABLE_SUBJECT, null, values);
	}
	
	public void insertTopic(Topic topic, List<Subject> subjects)
	{
		ContentValues values = new ContentValues();
		values.put(TopicTable.COLUMN_ID, topic.getId());
		values.put(TopicTable.COLUMN_NAME, topic.getName());
		values.put(TopicTable.COLUMN_LEVEL, topic.getLevel());
		database.insert(TopicTable.TABLE_TOPIC, null, values);
		
		for (Subject subject : subjects)
		{
			values.clear();
			values.put(TopicTable.COLUMN_FK_SUBJECT, subject.getId());
			values.put(TopicTable.COLUMN_FK_TOPIC, topic.getId());
			database.insert(TopicTable.TABLE_SUBJECT_TOPIC, null, values);
		}
	}
	
	public void insertVideo(Video video)
	{
		ContentValues values = new ContentValues();
		values.put(VideoTable.COLUMN_ID, video.getId());
		values.put(VideoTable.COLUMN_NAME, video.getName());
		values.put(VideoTable.COLUMN_FK_TOPIC, video.getTopic().getId());
		database.insert(VideoTable.TABLE_VIDEO, null, values);
	}
	
	public void loadDataFromXML(Context context)
	{
		database.beginTransaction();
		try
		{
			XMLParser parser = new XMLParser(database);
			parser.parse(context.getResources().openRawResource(R.raw.skompis_fag));
			parser.parse(context.getResources().openRawResource(R.raw.skompis_tema));
			parser.parse(context.getResources().openRawResource(R.raw.skompis_fag_har_tema));
			database.setTransactionSuccessful();
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		catch (XmlPullParserException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			database.endTransaction();
		}
	}
}
