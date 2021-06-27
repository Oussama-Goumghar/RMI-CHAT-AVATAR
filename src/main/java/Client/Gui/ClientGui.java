package Client.Gui;

import Client.Gui.Screen.Chat;
import Client.Gui.Screen.Login;
import Client.Gui.Utils.SubmitEvent;
import Client.Logic.ClientApp;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientGui extends Application {

    private static ClientApp client;

    public void setClient(ClientApp client){
        this.client = client;
    }

    public void changeScene(Stage stage, Login login, Chat chat){
        login.getSubmitButton().setOnMouseClicked(new SubmitEvent(stage, login, chat, client));
    }

    @Override
    public void start(Stage stage){
        Login login = new Login(this.client);
        Chat chat = new Chat(this.client);
        this.client.setChat(chat);

        changeScene(stage, login, chat);
        stage.setTitle("Chat");
        stage.setScene(login.getLoginScene());
        stage.setResizable(false);
        stage.show();
    }

    public static void init(ClientApp client){
        ClientGui gui = new ClientGui();
        gui.setClient(client);
        gui.launch();
    }    
}
