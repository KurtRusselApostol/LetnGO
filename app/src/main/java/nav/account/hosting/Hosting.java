package nav.account.hosting;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Hosting {
    private String  PlaceTitle;
    private String Description;
    private String Highlights;
    private String Offer_Description;
    private String Others;
    private String Price;
    private String PriceDiscount;

    public Hosting() {
    }

    public Hosting(String placeTitle, String description, String highlights, String offer_Description, String others, String price, String priceDiscount) {
        PlaceTitle = placeTitle;
        Description = description;
        Highlights = highlights;
        Offer_Description = offer_Description;
        Others = others;
        Price = price;
        PriceDiscount = priceDiscount;
    }

    public String getPlaceTitle() {
        return PlaceTitle;
    }

    public String getDescription() {
        return Description;
    }

    public String getHighlights() {
        return Highlights;
    }

    public String getOffer_Description() {
        return Offer_Description;
    }

    public String getOthers() {
        return Others;
    }

    public String getPrice() {
        return Price;
    }

    public String getPriceDiscount() {
        return PriceDiscount;
    }
}

