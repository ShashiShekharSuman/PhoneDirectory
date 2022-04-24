package phonedirectory;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Contact {

    private String name;
    private long number;
    private String email;
    
    private static Map<String, Contact> contact_map = new HashMap<String, Contact>();

    public Contact(String name, long number, String email) {
        this.name = name;
        this.number = number;
        this.email = email;
        contact_map.put(this.getName().toLowerCase(), this);
        contact_map.put(this.getNumber()+"", this);
    }
        
    public static Contact get(String name) {
        return contact_map.get(name);
    }
    
    public static void delete(String str){
        str = str.toLowerCase();
        contact_map.remove(str);
    }

    public String getName() {
        return name;
    }

    public long getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Contact{" + "name=" + name + ", number=" + number + ", email=" + email + '}';
    }
    
    public static boolean validateEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
