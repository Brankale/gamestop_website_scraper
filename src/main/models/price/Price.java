package main.models.price;

import java.math.BigDecimal;

public class Price {

    private final BigDecimal price;
    private final PriceType type;
    private final BigDecimal discountedPrice;
    private final boolean available;
    private final boolean homeDelivery;
    private final boolean collectibleInStore;

    public static class Builder {

        private final BigDecimal price;
        private final PriceType type;

        // optionals
        private BigDecimal discountedPrice;
        private boolean available;
        private boolean homeDelivery;
        private boolean collectibleInStore;

        public Builder(BigDecimal price, PriceType type) {
            this.price = price;
            this.type = type;
        }

        public Builder setDiscountedPrice(BigDecimal discountedPrice) {
            this.discountedPrice = discountedPrice;
            return this;
        }

        public Builder setAvailability(boolean available) {
            this.available = available;
            return this;
        }

        public Builder setHomeDelivery(boolean homeDelivery) {
            this.homeDelivery = homeDelivery;
            return this;
        }

        public Builder setCollectibleInStore(boolean collectibleInStore) {
            this.collectibleInStore = collectibleInStore;
            return this;
        }

        public Price build() {
            return new Price(this);
        }

    }

    private Price(Builder builder) {
        this.price = builder.price;
        this.type = builder.type;
        this.discountedPrice = builder.discountedPrice;
        this.available = builder.available;
        this.homeDelivery = builder.homeDelivery;
        this.collectibleInStore = builder.collectibleInStore;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public PriceType getType() {
        return type;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean hasHomeDelivery() {
        return homeDelivery;
    }

    public boolean isCollectibleInStore() {
        return collectibleInStore;
    }

}
