/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package monopoly.beans;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Reena
 */
public class Message {

    private String messageType;
    private String messageText;
    private Map<String, String> additionalStringMessage = new HashMap<>();
    private String objectType = "";

    public Message(String type, String message) {
        this.messageText = message;
        this.messageType = messageType;
    }

    public String getMessage() {
        return messageText;
    }

    public void setMessage(String message) {
        this.messageText = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getObjectType() {
        return objectType;
    }

    public Map<String, String> getAdditionalStringMessage() {
        return additionalStringMessage;
    }

    public void setAdditionalStringMessage(Map<String, String> additionalStringMessage) {
        this.additionalStringMessage = additionalStringMessage;
    }
}