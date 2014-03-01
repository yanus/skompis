package no.uia.yannis11.skompispt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class SelectSubjectActivity extends Activity implements OnItemClickListener
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_subject);

		// Create handle to the grid view and assign the activity as listener
		GridView grid = (GridView) findViewById(R.id.gridView);
		grid.setOnItemClickListener(this);
		
		// Create adapter and assign to list
		final SubjectAdapter adapter = new SubjectAdapter(this);
		grid.setAdapter(adapter);
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_subject, menu);
		return true;
	}

	public void onItemClick(AdapterView<?> listView, View view, int position, long id)
	{
		Intent intent = new Intent(this, SelectVideoActivity.class);
		intent.putExtra("subject", (String) listView.getItemAtPosition(position));
		intent.putExtra("icon", SubjectAdapter.getIcon(position));
		intent.putExtra("color", SubjectAdapter.getColor(position));
		startActivity(intent);
	}

}
