import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Implementation of ContactManager
 * @author geoff_000
 *
 */
public class ContactManagerImpl implements ContactManager {
	
	private ArrayList<Contact> contactList;
	private Set<Contact> contactSet;
	private ArrayList<Meeting> futureMeetingList;
	private ArrayList<PastMeeting> pastMeetingList;
	Calendar cal;
	
	 
	
	
	public ContactManagerImpl(){
		contactList = new ArrayList<Contact>();
		contactSet = new HashSet<Contact>();
		futureMeetingList = new ArrayList<Meeting>();
		pastMeetingList = new ArrayList<PastMeeting>();	
	}
	
	public int addFutureMeeting(Set<Contact> contacts, Calendar date){
		Date meetingDate = date.getTime();
		Calendar calNow = Calendar.getInstance();
		Date now = calNow.getTime();
		if (meetingDate.before(now)) {
			throw new IllegalArgumentException("Error: Date is in the past");
		}
		FutureMeeting fm = new FutureMeetingImpl(contacts,date);
		System.out.println("<Meeting ID: " + fm.getId() + ">");
		futureMeetingList.add(fm);
		return fm.getId();	
	}
	
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text){
		PastMeeting pastMeeting = new PastMeetingImpl(contacts,date,text);
		System.out.println("<Meeting ID: " + pastMeeting.getId() + ">");
		pastMeetingList.add(pastMeeting);
		if (date == null || contacts == null || text == null) {
			throw new NullPointerException("parameter(s) missings");
		}
	}
	
	public void addMeetingNotes(int id, String text){
		for (int i = 0; i < pastMeetingList.size(); i++){
			if (pastMeetingList.get(i).getId() == id){
				
			}
		}
		
	}

	public PastMeeting getPastMeeting(int id){
		for (int i = 0; i < pastMeetingList.size(); i++) {
			if (pastMeetingList.get(i).getId() == id){
				return pastMeetingList.get(i);
			} else {
			} 
		} return null;
	}
	
	public FutureMeeting getFutureMeeting(int id){
		boolean found = false;
		for (int i = 0; i < futureMeetingList.size(); i++){
			if (futureMeetingList.get(i).getId() == id){
					Calendar cal = futureMeetingList.get(i).getDate();
					Date meetingDate = cal.getTime();
					Calendar calNow = Calendar.getInstance();
					Date now = calNow.getTime();
					if (meetingDate.before(now)){
						throw new IllegalArgumentException("Date is in the past");
					}
				
				System.out.println("found it!");
				found = true;
				System.out.println("Date: " + futureMeetingList.get(i).getDate());
				System.out.println("Attendees: " + futureMeetingList.get(i).getContacts());
				Meeting meeting = futureMeetingList.get(i);
				FutureMeeting futureMeeting  = (FutureMeeting)meeting;
				return futureMeeting;
			}
		}
		if (!found) {
			System.out.println("meeting not found");
		}
		return null;
	}
	
	public Meeting getMeeting(int id){
		for (int i = 0; i < futureMeetingList.size(); i++){
			if (futureMeetingList.get(i).getId() == id){
				return futureMeetingList.get(i);
			} 
		}
			for (int j = 0; j < pastMeetingList.size(); j++){
			 if (pastMeetingList.get(j).getId() == id){
				return pastMeetingList.get(j);
			} 
			}
			return null;	
	}
	
	public List<Meeting> getFutureMeetingList(Contact contact){
		List<Meeting> contactMeeting = new ArrayList<Meeting>();
		for (int i = 0; i < futureMeetingList.size(); i++){
				if (futureMeetingList.get(i).getContacts().contains(contact)){
					contactMeeting.add(futureMeetingList.get(i));
					System.out.println("found contact at i= " + i);
				} else { System.out.println("else case, i = " + i);
				i++;
				
			}
		}
		System.out.println("contactMeetingList size" + contactMeeting.size());	
		return contactMeeting;
	}
	/**
	 * Test to get size of fmlist--remove
	 * @return
	 */
	public int getFutureMeetingListSize(){
		return futureMeetingList.size();
	}
	
	/**
	 * test to get size
	 */
	public int getContactSetSize(){
		return contactSet.size();
	}
	
	public List<Meeting> getFutureMeetingList(Calendar date){
		return null;
	}
	
	public List<PastMeeting> getPastMeetingList(Contact contact){
		return null;
	}
	
	public void addNewContact(String name, String notes){
		Contact c = new ContactImpl(name,notes);
		contactList.add(c);
		System.out.println("Added: " + c.getName() + "  <ID: " + c.getId()+ ">");
	}
	
	public Set<Contact> getContacts(int... ids){
		contactSet.clear();
		for(int i = 0; i < ids.length; i++){
			for (int x = 0; x < contactList.size();x++){
				if(contactList.get(x).getId() == (ids[i])){
					contactSet.add(contactList.get(x));
				}
			}
		}
		Iterator<Contact> iterator = contactSet.iterator(); 
		while(iterator.hasNext()){
			Contact element = (Contact) iterator.next();
			System.out.println(element.getName() + " <ID: " + element.getId()+">");
		}
		return contactSet;
	}
		
	public Set<Contact> getContacts(String name){
		contactSet.clear();
		boolean found = false;
		for(int i = 0; i < contactList.size(); i++){
			if(contactList.get(i).getName().equals(name)){
				contactSet.add(contactList.get(i));
				found = true;
			}
		}
		if (!found){
			System.out.println("Contact not found");
		}
		return contactSet;
	}
		
	public void flush(){
		
	}
	
	
	
		

	
}
