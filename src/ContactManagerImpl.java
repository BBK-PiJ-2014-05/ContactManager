import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Implementation of ContactManager
 * @author geoff_000
 *
 */
public class ContactManagerImpl implements ContactManager {
	
	private ArrayList<Contact> contactList;
	private Set<Contact> contactSet;
	private ArrayList<Meeting> meetingList;
	private ArrayList<FutureMeeting> futureMeetingList;
	private ArrayList<PastMeeting> pastMeetingList;
	Calendar cal;
	Scanner in;
	 
	
	
	public ContactManagerImpl(){
		contactList = new ArrayList<Contact>();
		contactSet = new HashSet<Contact>();
		meetingList = new ArrayList<Meeting>();
		futureMeetingList = new ArrayList<FutureMeeting>();
		pastMeetingList = new ArrayList<PastMeeting>();
		
	}
	
	public Set<Contact> getMeetList(){
		return contactSet;
	}
	
	public static void main(String[] args){
		ContactManagerImpl cm = new ContactManagerImpl();
		cm.launch();
	}
	
	public void launch(){
		displayMainMenu();
	}
	
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
		addNewMeeting();
		displayMainMenu();
		break;
		case 2:
		break;
		case 3:
		meetingsMenuGetFutureMeetingwithId();
		displayMainMenu();
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
		displayMainMenu();
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
		userInputContact();
		break;
		case 2:
		getContactsUsingId();
		displayMainMenu();
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
	
	public String getUserInputString(){
		in = new Scanner(System.in);
		String str = in.nextLine();
		return str;
	}
	
	
	public Calendar getUserInputDate(){
		Calendar cal = Calendar.getInstance();
		in = new Scanner(System.in);
		try {
		System.out.print("Enter meeting date and time in the format dd/MM/yyyy HH:mm");
		String str = in.nextLine();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		cal.setTime(sdf.parse(str));
		//System.out.println(sdf.format(cal.getTime()));
		} catch (ParseException e){
			e.printStackTrace();
		}
		
		return cal;
	}
	
	public void getContactsUsingId(){
		System.out.println("Enter ids...");
		in = new Scanner(System.in);
		String inputLine = in.nextLine();
		String[] splitLine = inputLine.split(",");
		int[] arr = new int[splitLine.length];
		for (int i = 0; i < splitLine.length; i++){
			arr[i] = Integer.parseInt(splitLine[i]);
		}
		getContacts(arr);
	}
	
	public void addNewMeeting(){
		//Set<Contact> contactSet = new HashSet<Contact>();
		getContactsUsingId();
		Calendar userInputDate = getUserInputDate();
		Date inputDate = userInputDate.getTime();
		Calendar timeNow = Calendar.getInstance();
		Date now = timeNow.getTime();
		//int meetingID = addMeeting(contactSet, userInputDate);
		if (now.before(inputDate)) {
			addFutureMeeting(contactSet, userInputDate);
		} else {
			System.out.println("This meeting is in the past- enter notes about the meeting: ");
			String notes = getUserInputString();
			addNewPastMeeting(contactSet, userInputDate, notes);
		}
		
	}
	
	public int addMeeting(Set<Contact> contacts, Calendar date){
		Meeting m = new MeetingImpl(contacts, date);
		meetingList.add(m);
		return m.getId();
	}
	
	public int addFutureMeeting(Set<Contact> contacts, Calendar date){
		FutureMeeting fm = new FutureMeetingImpl(contacts,date);
		confirmMeeting(fm);
		System.out.println("<Meeting ID: " + fm.getId() + " >");
		futureMeetingList.add(fm);
		return fm.getId();	
	}
	
	public void confirmMeeting(Meeting meeting){
		Date dateDisplay = meeting.getDate().getTime();
		Set<Contact> meetingContacts = meeting.getContacts();
		Iterator<Contact> iterator = meetingContacts.iterator(); 
		System.out.println("Scheduled meeting for: " + dateDisplay);
		System.out.println("Attendees: ");
		while(iterator.hasNext()){
			Contact element = (Contact) iterator.next();
			System.out.println(element.getName() + " <ID: " + element.getId()+">");
		}
	}
	
	public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text){
		PastMeeting pm = new PastMeetingImpl(contacts,date,text);
		confirmMeeting(pm);
		System.out.println("<Meeting ID: " + pm.getId() + " >");
		pastMeetingList.add(pm);		
	}
	
	public void addMeetingNotes(int id, String text){
		
	}
	
	
	public PastMeeting getPastMeeting(int id){
		return null;
	}
	
	public void meetingsMenuGetFutureMeetingwithId(){
		System.out.print("Enter the meeting ID: ");
		in = new Scanner(System.in);
		int x = in.nextInt();
		getFutureMeeting(x);
	}
	
	
	public FutureMeeting getFutureMeeting(int id){
		boolean found = false;
		for (int i = 0; i < futureMeetingList.size(); i++){
			if (futureMeetingList.get(i).getId() == id){
				System.out.println("found it!");
				found = true;
				System.out.println("Date: " + futureMeetingList.get(i).getDate());
				System.out.println("Attendees: " + futureMeetingList.get(i).getContacts());
				return futureMeetingList.get(i);
			}
		}
		if (!found) {
			System.out.println("meeting not found");
		}
		return null;
	}
	
	public Meeting getMeeting(int id){
		return null;
	}
	
	public List<Meeting> getFutureMeetingList(Contact contact){
		return null;
	}
	
	public List<Meeting> getFutureMeetingList(Calendar date){
		return null;
	}
	
	public List<PastMeeting> getPastMeetingList(Contact contact){
		return null;
	}
	
	
	
	public void userInputContact(){
		System.out.print("Enter the name of the Contact: ");
		String name = getUserInputString();
		System.out.print("Enter notes about the contact: ");
		String notes = getUserInputString();
		addNewContact(name,notes);
		displayMainMenu();
	}
	
	
	public void addNewContact(String name, String notes){
		Contact c = new ContactImpl(name,notes);
		contactList.add(c);
		System.out.println("Added: " + c.getName() + "  <ID: " + c.getId()+ ">");
	}
	
	
	
	
	public Set<Contact> getContacts(int... ids){
		contactSet.clear();
		for(int i = 0; i < ids.length; i++){
			for (int x = 0; x < contactList.size();x++){
				if(contactList.get(x).getId() == (ids[i])){
					contactSet.add(contactList.get(x));
				}
			}
		}
		Iterator<Contact> iterator = contactSet.iterator(); 
		while(iterator.hasNext()){
			Contact element = (Contact) iterator.next();
			System.out.println(element.getName() + " <ID: " + element.getId()+">");
		}
		return contactSet;
	}
		
	public Set<Contact> getContacts(String name){
		contactSet.clear();
		boolean found = false;
		for(int i = 0; i < contactList.size(); i++){
			if(contactList.get(i).getName().equals(name)){
				contactSet.add(contactList.get(i));
				found = true;
			}
		}
		if (!found){
			System.out.println("Contact not found");
		}
		return contactSet;
	}
		
	public void flush(){
		
	}
	
	
	
		

	
}
