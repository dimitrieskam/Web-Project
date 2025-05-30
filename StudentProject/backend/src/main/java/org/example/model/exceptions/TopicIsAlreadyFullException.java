package org.example.model.exceptions;

public class TopicIsAlreadyFullException extends RuntimeException{
    public TopicIsAlreadyFullException() {
        super("Topic is already full");
    }
}
