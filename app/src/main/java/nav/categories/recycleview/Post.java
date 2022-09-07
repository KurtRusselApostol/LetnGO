package nav.categories.recycleview;

import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Post {
    private int bathrooms;
    private int beds;
    private String country;
    private Uri imageUri;
    private String price;
    private int rooms;
    private String user;

    public Post(){

    }

    public Post(int bathrooms, int beds, String country, Uri imageUri, String price, int rooms, String user){
        this.bathrooms = bathrooms;
        this.beds = beds;
        this.country = country;
        this.imageUri = imageUri;
        this.price = price;
        this.rooms = rooms;
        this.user = user;

    }

    public int getBathrooms() {
        return bathrooms;
    }

    public int getBeds() {
        return beds;
    }

    public String getCountry() {
        return country;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public String getPrice() {
        return price;
    }

    public int getRooms() {
        return rooms;
    }

    public String getUser() {
        return user;
    }
}
