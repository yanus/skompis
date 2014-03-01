package no.uia.yannis11.skompispt.helpers;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

public class ViewUtils
{
	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	static public void setBackground(View view, Drawable drawable)
	{
		if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN)
			view.setBackgroundDrawable(drawable);
		else
			view.setBackground(drawable);
	}
	
	static public void setBackgroundResource(View view, Context context, int id, TileMode tileMode)
	{
		BitmapDrawable background = new BitmapDrawable(context.getResources(), BitmapFactory.decodeResource(context.getResources(), id));
		background.setTileModeXY(tileMode, tileMode);
		setBackground(view, background);
	}
	
	static public void setBackgroundResource(View view, Context context, int id)
	{
		setBackgroundResource(view, context, id, TileMode.CLAMP);
	}
}
