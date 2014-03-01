package no.uia.yannis11.skompispt;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SubjectAdapter extends BaseAdapter
{
	static final String[] subjects = { "Mathematics", "Physics", "Biology", "Chemistry", "History" };
	static final int[] icons = { R.drawable.subject1, R.drawable.subject2, R.drawable.subject3, R.drawable.subject4, R.drawable.subject5 };
	static final int[] colors = { Color.rgb(102, 153, 204), Color.rgb(255, 102, 102), Color.rgb(204, 255, 153), Color.rgb(204, 153, 204), Color.rgb(255, 255, 153) };

	private final Context context;
	
	public SubjectAdapter(Context context)
	{
		super();
		this.context = context;
	}
	
	@Override
	public int getCount()
	{
		return 5;
	}
	
	@Override
	public Object getItem(int position)
	{
		return subjects[position];
	}
	
	@Override
	public long getItemId(int position)
	{
		return position;
	}
	
	public static int getIcon(int position)
	{
		return icons[position];
	}
	
	public static int getColor(int position)
	{
		return colors[position];
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.item_subject, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.rowText);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.rowIcon);
		textView.setText(subjects[position]);
		imageView.setImageResource(getIcon(position));
		return rowView;
	}
	
}
