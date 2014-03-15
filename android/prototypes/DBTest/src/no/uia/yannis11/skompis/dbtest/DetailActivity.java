package no.uia.yannis11.skompis.dbtest;

import java.util.List;

import no.uia.yannis11.skompis.dbtest.db.SkompisDataSource;
import no.uia.yannis11.skompis.dbtest.model.Subject;
import no.uia.yannis11.skompis.dbtest.model.Topic;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DetailActivity extends Activity
{
	private SkompisDataSource datasource;
	List<Topic> topics;
	Subject subject;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		Bundle b = getIntent().getExtras();
		subject = b.getParcelable(".model.Subject");
		
		setTitle(subject.getName());
		
		View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
		rootView.setBackgroundColor(Color.parseColor(subject.getColor()));
		
		ListView listView = (ListView)findViewById(R.id.list);
		//.setOnItemClickListener(this);
		
		datasource = new SkompisDataSource(this);
		datasource.open();
		
		// TODO: implement a Loader to load data asynchronously
		topics = datasource.getTopicsForSubject(subject);
		ArrayAdapter<Topic> adapter = new ArrayAdapter<Topic>(this, android.R.layout.simple_list_item_1, topics);
		listView.setAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
