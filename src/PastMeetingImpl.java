import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;


public class PastMeetingImpl extends MeetingImpl implements PastMeeting, Serializable{

private String notes;
	
	public PastMeetingImpl(Set<Contact> contacts, Calendar date, String notes){
		super(contacts,date);
		this.notes = notes;
	}
	
	public PastMeetingImpl(Set<Contact> contacts, Calendar date, String notes, int Id){
		super(contacts,date,Id);
		this.notes = notes;
		
	}

	
	public String getNotes(){
		return notes;
	}
	
	
	
	}
	
