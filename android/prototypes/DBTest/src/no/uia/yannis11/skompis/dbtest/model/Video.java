package no.uia.yannis11.skompis.dbtest.model;

public class Video
{
	private long id;
	private String name;

	private Topic topic;
	
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
	
	public Topic getTopic()
	{
		return topic;
	}
	public void setTopic(Topic topic)
	{
		this.topic = topic;
	}
	
	@Override
	public String toString() { return name; }
}
