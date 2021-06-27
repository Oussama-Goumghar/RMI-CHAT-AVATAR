package Client.Gui.Utils;

import Client.Gui.Utils.LimitedTextField;
import Client.Gui.Screen.Chat;
import Client.Logic.ClientApp;
import Client.Utils.Message;
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
