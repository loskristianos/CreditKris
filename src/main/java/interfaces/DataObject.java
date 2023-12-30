package interfaces;

import java.util.HashMap;

public interface DataObject {
    HashMap<String, String> getDetails();
    void setDetails(HashMap<String,String> details);

    String getObjectType();
}
