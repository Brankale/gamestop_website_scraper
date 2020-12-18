package test.models;

import main.models.price.Price;
import main.models.price.PriceType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
    public void cantPassNullPrice() {
        assertThrows(Price.PriceInitException.class, () -> {
            PriceType type = PriceType.NEW;
            new Price(null, type);
        });
    }

    @Test
    public void cantPassNullPriceType() {
        assertThrows(Price.PriceInitException.class, () -> {
            BigDecimal _price = new BigDecimal("9.99");
            new Price(_price, null);
        });
    }

    @Test
    public void setAvailableTrue() {
        price.setAvailable(true);
        assertTrue(price.isAvailable());
    }

    @Test
    public void setAvailableFalse() {
        price.setAvailable(false);
        assertFalse(price.isAvailable());
    }

    @Test
    public void setHomeDeliveryTrue() {
        price.setHomeDeliveryAvailability(true);
        assertTrue(price.isHomeDeliveryAvailable());
    }

    @Test
    public void setHomeDeliveryFalse() {
        price.setHomeDeliveryAvailability(false);
        assertFalse(price.isHomeDeliveryAvailable());
    }

    @Test
    public void setCanCollectInStoreTrue() {
        price.setCanCollectInStore(true);
        assertTrue(price.canCollectInStore());
    }

    @Test
    public void setCanCollectInStoreFalse() {
        price.setCanCollectInStore(false);
        assertFalse(price.canCollectInStore());
    }

}