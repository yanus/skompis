package no.uia.yannis11.skompispt;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class SelectSubjectActivity extends ListActivity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_subject);
		
		// Create adapter and assign to list
		final SubjectAdapter adapter = new SubjectAdapter(this);
		setListAdapter(adapter);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_subject, menu);
		return true;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id)
	{
		Intent intent = new Intent(this, SelectVideoActivity.class);
		intent.putExtra("subject", (String) listView.getItemAtPosition(position));
		intent.putExtra("icon", SubjectAdapter.getIcon(position));
		startActivity(intent);
	}
	
}
