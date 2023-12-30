package interfaces;

import java.util.List;

public interface DataHandling {

    void writeNewRecord();
    List<DataObject> getRecords();
    void update();
    void delete();
}
