package com.webapp.neo.model;


import javax.persistence.*;


@Entity
@Table(name = "chat_message")
public class ChatMessage {
    @Id
    @GeneratedValue
    private Long id;
    private MessageType type;
    private String content;
    private String sender;

    public MessageType getType() {
        return type;
    }

    private String recipient = "Neo";


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setType(MessageType messageType) {
    }

    public String getSender() {

        return sender;
    }

    public void setSender(String sender) {
    }

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

}