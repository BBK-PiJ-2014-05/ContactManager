import java.io.Serializable;

/** implementation of Contact interface.
 * 
 * @author geoff_000
 *
 */


public class ContactImpl implements Contact, Serializable {

	private String name;
	private int id = 0;
	private String notes;
	
	private static int nextId = 0;
	
	/** 
	 * Each contact has a unique ID generated from a static variable. This acts as the key
	 * since there may be more than one person with the same name
	 * 
	 * @param name
	 * @Param notes
	 */
	
	public ContactImpl(String name,String notes){
		this.name = name;
		id = generateId();
		this.notes = notes;
		}
	
	/**
	 * Creation of the key is managed by the class through manipulating the static variable nextId
	 * 
	 * @return the unique ID
	 */
	
	private int generateId(){
		return nextId++;
	}
	
	public String getName(){
		return name;
	}
	
	public int getId(){
		return id;
	}
	
	public String getNotes(){
		return notes;
	}
	
	public void addNotes(String note){
		notes = notes + " ; "+ note;
	}
	
	/**
	 * displays the object's variables in a user-friendly way
	 */
	
	@Override
	public String toString(){
		String string = "Contact ID: " + id + " Contact Name: " + name + " Contact Notes: " + notes;
		return string;
	}
	
	/**
	 * used for testing, to avoid escalating id's
	 */
	
	public static void resetNextId(){
		nextId = 0;
	}
}
