package org.example.model.exceptions;

public class TopicIsClosedException extends RuntimeException{
    public TopicIsClosedException(String topicId) {
        super(String.format("Topic with Id %s is already closed.",topicId));
    }
}
