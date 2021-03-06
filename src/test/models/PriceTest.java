package test.models;

import main.models.price.Price;
import main.models.price.PriceType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    private static Price price;

    @BeforeAll
    private static void createPriceObject() {
        BigDecimal _price = new BigDecimal("9.99");
        PriceType type = PriceType.NEW;
        price = new Price(_price, type);
    }

    @Test
    public void getPrice() {
        BigDecimal _price = new BigDecimal("9.99");
        PriceType type = PriceType.NEW;
        Price price = new Price(_price, type);
        assertEquals(price.getPrice(), _price);
    }

    @Test
    public void setAvailable() {
        price.setAvailable(true);
        assertTrue(price.isAvailable());
        price.setAvailable(false);
        assertFalse(price.isAvailable());
    }

    @Test
    public void setHomeDelivery() {
        price.setHomeDeliveryAvailability(true);
        assertTrue(price.isHomeDeliveryAvailable());
        price.setHomeDeliveryAvailability(false);
        assertFalse(price.isHomeDeliveryAvailable());
    }

    @Test
    public void setCanCollectInStore() {
        price.setCollectibleInStore(true);
        assertTrue(price.isCollectibleInStore());
        price.setCollectibleInStore(false);
        assertFalse(price.isCollectibleInStore());
    }

    @Test
    public void oldPricesCantBeNull() {
        assertNotNull(price.getOldPrices());
    }

    @Test
    public void addOldPrice() {
        List<BigDecimal> test = new ArrayList<>();
        test.add(BigDecimal.valueOf(10));
        price.addOldPrice(BigDecimal.valueOf(10));
        assertEquals(price.getOldPrices(), test);
    }

    @Test
    public void getPriceType() {
        Price price;
        price = new Price(BigDecimal.valueOf(1), PriceType.NEW);
        assertEquals(price.getType(), PriceType.NEW);
        price = new Price(BigDecimal.valueOf(1), PriceType.USED);
        assertEquals(price.getType(), PriceType.USED);
        price = new Price(BigDecimal.valueOf(1), PriceType.PREORDER);
        assertEquals(price.getType(), PriceType.PREORDER);
        price = new Price(BigDecimal.valueOf(1), PriceType.DIGITAL);
        assertEquals(price.getType(), PriceType.DIGITAL);
    }

}