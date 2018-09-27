package Gui.Utils;

import Gui.Utils.LimitedTextField;
import Gui.Screen.Chat;
import Logic.ClientApp;
import Utils.Message;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class SendClickEvent implements EventHandler <MouseEvent>{
    
    private Chat chat;
    private LimitedTextField textField;
    private ClientApp client;

    public SendClickEvent(Chat chat, LimitedTextField textField, ClientApp client){
        this.chat = chat;
        this.textField = textField;
        this.client = client;
    }

    public void handle(MouseEvent event){
        if(this.textField.getText().isEmpty())
            return;
       this.chat.addMessage(Message.sentMessage(this.textField.getText()));
       this.client.sendMessage(this.chat.getContactUUID(), this.textField.getText());
       this.textField.clear();
    }
} 
