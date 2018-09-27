package Common;

import java.util.UUID;
import java.io.*;

public class User implements Serializable{
	
	private UUID uuid;
    private String name;
    private String quote;
    private String imagePath;

	public User() {	
		this.uuid = UUID.randomUUID();
	}
	
    public void registerUser(String imagePath, String name, String quote){
        this.imagePath = imagePath;
        this.name = (!name.isEmpty())? name : this.uuid.toString().substring(0, 10);    
        this.quote = (!quote.isEmpty())? quote : "Hi! I am Using this Chat!";
    }

	public UUID getUUID(){
		return this.uuid;
	}

    public String getName(){
        return this.name;
    }

    public String getQuote(){
        return this.quote;
    }

    public String getImage(){
        return this.imagePath;
    }
}
