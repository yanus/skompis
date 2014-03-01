package no.uia.yannis11.skompispt;

import no.uia.yannis11.skompispt.helpers.ViewUtils;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TopicAdapter extends BaseAdapter
{
	static final String[] topics = { "Topic 1", "Topic 2", "Topic 3", "Topic 4", "Topic 5", "Topic 6", "Topic 7", "Topic 8", "Topic 9" };
	//static final int[] icons = { R.drawable.subject1, R.drawable.subject2, R.drawable.subject3, R.drawable.subject4, R.drawable.subject5 };
	
	private final Context context;
	private int color = Color.argb(255, 255, 255, 255);
	
	public TopicAdapter(Context context)
	{
		super();
		this.context = context;
	}
	
	public void setColor(int color)
	{
		this.color = color;
	}
	
	@Override
	public int getCount()
	{
		return 9;
	}
	
	@Override
	public Object getItem(int position)
	{
		return topics[position];
	}
	
	@Override
	public long getItemId(int position)
	{
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.item_topic, parent, false);
		
		GradientDrawable gradient = new GradientDrawable(Orientation.LEFT_RIGHT, new int[] {color, Color.argb(223, 255, 255, 255)});
		ViewUtils.setBackground(rowView, gradient);
		
		TextView textView = (TextView) rowView.findViewById(R.id.rowText);
		textView.setText(topics[position]);
		return rowView;
	}
	
}
