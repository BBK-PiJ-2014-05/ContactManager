import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Contact Manager Program.
 * 
 * The program execution script. The user is presented with a menu from which they may add and obtain 
 * lists of contacts and meetings. Upon exiting the program the data created in the session is copied
 * to a csv file, which is then read upon next start-up to restore the database.
 * 
 * @author geoff_000
 *
 */
public class ContactManagerScript {

	Calendar cal;
	Scanner in;
	ContactManagerImpl cm;
	String name;
	String notes;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	ContactManagerScript cms = new ContactManagerScript();
	cms.launch();
	}
	
	public void launch(){
		cm = new ContactManagerImpl();
		displayMainMenu();
	}
	
	/**
	 * Choose meetings or contacts menu from the main menu 
	 * 
	 */
	public void displayMainMenu(){
		System.out.print("MAIN MENU\n"
				+ "1- Meetings\n"
				+ "2- Contacts\n"
				+ "3- Flush Data\n"
				+ "4- Exit\n"
				+ "SELECT>");
		in = new Scanner(System.in);
		String str = in.nextLine();
		int menuSelect = Integer.parseInt(str);
		switch (menuSelect){
		case 1:
		displayMeetingsMenu();
		break;
		case 2:
		displayContactsMenu();
		break;
		case 3:
		break;
		case 4:
		break;
		default:
		System.out.println("default");
		}
	}
	
	public void displayMeetingsMenu(){
		System.out.print("1- Schedule new Meeting\n"
				+ "2- Get past Meeting using ID\n"
				+ "3- Get future Meeting using ID\n"
				+ "4- Get meeting using ID\n"
				+ "5- Get future meeting using Contact\n"
				+ "6- Get future meeting list using Date\n"
				+ "7- Get past meetings using Contact\n"
				+ "8- Add meeting notes\n"
				+ "9- Main Menu\n"
				+ "SELECT>");
		in = new Scanner(System.in);
		String str = in.nextLine();
		int menuSelect = Integer.parseInt(str);
		switch (menuSelect){
		case 1:
		addNewContact();
		break;
		case 2:
		break;
		case 3:
		break;
		case 4:
		break;
		case 5:
		break;
		case 6:
		break;
		case 7:
		break;
		case 8:
		break;
		case 9:
		break;
		default:
		System.out.println("default");
				
	}
	}
	
	public void displayContactsMenu(){
		System.out.print("1- Add new contact\n"
				+ "2- Get contact using ID\n"
				+ "3- Get contact using name\n"
				+ "4- Main Menu\n"
				+ "SELECT>");
		in = new Scanner(System.in);
		String str = in.nextLine();
		int menuSelect = Integer.parseInt(str);
		switch (menuSelect){
		case 1:
		addNewContact();
		break;
		case 2:
		break;
		case 3:
		break;
		case 4:
		displayMainMenu();
		break;
		case 5:
		break;
	}
		
	}
	
	
	
	
	public Calendar getUserInputDate(){
		cal = Calendar.getInstance();
		in = new Scanner(System.in);
		try {
		System.out.println("Enter meeting date and time in the format dd/MM/yyyy HH:mm");
		String str = in.nextLine();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		cal.setTime(sdf.parse(str));
		System.out.println(sdf.format(cal.getTime()));
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		return cal;
	}
	
	public String getUserInputString(){
		in = new Scanner(System.in);
		String str = in.nextLine();
		return str;
	}
	
	public void addNewContact(){
		System.out.print("Enter the name of the contact: ");
		name = getUserInputString();
		System.out.print("Enter notes about the contact: ");
		notes = getUserInputString();
		cm.addNewContact(name, notes);
		displayMainMenu();
		
	}
	
	
	
	
}
