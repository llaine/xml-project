package app.interfaces;


import java.util.Properties;

/**
 * projetXML
 *
 * @author llaine
 * @package app.interfaces
 */
public interface IFactory {

    public Object createClass(String... properties);

}
