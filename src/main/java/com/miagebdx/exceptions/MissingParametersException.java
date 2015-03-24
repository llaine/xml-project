package com.miagebdx.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * projetXML
 *
 * @author llaine
 * @package com.miagebdx.exceptions
 */

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Resource fields are missing")
public class MissingParametersException extends RuntimeException {
    public MissingParametersException() {
        super();
    }
}

