package no.uia.yannis11.skompis.dbtest.model;

public class Subject
{
	private long id;
	private String name;
	private String image;
	
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
	
	public String getImage()
	{
		return image;
	}
	public void setImage(String image)
	{
		this.image = image;
	}
	
	@Override
	public String toString() { return name; }
}
