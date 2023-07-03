package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GlobalException extends Exception {


    public ResponseEntity<String> procesarErrorNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    public ResponseEntity<String> procesarErrorCouldNotBeAdded(ResourceCouldNotBeAdded cd) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cd.getMessage());
    }

}
