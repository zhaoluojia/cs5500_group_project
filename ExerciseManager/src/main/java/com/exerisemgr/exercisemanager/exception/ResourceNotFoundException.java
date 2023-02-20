package com.exerisemgr.exercisemanager.exception;

public class ResourceNotFoundException extends RuntimeException{
  private static final long serialVersionUTD = 1L;

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String message, Throwable throwable) {
    super(message, throwable);
  }


}
