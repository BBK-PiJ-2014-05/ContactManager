import static org.junit.Assert.*;

import org.junit.Test;


public class ContactTest {
@Test
public void testID(){
	Contact con1 = new ContactImpl("Con1");
	Contact con2 = new ContactImpl("Con2");
	int output = con1.getId();
	int expected = 1;
	assertEquals(output,expected);
	output = con2.getId();
	expected = 2;
	assertEquals(output,expected);
	
}

@Test
public void testGetNotes(){
	Contact con1 = new ContactImpl("Con1");
	con1.addNotes("first note");
	con1.addNotes("second note");
	String output = con1.getNotes();
	String expected = ";first note;second note";
	assertEquals(output,expected);
}
	
	
	
	
	
	
	
}
