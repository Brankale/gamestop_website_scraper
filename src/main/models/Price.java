package main.models;

import java.math.BigDecimal;

public class Price {

    private final BigDecimal price;
    private final PriceType type;
    private boolean available;
    private boolean homeDelivery;
    private boolean collectInStore;

    public Price(BigDecimal price, PriceType type) {
        if (price == null)
            throw new PriceInitException();
        if (type == null)
            throw new PriceInitException();

        this.price = price;
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setHomeDeliveryAvailability(boolean available) {
        homeDelivery = available;
    }

    public boolean isHomeDeliveryAvailable() {
        return homeDelivery;
    }

    public void setCanCollectInStore(boolean canCollectInStore) {
        collectInStore = canCollectInStore;
    }

    public boolean canCollectInStore() {
        return collectInStore;
    }

    public static class PriceInitException extends RuntimeException {}

}
