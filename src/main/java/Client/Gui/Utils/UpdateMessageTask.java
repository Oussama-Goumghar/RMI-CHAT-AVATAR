package Gui.Utils;

import java.lang.Thread;
import Gui.Screen.Chat;
import Utils.Message;

public class UpdateMessageTask extends Thread{
    private Chat chat;
    private String message;

    public UpdateMessageTask(Chat chat, String message){
        this.chat = chat;
        this.message = message;
    } 
    
    public void run(){
        this.chat.addMessage(Message.receivedMessage(this.message));
    }
}
