package Client.Gui.Screen;

import Common.User;
import Client.Logic.ClientApp;
import Client.Utils.Message;
import Client.Gui.Utils.PathImage;
import Client.Gui.Utils.LimitedTextField;
import Client.Gui.Utils.UpdateOnlineTask;
import Client.Gui.Utils.UpdateMessageTask;
import Client.Gui.Utils.SendClickEvent;
import Client.Gui.Utils.SendEnterEvent;
import Client.Gui.Utils.ClickAvatarEvent;
import javafx.scene.image.ImageView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.application.Platform;
import java.util.UUID;

public class Chat extends BorderPane{
    
    private ClientApp client;
    private ImageView imageContact;
    private Label nameContact;
    private Label quoteContact;
    private VBox messageArea;
    private VBox contactArea;
    private Button sendButton;
    private LimitedTextField text;
    private UUID contact;

    public Scene getChatScene(){       
        return new Scene(this, 800, 640);
    }

    public void addMessage(Message message){
        Label label = new Label(message.getMessage());
        label.setId(message.getCss());
        this.messageArea.getChildren().add(label);
    }

    public void clearMessage(){
        this.messageArea.getChildren().clear();
    }

    public void setMessagble(boolean flag){ 
        this.text.clear();
        this.text.setEditable(flag);
    }

    public UUID getContactUUID(){
        return this.contact;
    }

    public void addContact(User user){
        GridPane grid = new GridPane();
        ImageView image = new ImageView(user.getImage());
        Label label = new Label(user.getName());
        grid.setId("contact-grid");
        image.setId("contact-image");
        label.setId("contact-text");
        grid.setOnMouseClicked(new ClickAvatarEvent(this, this.client, user));
        grid.add(label, 0, 0);
        grid.add(image, 0, 1);
        this.contactArea.getChildren().add(grid);
    }

    public void setContact(User user){
        this.imageContact.setImage(new PathImage(user.getImage()));
        this.nameContact.setText(user.getName());
        this.quoteContact.setText(user.getQuote());
        this.contact = user.getUUID();
    }

    public void updateOnlineContact(User user){
        Platform.runLater(new UpdateOnlineTask(this, user));
    }

    public void updateMessageReceived(String message){
        Platform.runLater(new UpdateMessageTask(this, message));
    }

    public Chat(ClientApp client) {
        super();
        this.client = client; 
        this.getStylesheets().add(this.getClass().getResource("/css/utils.css").toExternalForm());
    
        BorderPane center = new BorderPane();
        GridPane grid = new GridPane();
        imageContact = new ImageView();
        nameContact  = new Label();
        quoteContact = new Label();        
        center.setId("sendbox-background"); 
        grid.setId("chat-top");
        imageContact.setId("avatar-top");
        nameContact.setId("name-top");
        quoteContact.setId("quote-top");
        
        grid.add(imageContact, 0, 0, 1, 3);
        grid.add(nameContact, 1, 0);
        grid.add(quoteContact, 1, 1);
        //grid.setGridLinesVisible(true);

        ScrollPane messageBox = new ScrollPane();
        messageArea = new VBox();
        messageBox.setContent(messageArea);    
        messageBox.setId("message-scroll-pane");

        ScrollPane contactBox = new ScrollPane();
        contactArea = new VBox();
        contactBox.setContent(contactArea);
        contactBox.setId("contact-scroll-pane");


        HBox sendBox = new HBox();
        sendButton = new Button();
        text = new LimitedTextField(32);
        sendBox.getChildren().addAll(text, sendButton);
        text.setOnKeyPressed(new SendEnterEvent(this, text, this.client));
        text.setId("send-text");
        text.setEditable(false);
        sendButton.setId("send-button");
        sendButton.setOnMouseClicked(new SendClickEvent(this, text, this.client));
        
        center.setTop(grid);
        center.setCenter(messageBox);
        center.setBottom(sendBox);

        this.setCenter(center);
        this.setLeft(contactBox);
    } 
}
