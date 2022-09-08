package nav.categories.recycleview;

import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Post {

    private String bathrooms;
    private String beds;
    private String country;
    private String image;
    private String price;
    private String rooms;





    private String uuid;


    public Post(){

    }

    public Post(String bathrooms, String beds, String country, String image, String price, String rooms, String uuid){
        this.bathrooms = bathrooms;
        this.beds = beds;
        this.country = country;
        this.image = image;
        this.price = price;
        this.rooms = rooms;
        this.uuid = uuid;

    }

    // Getter

    public String getBathrooms() {
        return bathrooms;
    }

    public String getBeds() {
        return beds;
    }

    public String getCountry() {return country;}

    public String getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getRooms() {
        return rooms;
    }

    public String getUuid() {return uuid;}

    // Setter

    public void setBathrooms(String bathrooms) {
        this.bathrooms = bathrooms;
    }

    public void setBeds(String beds) {
        this.beds = beds;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public void setUuid(String uuid) {this.uuid = uuid;}
}
