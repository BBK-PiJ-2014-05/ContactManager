import static org.junit.Assert.*;

import org.junit.*;

import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ContactTest {

	private ContactManager cm;
	Set<Contact> contactSet;
	SimpleDateFormat sdf;


	
	
@Before	
public void setUp(){
	cm = new ContactManagerImpl();
	contactSet = new HashSet<Contact>();
	sdf = new SimpleDateFormat("dd/M/yyyy hh:mm");
	cm.addNewContact("Geoff", "geoff notes");
	cm.addNewContact("Dave", "dave notes");
	cm.addNewContact("Sharon", "sharon notes");
	cm.addNewContact("Geoff", "geoff notes");
	
	
	
}

@After
public void cleanUp(){
	ContactImpl.resetNextId();
	MeetingImpl.resetId();
}

	
@Test(expected = IllegalArgumentException.class)
public void GetContactsFail(){
	contactSet = cm.getContacts(9);	
}

@Test
public void GetContactsPass(){
	contactSet = cm.getContacts(0,1,2);
	int output = contactSet.size();
	int expected = 3;
	assertEquals(expected, output);
}

@Test
public void testGetNotes(){
	contactSet = cm.getContacts(0);
	Iterator<Contact> it = contactSet.iterator();
	Contact c = it.next();
	String output = c.getNotes();
	String expected = "geoff notes";
	assertEquals(expected,output);
	
}


@Test
public void testaddnewContact(){
	cm.addNewContact("Cindy", "cindy notes");
	contactSet = cm.getContacts(4);
	Iterator<Contact> it = contactSet.iterator();
	Contact c = it.next();
	String output = c.getName();
	String expected = "Cindy";
	assertEquals(expected,output);
}


@Test
public void addFutureMeetingPass(){
	
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MILLISECOND,100);
	contactSet = cm.getContacts(0,1,2);
	int output = cm.addFutureMeeting(contactSet, cal);
	int expected = 100;
	assertEquals(output,expected);
}

@Test(expected = IllegalArgumentException.class)
public void addFutureMeetingFail(){
	
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MILLISECOND,-10);
	contactSet = cm.getContacts(0,1,2);
	int output = cm.addFutureMeeting(contactSet,cal);
	int expected = 100;
	assertEquals(expected,output);
}

@Test
public void testGetMeetingId(){
	
	contactSet = cm.getContacts(0,1);
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MILLISECOND, 100);
	int output = cm.addFutureMeeting(contactSet,cal);
	int expected = 100;
	assertEquals(expected,output);
}


@Test 
public void addNewPastMeetingPass(){
	
	contactSet = cm.getContacts(0,1,2);
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MILLISECOND,-10);
	String expected = "test notes";
	cm.addNewPastMeeting(contactSet, cal,expected);
	String output = cm.getPastMeeting(100).getNotes();
	assertEquals(expected,output);
	
}

@Test(expected = NullPointerException.class)
public void addNewPastMeetingFail(){
	
	contactSet = null;
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MILLISECOND,-10);
	String expected = "test notes";
	cm.addNewPastMeeting(contactSet, cal,expected);
	String output = cm.getPastMeeting(100).getNotes();
	assertEquals(expected,output);
}


@Test
public void testAddMeetingNotes(){
	
	contactSet = cm.getContacts(0,1,2);
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MILLISECOND,-100);
	String notes = "init notes_";
	cm.addNewPastMeeting(contactSet,cal,notes);
	cm.addMeetingNotes(100,"_add notes");
	String output = cm.getPastMeeting(100).getNotes();
	String expected = "init notes__add notes";
	assertEquals(expected,output);	
}


@Test
public void testGetFutureMeeting(){
	
	Set<Contact> contacts = new HashSet<Contact>();
	contacts.add(c1);
	contacts.add(c2);
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MILLISECOND,5);
	int id = cm.addFutureMeeting(contacts, cal);
	FutureMeeting futureMeeting = cm.getFutureMeeting(id);
	int output = futureMeeting.getId();
	int expected = 100;
	assertEquals(output,expected);
	
}
	
@Test
public void testGetMeeting(){
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MILLISECOND,10);
	Set<Contact> contacts = new HashSet<Contact>();
	contacts.add(c1);
	contacts.add(c2);
	int meetingId = cm.addFutureMeeting(contacts, cal);
	Meeting expected = cm.getFutureMeeting(meetingId);
	Meeting output = cm.getMeeting(meetingId);
	assertEquals(output,expected);
	
}

@Test
public void testGetFutureMeetingListContact(){
	Set<Contact> testSet = new HashSet<Contact>();
	testSet.add(c1);
	testSet.add(c2);
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MILLISECOND,10);
	cm.addFutureMeeting(testSet, cal);
	List<Meeting> output = new ArrayList<Meeting>();
	output = cm.getFutureMeetingList(c1);
	List<Meeting> expected = new ArrayList<Meeting>();
	expected = cm.getFutureMeetingList(cal);
	assertEquals(output,expected);
	
	
	
}

@Test
public void testGetFutureMeetingListDate(){
	List<Meeting> expected = new ArrayList<Meeting>();
	Set<Contact> contacts = new HashSet<Contact>();
	contacts.add(c1);
	contacts.add(c2);
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.SECOND, 1);
	cm.addFutureMeeting(contacts,cal);
	expected = cm.getFutureMeetingList(c1);
	List<Meeting> output = new ArrayList<Meeting>();
	output = cm.getFutureMeetingList(cal);
	assertEquals(output,expected);
}
	
@Test 
public void testGetPastMeetingList(){
	Set<Contact> contacts = new HashSet<Contact>();
	contacts.add(c1);
	contacts.add(c2);
	Calendar cal = Calendar.getInstance();
	String notes = "tetsnotes1";
	cm.addNewPastMeeting(contacts, cal, notes);
	notes = notes + "morenotes";
	cm.addNewPastMeeting(contacts,cal,notes);
	List<PastMeeting> pastMeetingList = new ArrayList<PastMeeting>();
	pastMeetingList = cm.getPastMeetingList(c1);
	PastMeeting output = pastMeetingList.get(0);
	PastMeeting expected = cm.getPastMeeting(100);
	assertEquals(output,expected);
}

@Test
public void testGetMeetingDate(){
	Set<Contact> c = new HashSet<Contact>();
	c.add(c1);
	c.add(c2);
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MILLISECOND,100);
	Meeting m = new FutureMeetingImpl(c,cal);
	Calendar expected = cal;
	Calendar output = m.getDate();
	assertEquals(output,expected);
	
}

@Test
public void testGetMeetingNotes(){
	Set<Contact> testSet = new HashSet<Contact>();
	testSet.add(c1);
	testSet.add(c2);
	Calendar cal = Calendar.getInstance();
	PastMeeting meeting = new PastMeetingImpl(testSet,cal,"testnotes");
	String output = meeting.getNotes();
	String expected = "testnotes";
	assertEquals(output,expected);
	
}

@Test
public void testAddNewContact(){
	String name = "testName";
	String notes = "testNotes";
	cm.addNewContact(name, notes);
	Set<Contact> testContacts = new HashSet<Contact>();
	testContacts = cm.getContacts(4);
	Contact c1 = new ContactImpl("testName","testNotes");
	assertSame(testContacts,c1);
	
}
	
@Test
public void testGetContactsUsingIds(){
	cm.addNewContact("John", "test");
	cm.addNewContact("Dave", "test");
	Set<Contact> output = new HashSet<Contact>();
	output = cm.getContacts(0,1);
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MILLISECOND, 10);
	cm.addFutureMeeting(output, cal);
	Set<Contact> expected = new HashSet<Contact>();
	expected = cm.getFutureMeeting(100).getContacts();
	assertEquals(output,expected);
}

@Test
public void testGetContactsUsingName(){
	Set<Contact> output = new HashSet<Contact>();
	Set<Contact> expected = new HashSet<Contact>();
	cm.addNewContact("Geoff", "GeoffNotes");
	cm.addNewContact("Geoff", "Geoff2Notes");
	cm.addNewContact("Sharon", "SharonNotes");
	expected = cm.getContacts(4,5);
	output = cm.getContacts("Geoff");
	assertEquals(output,expected);
}

@Test
public void testFlush(){
	
}
}
