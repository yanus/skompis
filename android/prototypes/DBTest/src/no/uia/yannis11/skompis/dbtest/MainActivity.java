package no.uia.yannis11.skompis.dbtest;

import java.util.List;

import no.uia.yannis11.skompis.dbtest.db.SkompisDataSource;
import no.uia.yannis11.skompis.dbtest.model.Subject;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity
{
	private SkompisDataSource datasource;
	
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listView = (ListView)findViewById(R.id.list);
		//listView.setOnClickListener(this);
		
		datasource = new SkompisDataSource(this);
		datasource.open();
		
		// TODO: implement a Loader to load data asynchronously
		List<Subject> values = datasource.getAllSubjects();
		ArrayAdapter<Subject> adapter = new ArrayAdapter<Subject>(this, android.R.layout.simple_list_item_1, values);
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
	
}
