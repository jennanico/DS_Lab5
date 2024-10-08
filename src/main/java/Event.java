
public class Event {

	public String event;
	public int position;
	public boolean deletion;
	
	public Event(String action, int pos, boolean del)
	{
		this.event = action;
		this.position = pos;
		this.deletion = del;
		
	}
	
}
