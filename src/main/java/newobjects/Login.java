package newobjects;

public class Login{

    public String username;
    public String password;
    public Integer customerID;


    public Login(){
    }
    
    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword (String password){
        this.password = password;
    }

    public void setCustomerID(Integer customerID){
        this.customerID = customerID;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public Integer getCustomerID(){
        return customerID;
    }
}