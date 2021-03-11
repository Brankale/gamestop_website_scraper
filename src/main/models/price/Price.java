package main.models.price;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Price {

    private final BigDecimal price;
    private final List<BigDecimal> oldPrices;
    private final PriceType type;
    private boolean available;
    private boolean homeDelivery;
    private boolean collectInStore;
    private Promo promo;

    public Price(@NotNull BigDecimal price, @NotNull PriceType type) {
        this.price = price;
        this.type = type;
        this.oldPrices = new ArrayList<>();
    }

    @NotNull
    public BigDecimal getPrice() {
        return price;
    }

    public void addOldPrice(@NotNull BigDecimal oldPrice) {
        oldPrices.add(oldPrice);
    }

    public void addOldPrices(@NotNull List<BigDecimal> oldPrices) {
        for (BigDecimal oldPrice : oldPrices) {
            addOldPrice(oldPrice);
        }
    }

    @NotNull
    public List<BigDecimal> getOldPrices() {
        return oldPrices;
    }

    @NotNull
    public PriceType getType() {
        return type;
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

    public void setCollectibleInStore(boolean canCollectInStore) {
        collectInStore = canCollectInStore;
    }

    public boolean isCollectibleInStore() {
        return collectInStore;
    }

    @Nullable
    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }

}
