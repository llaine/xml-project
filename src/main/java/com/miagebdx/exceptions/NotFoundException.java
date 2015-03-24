package com.miagebdx.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * projetXML
 *
 * @author llaine
 * @package com.miagebdx.exceptions
 */

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Resource not found")
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super();
    }
}