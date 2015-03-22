import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/** test case
 * 
 * @author geoff_000
 *
 */

public class TestClass {

	public static void main(String[] args) {
		TestClass test = new TestClass();
		test.launch();
		}
	
	public void launch(){
		ContactManagerImpl cm = new ContactManagerImpl();
		addContacts(cm);
		//testAddFutureMeetings(cm);
		//testPastMeetings(cm);
		//cm.flush();
		//testGetContacts(cm);
		testGetFutureMeetingList(cm);
		//testGetFutureMeetingListDate(cm);
	}
	
	public void addContacts(ContactManagerImpl cm){
		cm.addNewContact("Geoff", "notes about Geoff1");
		cm.addNewContact("Dave", "notes about Dave");
		cm.addNewContact("Sharon", "notea bout Sharon");
		cm.addNewContact("Rachel", "notes about Rachel");
		cm.addNewContact("Geoff", "notes about Geoff2");
	}
	/**
	 * Add 4 future meetings
	 * @param cm
	 */
	
	public void testAddFutureMeetings(ContactManagerImpl cm){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MILLISECOND,30);
		Set<Contact> contacts = new HashSet<Contact>();
		contacts = cm.getContacts(0,1);
		cm.addFutureMeeting(contacts, cal);
		
		contacts.clear();
		contacts = cm.getContacts(3);
		cm.addFutureMeeting(contacts, cal);
		
		cal.add(Calendar.MILLISECOND,100);
		contacts.clear();
		contacts = cm.getContacts(2,3);
		cm.addFutureMeeting(contacts, cal);
		
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.MONTH,1);
		cm.addFutureMeeting(contacts, cal1);

	}
	
	public void testPastMeetings(ContactManagerImpl cm){
		Calendar cal = Calendar.getInstance();
		Set<Contact> contacts = new HashSet<Contact>();
		contacts = cm.getContacts(2,3);
		String notes = "initializationNotes";
		cm.addNewPastMeeting(contacts, cal, notes);
		cm.addMeetingNotes(100, "__________additionalNotes");
		Meeting m =  cm.getMeeting(100);
		//cm.addMeetingNotes(100, "additionalNotes");
		PastMeeting pm = (PastMeeting)m;
		System.out.println("notes:" + pm.getNotes());
		Set<Contact> newContacts = new HashSet<Contact>();
		newContacts = cm.getContacts(0,1,2,3);
		String moreNotes = "some notes for the new pastMeeting";
		cm.addNewPastMeeting(newContacts, cal, moreNotes);
		PastMeeting pm1 = cm.getPastMeeting(101);
		System.out.println(""+ pm1.getContacts());
		System.out.println(""+ pm.getContacts());
		System.out.println("" + pm1.getNotes());
		
	}
	
	public void testGetContacts(ContactManagerImpl cm){
		
		System.out.println("contacts name Geoff: " + cm.getContacts("Geoff"));
		Set<Contact> contactSet = new HashSet<Contact>();
		contactSet = cm.getContacts(2,4);
		System.out.println("contacts 2 & 4 " + contactSet);
		
	}
	
	public void testGetFutureMeetingListContact(ContactManagerImpl cm){
		Set<Contact> testSet = new HashSet<Contact>();
		testSet = cm.getContacts(0,1);
		Iterator<Contact> it = testSet.iterator();
		Contact c1 = it.next();
		// this contact 
		System.out.println("contact: " + c1);
		cm.getFutureMeetingList(c1);
	}
	
	
	public void testGetFutureMeetingListDate(ContactManagerImpl cm){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH,1);
		System.out.println(cm.getFutureMeetingList(cal));
		
	}
	
	public void testGetFutureMeetingList(ContactManagerImpl cm){
	Set<Contact> contactSet = new HashSet<Contact>();
	contactSet = cm.getContacts(0,1,2);
	Calendar cal1 = Calendar.getInstance();
	cal1.add(Calendar.MILLISECOND,100);
	cm.addFutureMeeting(contactSet, cal1);
	
	contactSet = cm.getContacts(0,3,4);
	cal1.add(Calendar.MONTH,100);
	cm.addFutureMeeting(contactSet,cal1);
	
	List<Meeting> output = new ArrayList<Meeting>();
	contactSet = cm.getContacts(0);
	Iterator<Contact> it = contactSet.iterator();
	Contact c = it.next();
	output = cm.getFutureMeetingList(c);
	List<Meeting> expected = new ArrayList<Meeting>();
	expected = cm.getFutureMeetingList(cal1);
	System.out.println("meeting by contact Geoff(0): " + output);
	System.out.println("meetings by date " + expected);
	System.out.println("meeting 100: "+cm.getFutureMeeting(100));
	}
}
