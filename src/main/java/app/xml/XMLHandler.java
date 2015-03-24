package app.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
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

    private final Logger log = LoggerFactory.getLogger(XMLHandler.class);


    /**
     * Private function which return the right path of a file according to the type.
     * @param file
     * @param type
     * @return
     */
    private String getRightDirectory(String file, String type){
        log.debug("Getting the right directory for {}, {}", file, type);

        String pathToDirectory = "";
        if(type.equals("app.domain.User")){
            pathToDirectory = prefix + "users/" + file;
        }else if(type.equals("app.domain.Group")){
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
    private String getRightFileInDirectory(Long id, String type){
        log.debug("Getting the right directory for {}, {}", id, type);
        String pathToFile = "";

        if(type.equals("app.dao.UserRepository")){
            pathToFile = prefix + "users/user-" + id + ".xml";
        }else if(type.equals("app.dao.GroupRepository")){
            pathToFile = prefix + "groups/group-" + id + ".xml";
        }

        return pathToFile;
    }

    private String getDirectoryFor(String type){
        log.debug("Getting the right directory for {}", type);

        String directoryForObject = "";

        if(type.equals("app.domain.User")){
            directoryForObject = prefix + "users/";
        }else if(type.equals("app.domain.Group")){
            directoryForObject = prefix + "groups/";
        }

        return directoryForObject;
    }

    /**
     * Create the file.
     * @param file
     */
    public void setFile(String file, Object type){

        String pathToFile = this.getDirectoryFor(type.getClass().getName());

        pathToFile += file;

        File f = new File(pathToFile);
        f.getParentFile().mkdirs();


        try {
            f.createNewFile();
        } catch(Exception e){
            e.printStackTrace();
        }

        log.debug("Creating a file {}", pathToFile);

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

            log.debug("Saving {} to path {} ", o.getClass(), f.getAbsolutePath());

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
    public Object loadObject(Long idObject, String type) throws Exception {
        log.debug("Loading object {}, {}", idObject, type);

        try {
            String f = getRightFileInDirectory(idObject, type);

            log.debug("Fetching object in file {} ", f);

            FileInputStream fs = new FileInputStream(f);

            XMLDecoder decoder = new XMLDecoder(fs);

            return decoder.readObject();

        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     *
     * @param path
     * @return
     */
    public Object loadObjectFromFile(String path){
        log.debug("Loading object {}", path);

        try {

            FileInputStream fs = new FileInputStream(path);

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
        log.debug("Listing all object from type {}", type);

        File folder = new File(getDirectoryFor(type));
        File[] listOfFiles = folder.listFiles();

        List<Object> objectsInDirectory = new ArrayList<>();

        if(listOfFiles != null) {
            for(File file : listOfFiles) {
                if (file.isFile())
                    objectsInDirectory.add(this.loadObjectFromFile(file.getAbsolutePath()));
            }
        }

        return objectsInDirectory;
    }

}
