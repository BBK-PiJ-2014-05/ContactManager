import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ContactTest {
	
	private Contact c1;
	private Contact c2;
	private ContactManagerImpl cm;
	
	
@Before	
public void setUp(){
	c1 = new ContactImpl("Contact#1","");
	c2 = new ContactImpl("Contact#2","");
	cm = new ContactManagerImpl();
}

@After
public void cleanUp(){
	ContactImpl.resetNextId();
}

	
@Test
public void testID(){
	int output = c1.getId();
	int expected = 0;
	assertEquals(expected,output);
	output = c2.getId();
	expected = 1;
	assertEquals(expected,output);
	
	
}

@Test
public void testGetNotes(){
	c1.addNotes("first note");
	c1.addNotes("second note");
	String output = c1.getNotes();
	String expected = ";first note;second note";
	assertEquals(output,expected);
}

@Test
public void testGetName(){
	String output = c1.getName();
	String expected = "Contact#1";
	assertEquals(output,expected);
}

@Test
public void testaddFutureMeeting(){
	Set<Contact> contacts = new HashSet<Contact>();
	contacts.add(c1);
	contacts.add(c2);
	Calendar cal = Calendar.getInstance();
	int output = cm.addFutureMeeting(contacts, cal);
	int expected = 100;
	assertEquals(output,expected);
}
	
	
	
	
	
	
}
