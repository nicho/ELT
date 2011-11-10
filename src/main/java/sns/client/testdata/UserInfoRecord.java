package sns.client.testdata;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class UserInfoRecord extends ListGridRecord {
	  public UserInfoRecord(String id, String name, String email, String password) {  
	        setId(id);  
	        setName(name);  
	        setEmail(email);  
	        setPassWord(password);  
	    }  
	  public void setId(String id) {  
	        setAttribute("id", id);  
	    }  
	  
	    public String getId() {  
	        return getAttributeAsString("id");  
	    }  
	    
	    public void setName(String name) {  
	        setAttribute("name", name);  
	    }  
	  
	    public String getName() {  
	        return getAttributeAsString("name");  
	    }  
	    
	    public void setEmail(String email) {  
	        setAttribute("email", email);  
	    }  
	  
	    public String getEmail() {  
	        return getAttributeAsString("email");  
	    }  
	    
	    
	    public void setPassWord(String password) {  
	        setAttribute("password", password);  
	    }  
	  
	    public String getPassWord() {  
	        return getAttributeAsString("password");  
	    }  
}
