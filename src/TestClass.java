import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
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
		ContactManager cm = new ContactManagerImpl();
		Contact c1 = new ContactImpl("John","good guy");
		Contact c2 = new ContactImpl("Dave","marketing specialist");
		Contact c3 = new ContactImpl("John","Could be useful with accounts");
		Set<Contact> contacts1 = new HashSet<Contact>();
		contacts1.add(c1);
		contacts1.add(c2);
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.MILLISECOND,1000);
		cm.addFutureMeeting(contacts1, cal1);
		//cm.getFutureMeetingList(c1);
		contacts1.clear();
		contacts1.add(c3);
		cm.addFutureMeeting(contacts1,cal1);
		cm.getFutureMeetingList(c3);
		
		
		
		
	}
	

}
