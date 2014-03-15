package no.uia.yannis11.skompis.dbtest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Subject implements Parcelable
{
	private long id;
	private String name;
	private String color;
	//private String image;
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getColor()
	{
		return color;
	}
	public void setColor(String color)
	{
		this.color = color;
	}
	
	/*public String getImage()
	{
		return image;
	}
	public void setImage(String image)
	{
		this.image = image;
	}*/
	
	@Override
	public String toString() { return name; }
	
	public Subject() {}
	
	public Subject(Parcel in)
	{
		id = in.readLong();
		name = in.readString();
		color = in.readString();
		//image = in.readString();
	}
	
	@Override
	public int describeContents()
	{
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeLong(id);
		dest.writeString(name);
		dest.writeString(color);
		//dest.writeString(image);
	}
	
	public static final Parcelable.Creator<Subject> CREATOR = new Parcelable.Creator<Subject>()
			{

				@Override
				public Subject createFromParcel(Parcel source)
				{
					return new Subject(source);
				}

				@Override
				public Subject[] newArray(int size)
				{
					return new Subject[size];
				}
				
			};
	{
	};
}
