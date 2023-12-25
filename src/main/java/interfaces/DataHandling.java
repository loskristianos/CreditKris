package interfaces;

import java.util.List;

public interface DataHandling {

    void writeNewRecord();
    List getRecords();
    void update();
    void delete();
}
