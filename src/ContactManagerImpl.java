import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of ContactManager
 * @author geoff_000
 *
 */
public class ContactManagerImpl {
	
	private ArrayList<Contact> contactList;
	private Set<Contact> meetList;
	 
	
	
	public ContactManagerImpl(){
		contactList = new ArrayList<Contact>();
		meetList = new HashSet<Contact>();
		
	}
	
	
	public void addNewContact(String name, String notes){
		Contact c = new ContactImpl(name,notes);
		contactList.add(c);
		System.out.println(c.getId());
	}
	
	public Set<Contact> getContacts(int... ids){
		for(int i = 0; i < ids.length; i++){
			for (int x = 0; x < contactList.size();x++){
				if(contactList.get(x).getId() == ids[i]){
					meetList.add(contactList.get(i));
				}
			}
		}
		Iterator<Contact> iterator = meetList.iterator(); 
		while(iterator.hasNext()){
			Contact element = (Contact) iterator.next();
			System.out.println(element.getName());
		}
		return meetList;
	}
			
			
			
		
		

	
}
