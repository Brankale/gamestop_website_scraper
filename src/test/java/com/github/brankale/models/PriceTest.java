package com.github.brankale.models;

import com.github.brankale.models.price.Price;
import com.github.brankale.models.price.PriceType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    private static final BigDecimal _price = new BigDecimal("9.99");
    private static final PriceType _type = PriceType.NEW;

    @Test
    public void getPrice() {
        Price price = new Price.Builder(_price, _type).build();
        assertEquals(price.getPrice(), _price);
    }

    @Test
    public void setAvailable() {
        Price price = new Price.Builder(_price, _type)
                .setAvailability(true)
                .build();
        assertTrue(price.isAvailable());


        price = new Price.Builder(_price, _type)
                .setAvailability(false)
                .build();
        assertFalse(price.isAvailable());
    }

    @Test
    public void setHomeDelivery() {
        Price price = new Price.Builder(_price, _type)
                .setHomeDelivery(true)
                .build();
        assertTrue(price.hasHomeDelivery());

        price = new Price.Builder(_price, _type)
                .setHomeDelivery(false)
                .build();
        assertFalse(price.hasHomeDelivery());
    }

    @Test
    public void setCanCollectInStore() {
        Price price = new Price.Builder(_price, _type)
                .setCollectibleInStore(true)
                .build();
        assertTrue(price.isCollectibleInStore());

        price = new Price.Builder(_price, _type)
                .setCollectibleInStore(false)
                .build();
        assertFalse(price.isCollectibleInStore());
    }

    @Test
    public void addOldPrice() {
        Price price = new Price.Builder(_price, PriceType.NEW)
                .setDiscountedPrice(BigDecimal.valueOf(20))
                .build();

        assertEquals(price.getDiscountedPrice(), BigDecimal.valueOf(20));
    }

    @Test
    public void getPriceType() {
        Price price;

        price = new Price.Builder(_price, PriceType.NEW).build();
        assertEquals(price.getType(), PriceType.NEW);

        price = new Price.Builder(_price, PriceType.USED).build();
        assertEquals(price.getType(), PriceType.USED);

        price = new Price.Builder(_price, PriceType.PREORDER).build();
        assertEquals(price.getType(), PriceType.PREORDER);

        price = new Price.Builder(_price, PriceType.DIGITAL).build();
        assertEquals(price.getType(), PriceType.DIGITAL);
    }

}