package interfaces;

import java.util.HashMap;

public interface Banking {
    HashMap<String, String> getDetails();
    void setDetails(HashMap<String,String> details);
}
