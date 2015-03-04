import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ContactTest {
	
	private Contact c1;
	private Contact c2;
	
	
@Before	
public void setUp(){
	c1 = new ContactImpl("Contact#1","");
	c2 = new ContactImpl("Contact#2","");
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
public void testgetName(){
	String output = c1.getName();
	String expected = "Contact#1";
	assertEquals(output,expected);
}
	
	
	
	
	
	
}
