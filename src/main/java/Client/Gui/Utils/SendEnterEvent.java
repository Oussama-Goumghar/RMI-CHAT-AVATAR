package Client.Gui.Utils;

import Client.Gui.Utils.LimitedTextField;
import Client.Gui.Screen.Chat;
import Client.Logic.ClientApp;
import Client.Utils.Message;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;

public class SendEnterEvent implements EventHandler <KeyEvent>{
    
    private Chat chat;
    private LimitedTextField textField;
    private ClientApp client;

    public SendEnterEvent(Chat chat, LimitedTextField textField, ClientApp client){
        this.chat = chat;
        this.textField = textField;
        this.client = client;
    }

    public void handle(KeyEvent key){
        if(key.getCode() != KeyCode.ENTER || this.textField.getText().isEmpty()) 
            return;
        this.chat.addMessage(Message.sentMessage(this.textField.getText()));
        this.client.sendMessage(this.chat.getContactUUID(), this.textField.getText());
        this.textField.clear();
    }
} 
