import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/** test case
 * 
 * @author geoff_000
 *
 */

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
		Calendar calendar = new GregorianCalendar(2015,01,28,12,20,37);
		System.out.println(sdf.format(calendar.getTime()));
		
		int year = calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println(year);
		
		String date = sdf.format(new Date());
		System.out.println(date);
		
		ContactManagerImpl cm = new ContactManagerImpl();
		
		
		
		
		
		
		
	}

}
