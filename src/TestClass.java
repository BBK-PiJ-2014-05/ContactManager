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
		//addFutureMeetings(cm);
		addPastMeetings(cm);
	}
	
	public void addContacts(ContactManagerImpl cm){
		cm.addNewContact("Geoff", "testNotesGeoff");
		cm.addNewContact("Dave", "testnotesDave");
		cm.addNewContact("Sharon", "testNotesSharon");
		cm.addNewContact("Rachel", "testNotesRachel");
	}
	
	public void addFutureMeetings(ContactManagerImpl cm){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MILLISECOND,100);
		Set<Contact> contacts = new HashSet<Contact>();
		contacts = cm.getContacts(0,1);
		cm.addFutureMeeting(contacts, cal);
		contacts = cm.getContacts(3);
		cm.addFutureMeeting(contacts, cal);
		cal.add(Calendar.MONTH,1);
		contacts = cm.getContacts(2,3);
		cm.addFutureMeeting(contacts, cal);
	}
	
	public void addPastMeetings(ContactManagerImpl cm){
		Calendar cal = Calendar.getInstance();
		Set<Contact> contacts = new HashSet<Contact>();
		contacts = cm.getContacts(2,3);
		String notes = "testAddPastMeetingNotes";
		cm.addNewPastMeeting(contacts, cal, notes);
		System.out.println("pm:" + cm.getPastMeeting(100));
	}
}
