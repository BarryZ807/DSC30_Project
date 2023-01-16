import java.util.*;

public class ContactList {
	
	// Add instance variables here

    // contact key is person name,value Person
    private HashMap<String,Person> contactList = new HashMap<>();

    // all the names in contact list
    TreeSet<String> nameList = new TreeSet<>();
	
    public boolean createContact(Person person) {

        // if there exists a person in the list that has the same name
        // as the person to be added
        // return false
        if (nameList.contains((person.getName())) && contactList.containsKey(person.getName())){
            return  false;
        }

        nameList.add(person.getName());
        contactList.put(person.getName(),person);
        return true;

    }

    public boolean lookupContact(String name) {
        return contactList.containsKey(name);
    }

    public Person getContact(String name) {
        if (lookupContact(name)){
            return contactList.get(name);
        }
        return  null;
    }

    public Person[] getContactByRange(String start, String end) {
        if (start.compareTo(end)>=0){
            throw new IllegalArgumentException();
        }
        Set<String> subSet =nameList.subSet(start, end);
        ArrayList<Person> personArrayList = new ArrayList<>();
        for (String name:subSet){
            personArrayList.add(contactList.get(name));
        }

        return (Person[])personArrayList.toArray(new Person[0]);

    }

    public boolean deleteContact(String name) {
        if (!lookupContact(name)){
            return false;
        }
        nameList.remove(name);
        contactList.remove(name);
        return true;
    }

    public int size() {
        return contactList.size();
    }

    public String[] fetchAllNames() {
        return (String[])nameList.toArray(new String[0]);
    }

    public String[] fetchAllPhoneNumbers() {
        ArrayList<String> phoneList = new ArrayList<>();
        for (String name:nameList){
            phoneList.addAll(contactList.get(name).getPhoneNumbers());
        }
        Set <String> tem = new TreeSet<>(phoneList);
        String[] phonelist = new String[tem.size()];
        phonelist = tem.toArray(phonelist);

        return phonelist;
    }
}
