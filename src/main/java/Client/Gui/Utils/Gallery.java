package Client.Gui.Utils;

import Client.Gui.Utils.PathImage;

import java.util.LinkedList;
import java.util.ListIterator;

public class Gallery {
    
    private final String path = "image/avatar/";
    private PathImage image;
    private LinkedList <String> gallery;
    private ListIterator <String> iterator;

    private void initGallery(){
        this.gallery.add("01.jpg");
        this.gallery.add("02.jpg");
        this.gallery.add("03.jpg");
        this.gallery.add("04.jpg");
    }
    public Gallery(){
        this.gallery = new LinkedList <String> ();
        initGallery();
        this.iterator = gallery.listIterator(0);
        this.image = new PathImage(path + gallery.get(0));
    }
    
    public PathImage nextImage(){
        if(!this.iterator.hasNext())
            return this.image;    
        return this.image = new PathImage(path + this.iterator.next());
    }
    
    public PathImage prevImage(){
        if(!this.iterator.hasPrevious())
            return this.image;
        return this.image = new PathImage(path + this.iterator.previous());
    }
    
    public PathImage getImage(){
        return this.image;
    }
}
