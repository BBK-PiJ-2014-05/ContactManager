import static org.junit.Assert.*;

import org.junit.*;

import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.After;
import org.junit.Before;
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
	
	contactSet = cm.getContacts(0,9);
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
public void testGetMeeting(){
	
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MILLISECOND,100);
	contactSet = cm.getContacts(0,1,2);
	int meetingId = cm.addFutureMeeting(contactSet,cal);
	Meeting expected = cm.getFutureMeeting(meetingId);
	Meeting output = cm.getMeeting(meetingId);
	assertEquals(expected,output);
	
	cal.add(Calendar.MILLISECOND,-100);
	cm.addNewPastMeeting(contactSet, cal, "test notes");
	expected = cm.getPastMeeting(101);
	output = cm.getMeeting(101);
	assertEquals(expected,output);
}


@Test
public void testGetFutureMeetingListContact(){
	
	contactSet = cm.getContacts(0,1,2);
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MILLISECOND,100);
	cm.addFutureMeeting(contactSet, cal);
	
	contactSet = cm.getContacts(0,3,4);
	cal.add(Calendar.MILLISECOND,100);
	cm.addFutureMeeting(contactSet,cal);
	
	List<Meeting> output = new ArrayList<Meeting>();
	contactSet = cm.getContacts(0);
	Iterator<Contact> it = contactSet.iterator();
	Contact c = it.next();
	output = cm.getFutureMeetingList(c);
	List<Meeting> expected = new ArrayList<Meeting>();
	expected = cm.getFutureMeetingList(cal);
	assertEquals(expected,output);
}

@Test
public void testGetFutureMeetingListDate(){
	
	contactSet = cm.getContacts(0,1,2);
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.SECOND, 1);
	cm.addFutureMeeting(contactSet,cal);
	
	contactSet.clear();
	contactSet = cm.getContacts(0,2);
	cm.addFutureMeeting(contactSet, cal);
	
	List<Meeting> output = new ArrayList<Meeting>();
	output = cm.getFutureMeetingList(cal);
	List<Meeting> expected = new ArrayList<Meeting>();
	
	contactSet.clear();
	contactSet = cm.getContacts(0);
	Iterator<Contact> it = contactSet.iterator();
	Contact c = it.next();
	
	expected = cm.getFutureMeetingList(c);
	assertEquals(expected, output);
}
	

@Test
public void testGetMeetingDate(){
	
	contactSet = cm.getContacts(0,1);
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.MILLISECOND,100);
	cm.addFutureMeeting(contactSet, cal);
	Calendar expected = cal;
	Calendar output = cm.getFutureMeeting(100).getDate();
	assertEquals(expected, output);
}

@Test
public void testGetContactsUsingName(){
	contactSet = cm.getContacts("Geoff");
	Set<Contact> expected = new HashSet<Contact>();
	expected = cm.getContacts(0,3);
	assertEquals(expected,contactSet);
	
}

@Test
public void testFlush(){
	
}
}
