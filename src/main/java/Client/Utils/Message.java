package Utils;

public class Message{
    private String message;
    private String css;

    public Message(String message, String css){
        this.message = message;
        this.css = css;
    }
    
    public String getMessage(){
        return this.message;
    }
    public String getCss(){
        return this.css;
    }
    
    public static Message receivedMessage(String message){
        return new Message(message, "received-message");
    }

    public static Message sentMessage(String message){
        return new Message(message, "sent-message");
    }
}
