import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/** test case
 * 
 * @author geoff_000
 *
 */

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestClass test = new TestClass();
		test.launch();
		}
	
	public void launch(){
		ContactManagerImpl cm = new ContactManagerImpl();
		addContacts(cm);
		//testFutureMeetings(cm);
		testPastMeetings(cm);
		//testGetContacts(cm);
		//testGetFutureMeetingListDate(cm);
	}
	
	public void addContacts(ContactManagerImpl cm){
		cm.addNewContact("Geoff", "testNotesGeoff");
		cm.addNewContact("Dave", "testnotesDave");
		cm.addNewContact("Sharon", "testNotesSharon");
		cm.addNewContact("Rachel", "testNotesRachel");
		cm.addNewContact("Geoff", "testNotesGeoff");
	}
	
	public void testFutureMeetings(ContactManagerImpl cm){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MILLISECOND,100);
		Set<Contact> contacts = new HashSet<Contact>();
		contacts = cm.getContacts(0,1);
		cm.addFutureMeeting(contacts, cal);
		contacts = cm.getContacts(3);
		cm.addFutureMeeting(contacts, cal);
		cal.add(Calendar.MILLISECOND,1);
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
		//Set<Contact> contacts = new HashSet<Contact>();
		//String n = null;
		System.out.println(""+cm.getContacts("Geoff"));
		//System.out.println("testGetContacts: " + contacts);
		Set<Contact> contactSet = new HashSet<Contact>();
		contactSet = cm.getContacts(2,4);
		System.out.println("getContacts: " + contactSet);
		Calendar cal = Calendar.getInstance();
		cm.getFutureMeetingList(cal);
	}
	
	public void testGetFutureMeetingListDate(ContactManagerImpl cm){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH,1);
		System.out.println(cm.getFutureMeetingList(cal));	
	}
}

