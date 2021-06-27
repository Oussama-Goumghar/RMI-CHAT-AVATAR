package Client.Gui.Utils;

import Client.Gui.Screen.Chat;
import Client.Gui.Screen.Login;
import Client.Logic.ClientApp;
import Client.Network.ClientRMI;

import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class SubmitEvent implements EventHandler <MouseEvent>{
    
    private Stage stage;
    private Login login;
    private Chat chat;
    private ClientApp client;

    public SubmitEvent(Stage stage, Login login, Chat chat, ClientApp client){
        this.stage = stage;
        this.login = login;
        this.chat = chat;
        this.client = client;
    }

    public void handle(MouseEvent event) {
           this.login.registerUser();
           ClientRMI.init(client);
           client.getFromServerOnlineContact();
           client.getOnlineContact().forEach(user ->{
               chat.addContact(user); 
           });
           this.stage.setScene(this.chat.getChatScene()); 
    }
}
