package Client.Gui.Screen;

import Client.Logic.ClientApp;
import Client.Gui.Utils.Gallery;
import Client.Gui.Utils.LimitedTextField;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;

public class Login extends BorderPane{

    private ClientApp client;
    private Button submit; 
    private Gallery gallery;
    private LimitedTextField userField;
    private LimitedTextField quoteField;

    public Scene getLoginScene(){
        return new Scene(this, 800, 500);
    }
    
    public Button getSubmitButton(){
        return this.submit;
    }

    public void registerUser(){
        this.client.getUser()
            .registerUser(this.gallery.getImage().getPath(),
                    this.userField.getText(), 
                    this.quoteField.getText());
    }

    public Login(ClientApp client){
        super();
        this.client = client;
        this.getStylesheets().add(this.getClass().getResource("/css/utils.css").toExternalForm());
        this.setId("borderPane");

        GridPane grid = new GridPane();
        grid.setId("grid-pane");

        ImageView avatar = new ImageView();
        this.gallery = new Gallery();
        avatar.setId("image");
        avatar.setImage(this.gallery.getImage());

        Label title = new Label("Login");
        Label userName = new Label("UserName");
        Label quote = new Label("Quote");
        title.setId("title-label");
        userName.setId("label");
        quote.setId("label");

        this.userField = new LimitedTextField(15);
        this.quoteField = new LimitedTextField(20);
        this.userField.setId("text-field");
        this.quoteField.setId("text-field");

        Button right = new Button();
        right.setOnMouseClicked(click -> avatar.setImage(gallery.nextImage()));
        right.setId("buttonRight");
        Button left = new Button();
        left.setId("buttonLeft");
        left.setOnMouseClicked(click -> avatar.setImage(gallery.prevImage()));

        HBox hbox = new HBox();
        hbox.getChildren().addAll(left, right);
        hbox.setId("hbox");
               
        this.submit = new Button();
        this.submit.setId("buttonSubmit");
 
        grid.add(title, 0, 0);
        grid.add(avatar, 0, 1, 1, 5);
        grid.add(userName, 1, 1);
        grid.add(userField, 1, 2);
        grid.add(quote, 1, 3);
        grid.add(quoteField, 1, 4);
        grid.add(hbox, 0, 6);

        this.setCenter(grid);
        this.setBottom(submit);
    }
}
