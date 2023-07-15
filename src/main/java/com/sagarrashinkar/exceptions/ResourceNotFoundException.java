package com.sagarrashinkar.exceptions;


public class ResourceNotFoundException extends RuntimeException{

    public String resourceName;
    public String fieldName;
    public Long value;

    public ResourceNotFoundException(String resourceName, String fieldName, long value) {
        super(resourceName+" not found with "+fieldName+" : "+value);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.value = value;
    }
}
