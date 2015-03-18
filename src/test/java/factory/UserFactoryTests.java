package factory;

import app.Application;
import app.domain.User;
import app.factory.UserFactory;
import app.interfaces.IFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * projetXML
 *
 * @author llaine
 * @package factory
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserFactoryTests {

    @Autowired
    IFactory userFactory;

    private final org.slf4j.Logger log = LoggerFactory.getLogger(UserFactory.class);

    @Test
    public void testFactory() throws Exception {
        log.debug("Factory testing");
        //assertThat(this.userFactory.createClass()).isEqualTo(User.class);
    }

}
