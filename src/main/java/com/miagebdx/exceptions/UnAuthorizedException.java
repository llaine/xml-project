package com.miagebdx.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * projetXML
 *
 * @author llaine
 * @package com.miagebdx.rest
 */
@ResponseStatus(value= HttpStatus.PROXY_AUTHENTICATION_REQUIRED, reason="Unauthorized access to Resource")
public class UnAuthorizedException extends RuntimeException {
    public UnAuthorizedException() {
        super();
    }
}




