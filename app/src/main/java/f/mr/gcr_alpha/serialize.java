package f.mr.gcr_alpha;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class serialize {

    public serialize()
    {

    }

    public void saveObject(Object p, File f){
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f)); //Select where you wish to save the file...
            oos.writeObject(p); // write the class as an 'object'
            oos.flush(); // flush the stream to insure all of the information was written to 'save_object.bin'
            oos.close();// close the stream
        }
        catch(Exception ex)
        {
            Log.v("Serialization S Error: ",ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Object loadSerializedObject(File f)
    {
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            Object o = ois.readObject();
            return o;
        }
        catch(Exception ex)
        {
            Log.v("Serialization R Error: ",ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
}
