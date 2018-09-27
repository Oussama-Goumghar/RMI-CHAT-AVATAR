package Gui.Utils;

import Gui.Screen.Chat;
import Logic.ClientApp;
import Common.User;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class ClickAvatarEvent implements EventHandler <MouseEvent>{
    
    private Chat chat;
    private ClientApp client;
    private User user;

    public ClickAvatarEvent(Chat chat, ClientApp client, User user){
        this.chat = chat;
        this.client = client;
        this.user = user;
    }

    public void handle(MouseEvent event){
        this.chat.setContact(user);
        this.chat.clearMessage();
        this.client.getMessage().get(user.getUUID()).forEach(message ->
                this.chat.addMessage(message));
        this.chat.setMessagble(true);
    }
} 
