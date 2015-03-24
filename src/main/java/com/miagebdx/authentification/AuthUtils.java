package com.miagebdx.authentification;

import com.miagebdx.domain.User;
import com.miagebdx.exceptions.UnAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * projetXML
 *
 * @author llaine
 * @package com.miagebdx.authentification
 */
public final class AuthUtils {

    private static volatile AuthUtils instance = null;
    private static final int ONE_HOURS = 60 * 60 * 1000;
    private final Logger log = LoggerFactory.getLogger(AuthUtils.class);


    /**
     * Basic firewall.
     * Check if the user in session and if the timestamp token is always valid.
     * @param session
     * @throws UnAuthorizedException
     */
    public void firewall(HttpSession session) throws UnAuthorizedException {
        if(null == session.getAttribute("user") || null == session.getAttribute("timestamp")){
            throw new UnAuthorizedException();
        }else{

            Date dt = (Date) session.getAttribute("timestamp");

            if(!this.checkTimestampAuthenticity(dt.getTime())){

                throw new UnAuthorizedException();

            }
        }
    }

    /**
     * Setup credentials for the current user.
     * @param u
     * @param session
     */
    public void bootstrapCredentials(User u, HttpSession session){
        if(!u.equals(null)){
            session.setAttribute("user", u);
            session.setAttribute("timestamp", new Date());
        }
    }


    private boolean checkTimestampAuthenticity(Long timestamp){
        Boolean isValid = true;
        long oneHour = System.currentTimeMillis() - ONE_HOURS;

        // timestamp is older than one hour
        if (timestamp < oneHour) {
            isValid = false;
        }

        return isValid;
    }


    public AuthUtils(){

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
