package app.xml;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * projetXML
 *
 * @author llaine
 * @package app.xml
 */
public abstract class XMLHandler {

    private Map files = new HashMap<>();
    private String prefix = "/Users/llaine/Workspace/miage/xml/projetXML/src/data/";


    /**
     * Private function which return the right path of a file according to the type.
     * @param file
     * @param type
     * @return
     */
    private String getRightDirectory(String file, String type){
        String pathToDirectory = "";
        if(type.equals("User")){
            pathToDirectory = prefix + "users/" + file;
        }else if(type.equals("Group")){
            pathToDirectory = prefix + "groups/" + file;
        }

        return pathToDirectory;
    }

    /**
     * Private function which return the file-path according to the type of the file.
     * @param id
     * @param type
     * @return
     */
    private String getRightFileInDirectory(int id, String type){
        String pathToFile = "";

        if(type.equals("UserFactory")){
            pathToFile = prefix + "users/user-" + id + ".xml";
        }else if(type.equals("GroupFactory")){
            pathToFile = prefix + "groups/group-" + id + ".xml";
        }

        return pathToFile;
    }

    private String getDirectoryFor(String type){
        String directoryForObject = "";

        if(type.equals("user")){
            directoryForObject = prefix + "users";
        }else if(type.equals("group")){
            directoryForObject = prefix + "groups";
        }

        return directoryForObject;
    }

    /**
     * Create the file.
     * @param file
     */
    public void setFile(String file){

        String pathToFile = prefix + file;

        File f = new File(pathToFile);
        f.getParentFile().mkdirs();

        try {
            f.createNewFile();
        } catch(Exception e){
            e.printStackTrace();
        }

        // Creating the file and saving to a HashMap
        // TODO Persist the HashMap
        this.files.put(file, f.getAbsolutePath());
    }

    /**
     * Serialize a specific object to a file.
     * @param o
     * @throws Exception
     */
    public void saveObject(String filename, Object o) throws Exception {
        try {
            File f = new File(this.getRightDirectory(filename, o.getClass().getName()));

            if(f.exists() && !f.isDirectory()) {

                String fileName = (String) this.files.get(filename);

                FileOutputStream os = new FileOutputStream(fileName);

                XMLEncoder encoder = new XMLEncoder(os);

                encoder.writeObject(o);

                encoder.close();
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Give a specific object
     * @param idObject
     * @throws Exception
     * @return Object
     */
    public Object loadObject(int idObject, String type) throws Exception {
        try {

            File f = new File(getRightFileInDirectory(idObject, type));

            FileInputStream fs = new FileInputStream(f.getAbsolutePath());

            XMLDecoder decoder = new XMLDecoder(fs);

            return decoder.readObject();

        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * List all file from a directory
     * @param type
     * @return
     */
    public List<Object> listAllByType(String type){
        File folder = new File(getDirectoryFor(type));

        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }

        return null;
    }

}
