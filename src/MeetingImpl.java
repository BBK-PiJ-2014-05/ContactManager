import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;


public class MeetingImpl implements Meeting, Comparable<Meeting>, Serializable {

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
	
	
	/**
	 * used for testing purposes, otherwise I found the id's difficult to track as test cases were invoked
	 */
	
	public static void resetId(){
		nextId = 100;
	}
	
	/**
	 * display variables in a user-friendly way
	 */
	
	@Override
	public String toString(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Date date = meetingDate.getTime();
		String strDate = sdf.format(date);
		
		String result = "Meeting Id: " + meetingId + " Meeting Date: " + strDate + " Meeting contacts " + contactSet; 
		return result;
	}
	
	/**
	 * used to sort meeting objects according to one of the object's fields i.e. the date field.
	 */
	
	@Override
	  public int compareTo(Meeting m) {
	    return getDate().compareTo(m.getDate());
	  }
	
	}
	
	
	

