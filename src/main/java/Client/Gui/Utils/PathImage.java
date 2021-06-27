package Client.Gui.Utils;

import javafx.scene.image.Image;

public class PathImage extends Image {

    private final String path;

    public PathImage(String path){
        super(path);
        this.path = path;
    }

    public String getPath(){
        return this.path;
    }
}
