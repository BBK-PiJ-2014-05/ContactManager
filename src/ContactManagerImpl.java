import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Comparator;
/**
* A class to manage your contacts and meetings.
*/
public class ContactManagerImpl implements ContactManager, Serializable {

	List<Meeting> futureMeetingList = new ArrayList<Meeting>();
	List<Contact> contactList = new ArrayList<Contact>();
	//List<Meeting> meetingList = new ArrayList<Meeting>();
	List<PastMeeting> pastMeetingList = new ArrayList<PastMeeting>();
	
	
	/**	
* Add a new meeting to be held in the future.
*
* @param contacts a list of contacts that will participate in the meeting
* @param date the date on which the meeting will take place
* @return the ID for the meeting
* @throws IllegalArgumentException if the meeting is set for a time in the past,
* of if any contact is unknown / non-existent
*/
public int addFutureMeeting(Set<Contact> contacts, Calendar date){
	Date meetingDate = date.getTime();
	Calendar calNow = Calendar.getInstance();
	Date now = calNow.getTime();
	if (meetingDate.before(now)) {
		throw new IllegalArgumentException("Error: Date is in the past");
	}
	Meeting futureMeeting = new FutureMeetingImpl(contacts,date);
	futureMeetingList.add(futureMeeting);
	return futureMeeting.getId();
}
/**
1* Returns the PAST meeting with the requested ID, or null if it there is none.
*
* @param id the ID for the meeting
* @return the meeting with the requested ID, or null if it there is none.
* @throws IllegalArgumentException if there is a meeting with that ID happening in the future
*/
public PastMeeting getPastMeeting(int id){
		for (int i = 0; i < pastMeetingList.size(); i++){
			PastMeeting pastMeeting = pastMeetingList.get(i);
			if (pastMeeting.getId() == id){
				return pastMeeting;
			}
		} return null;
}
/**
* Returns the FUTURE meeting with the requested ID, or null if there is none.
*
* @param id the ID for the meeting
* @return the meeting with the requested ID, or null if it there is none.
* @throws IllegalArgumentException if there is a meeting with that ID happening in the past
*/
public FutureMeeting getFutureMeeting(int id){
	for (int i = 0; i < futureMeetingList.size();i++){
		Meeting meeting = futureMeetingList.get(i);
		if (meeting.getId() == id){
			Calendar calNow = Calendar.getInstance();
			Date now = calNow.getTime();
			Calendar meetingDate = meeting.getDate();
			Date meetingTime = meetingDate.getTime();
			if (meetingTime.before(now)) {
				throw new IllegalArgumentException("Error: Date is in the past");
			} else {
				FutureMeeting futureMeeting = (FutureMeeting)meeting;
				return futureMeeting;
			} 
		} 
	} return null;
}
/**
* Returns the meeting with the requested ID, or null if it there is none.
*
* @param id the ID for the meeting
* @return the meeting with the requested ID, or null if it there is none.
*/
public Meeting getMeeting(int id){
	for (int i = 0; i < futureMeetingList.size(); i++){
		Meeting meeting = futureMeetingList.get(i);
		int x = meeting.getId();
		if (x == id){
			FutureMeeting futureMeeting = (FutureMeeting)meeting;
			return futureMeeting;
		} i++;
	}
	for (int j = 0; j < pastMeetingList.size(); j++){
		Meeting pastMeeting = pastMeetingList.get(j);
		if (pastMeeting.getId() == id){
			return pastMeeting;
		} j++;	
	}
	return null;
}
/**
* Returns the list of future meetings scheduled with this contact.
*
* If there are none, the returned list will be empty. Otherwise,
* the list will be chronologically sorted and will not contain any
* duplicates.
*
* @param contact one of the user’s contacts
* @return the list of future meeting(s) scheduled with this contact (maybe empty).
* @throws IllegalArgumentException if the contact does not exist
*/
public List<Meeting> getFutureMeetingList(Contact contact){
	List<Meeting> list = new ArrayList<Meeting>();
	try {
	for (int i = 0; i < futureMeetingList.size(); i++){
		Set<Contact> contactSet = new HashSet<Contact>();
		contactSet = futureMeetingList.get(i).getContacts();
		if (contactSet.contains(contact)){
			list.add(futureMeetingList.get(i));
		}	
	} 
	} catch(IllegalArgumentException e) {System.out.println("Contact does not exist!");
	} return list;
}
/**
* Returns the list of meetings that are scheduled for, or that took
* place on, the specified date
*
* If there are none, the returned list will be empty. Otherwise,
* the list will be chronologically sorted and will not contain any
* duplicates.
*
* @param date the date
* @return the list of meetings
*/
public List<Meeting> getFutureMeetingList(Calendar date){
	
		date.clear(Calendar.HOUR_OF_DAY);
		date.clear(Calendar.AM_PM);
		date.clear(Calendar.MINUTE);
		date.clear(Calendar.SECOND);
		date.clear(Calendar.MILLISECOND);
		List<Meeting> fmList = new ArrayList<Meeting>();
			for (int i = 0; i < futureMeetingList.size(); i++){
				Meeting meeting = futureMeetingList.get(i);
				Calendar dateOfMeeting = meeting.getDate();
				dateOfMeeting.clear(Calendar.HOUR_OF_DAY);
				dateOfMeeting.clear(Calendar.AM_PM);
				dateOfMeeting.clear(Calendar.MINUTE);
				dateOfMeeting.clear(Calendar.SECOND);
				dateOfMeeting.clear(Calendar.MILLISECOND);
					if (dateOfMeeting.equals(date)){
						fmList.add(meeting);
					}
		
			}
		//Collections.sort(fmList);
		return fmList;
}
/**
* Returns the list of past meetings in which this contact has participated.
*
* If there are none, the returned list will be empty. Otherwise,
* the list will be chronologically sorted and will not contain any
2* duplicates.
*
* @param contact one of the user’s contacts
* @return the list of future meeting(s) scheduled with this contact (maybe empty).
* @throws IllegalArgumentException if the contact does not exist
*/
public List<PastMeeting> getPastMeetingList(Contact contact){
	boolean found = false;
	for (int i = 0; i < contactList.size(); i++){
		Contact c = contactList.get(i);
		if (c.equals(contact)){
			found = true;
		}
	} if (!found){
		throw new IllegalArgumentException("Contact does not exist!");
	}
	
	List<PastMeeting> pmList = new ArrayList<PastMeeting>();
	
	for (int j = 0; j < pastMeetingList.size(); j++){
		PastMeeting pm = pastMeetingList.get(j);
		Set<Contact> contacts = new HashSet<Contact>();
		contacts = pm.getContacts();
		if (contacts.contains(contact)){
			pmList.add(pm);		
		}
	}
	return pmList;
}
/**
* Create a new record for a meeting that took place in the past.
*
* @param contacts a list of participants
* @param date the date on which the meeting took place
* @param text messages to be added about the meeting.
* @throws IllegalArgumentException if the list of contacts is
* empty, or any of the contacts does not exist
* @throws NullPointerException if any of the arguments is null
*/
public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text){
	PastMeeting pastMeeting = new PastMeetingImpl(contacts,date,text);
	if (contacts.isEmpty()){
		throw new IllegalArgumentException("Set<Contact> contacts is Empty!");
	}
	if (contacts == null || date == null || text == null){
		throw new NullPointerException("Set<Contact> contacts or Calendar date or String text is Null!");
	}
	pastMeetingList.add(pastMeeting);
}
/**
* Add notes to a meeting.
*
* This method is used when a future meeting takes place, and is
* then converted to a past meeting (with notes).
*
* It can be also used to add notes to a past meeting at a later date.
*
* @param id the ID of the meeting
* @param text messages to be added about the meeting.
* @throws IllegalArgumentException if the meeting does not exist
* @throws IllegalStateException if the meeting is set for a date in the future
* @throws NullPointerException if the notes are null
*/
public void addMeetingNotes(int id, String text){
	if (text == null){
		throw new NullPointerException("Notes are null!");
	}
	boolean found = false;
	for (int i = 0; i < pastMeetingList.size();i++){
		PastMeeting pastMeeting = pastMeetingList.get(i);
		if (pastMeeting.getId() == id) {
			found = true;
			Set<Contact> contacts = new HashSet<Contact>();
			contacts = pastMeeting.getContacts();
			String notes = pastMeeting.getNotes() + text;
			Calendar cal = new GregorianCalendar();
			cal = pastMeeting.getDate();
			Calendar calNow = Calendar.getInstance();
			Date now = calNow.getTime();
			Date meetingTime = cal.getTime();
			if (now.before(meetingTime)) {
				throw new IllegalArgumentException("Error: Date is in the future!");
			}
			pastMeetingList.remove(i);
			PastMeeting replacePastMeeting = new PastMeetingImpl(contacts,cal,notes,id);
			pastMeetingList.add(replacePastMeeting);
			
		}
		
	} if (!found){
		throw new IllegalArgumentException("Meeting does not exist!");
	}
}
/**
* Create a new contact with the specified name and notes.
*
* @param name the name of the contact.
* @param notes notes to be added about the contact.
* @throws NullPointerException if the name or the notes are null
*/
public void addNewContact(String name, String notes){
	if (name == null || notes == null){
		throw new NullPointerException("Name or notes are null");
	}
	Contact contact = new ContactImpl(name,notes);
	contactList.add(contact);
}
/**
* Returns a list containing the contacts that correspond to the IDs.
*
* @param ids an arbitrary number of contact IDs
* @return a list containing the contacts that correspond to the IDs.
* @throws IllegalArgumentException if any of the IDs does not correspond to a real contact
*/
public Set<Contact> getContacts(int... ids){
	Set<Contact> contactSet = new HashSet<Contact>();
	boolean found = false;
	for(int i = 0; i < ids.length; i++){
		for (int j = 0; j < contactList.size(); j++){
			if(contactList.get(j).getId() == (ids[i])){
				contactSet.add(contactList.get(j));
				found = true;
			}
		} 
	} if (!found) {
		throw new IllegalArgumentException("Id given does not correspond to a real contact!");
	}
	return contactSet;
}
/**
* Returns a list with the contacts whose name contains that string.
3*
* @param name the string to search for
* @return a list with the contacts whose name contains that string.
* @throws NullPointerException if the parameter is null
*/
public Set<Contact> getContacts(String name){
	if (name == null) {
		throw new NullPointerException("No name provided!");
	}
	
	Set<Contact> contactSet = new HashSet<Contact>();
	for (int i = 0; i < contactList.size(); i++){
		Contact contact = contactList.get(i);
		String contactName = contact.getName();
		if (contactName.equals(name)){
			contactSet.add(contact);
		}
	} return contactSet;
}
/**
* Save all data to disk.
*
* This method must be executed when the program is
* closed and when/if the user requests it.
*/
public void flush(){
	String filename = "contactManagerImpl.ser";

    // save the object to file
    FileOutputStream fos = null;
    ObjectOutputStream out = null;
    try {
      fos = new FileOutputStream(filename);
      out = new ObjectOutputStream(fos);
      out.writeObject(futureMeetingList);
      out.writeObject(pastMeetingList);
      out.writeObject(contactList);

      out.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
   
  }

}