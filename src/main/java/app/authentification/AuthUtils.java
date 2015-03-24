package app.authentification;

import app.domain.User;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

/**
 * projetXML
 *
 * @author llaine
 * @package app.authentification
 */
public final class AuthUtils {

    private static volatile AuthUtils instance = null;

    public User authenticate(HttpSession session){

    }


    public User getCurrentUser(HttpSession session){
        return (User) session.getAttribute("user");
    }

    public void setCurrentUser(HttpSession session, User u){
        session.setAttribute("user", u);
    }

    public final static AuthUtils getInstance() {
        if (AuthUtils.instance == null) {
            synchronized(AuthUtils.class) {
                if (AuthUtils.instance == null) {
                    AuthUtils.instance = new AuthUtils();
                }
            }
        }
        return AuthUtils.instance;
    }

}
