package main.models;

import java.util.ArrayList;

public class Price {

    public enum PriceType {
        NEW,
        USED,
        PREORDER,
        DIGITAL
    }

    private final PriceType type;
    private final float price;
    private final ArrayList<Float> oldPrices;
    private boolean available;
    private boolean homeDelivery;
    private boolean pickUpInStore;

    public Price(PriceType type, float price, ArrayList<Float> oldPrices) {
        this.type = type;
        this.price = price;
        this.oldPrices = oldPrices;
    }

    public PriceType getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public ArrayList<Float> getOldPrices() {
        return oldPrices;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isHomeDeliveryAvailable() {
        return homeDelivery;
    }

    public void setHomeDeliveryAvailability(boolean homeDelivery) {
        this.homeDelivery = homeDelivery;
    }

    public boolean isPickUpInStoreAvailable() {
        return pickUpInStore;
    }

    public void setPickUpInStoreAvailability(boolean pickUpInStore) {
        this.pickUpInStore = pickUpInStore;
    }

}
