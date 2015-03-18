package app.factory;

import app.domain.Group;
import app.interfaces.IFactory;

/**
 * projetXML
 *
 * @author llaine
 * @package app.factory
 */
public class GroupFactory implements IFactory {

    @Override
    public Object createClass(String... properties) {
        return new Group();
    }
}
