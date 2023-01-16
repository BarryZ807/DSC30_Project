import java.util.*;

public class Person {
	
    // Add instance variables here
    private String name;
    private  TreeSet<String> pnArray;
	
	public Person(String name, ArrayList<String> pnArray) {
        this.name = name;
        this.pnArray = new TreeSet<>(pnArray);
	}
	
    public String getName() {
        return name;
    }

    public boolean addPhoneNumber(String pn) {
        if (pnArray.contains(pn)){
            return false;
        }
        pnArray.add(pn);
        return  true;
    }

    public ArrayList<String> getPhoneNumbers() {
        ArrayList<String> res =  new ArrayList<>(pnArray);
        Collections.sort(res);
        return res;
    }

    public boolean deletePhoneNumber(String pn) {
        if (!pnArray.contains(pn)){
            return false;
        }
        if (pnArray.size()<=1){ // the Person needs to have at least 1 phone number.
            throw new IllegalArgumentException();
        }
        return pnArray.remove(pn);
    }
}
