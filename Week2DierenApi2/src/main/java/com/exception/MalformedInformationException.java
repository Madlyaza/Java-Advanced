package com.exception;

public class MalformedInformationException extends RuntimeException
{
        public MalformedInformationException(String errorMessage)
        {
            super(errorMessage);
        }

}
