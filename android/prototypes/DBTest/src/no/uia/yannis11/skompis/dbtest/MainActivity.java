package no.uia.yannis11.skompis.dbtest;

import java.util.List;

import no.uia.yannis11.skompis.dbtest.db.SkompisDataSource;
import no.uia.yannis11.skompis.dbtest.model.Subject;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements OnItemClickListener
{
	private SkompisDataSource datasource;
	
	List<Subject> subjects;
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listView = (ListView)findViewById(R.id.list);
		listView.setOnItemClickListener(this);
		
		datasource = new SkompisDataSource(this);
		datasource.open();
		
		// TODO: implement a Loader to load data asynchronously
		subjects = datasource.getAllSubjects();
		if (subjects.size() == 0)
		{
			datasource.loadDataFromXML(this);
			subjects = datasource.getAllSubjects();
		}
		
		ArrayAdapter<Subject> adapter = new ArrayAdapter<Subject>(this, android.R.layout.simple_list_item_1, subjects);
		listView.setAdapter(adapter);
	}
	
	@Override
	protected void onPause()
	{
		datasource.close();
		super.onPause();
	}

	@Override
	protected void onResume()
	{
		datasource.open();
		super.onResume();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> listView, View view, int position, long id)
	{
		Intent intent = new Intent(this, DetailActivity.class);
		intent.putExtra(".model.Subject", (Subject) listView.getItemAtPosition(position));
		startActivity(intent);
	}
	
}
