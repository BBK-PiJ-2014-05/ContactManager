import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;


public class MeetingImpl implements Meeting, Comparable<Meeting> {

	private Calendar meetingDate = new GregorianCalendar();
	private Set<Contact> contactSet = new HashSet<Contact>();
	private int meetingId;
	
	private static int nextId = 100;
	
	
	public MeetingImpl(Set<Contact> contacts, Calendar date){
		this.contactSet = contacts;
		this.meetingDate = date;
		this.meetingId = generateId();
	}
	
	public MeetingImpl(Set<Contact> contacts, Calendar date, int Id){
		this.contactSet = contacts;
		this.meetingDate = date;
		this.meetingId = Id;
	}
	
	private int generateId(){
		return nextId++;
	}
	
	public int getId(){
		return meetingId;
	}
	
	public Set<Contact> getContacts(){		
		return contactSet;
	}
	
	public Calendar getDate(){
		return meetingDate;
		
	}
	
	public static void resetId(){
		nextId = 100;
	}
	
	@Override
	  public int compareTo(Meeting o) {
	    return getDate().compareTo(o.getDate());
	  }
	
	}
	
	
	

