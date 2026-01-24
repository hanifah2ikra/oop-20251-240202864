package com.upb.agripos;

import com.upb.agripos.model.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    void testProduct() {
        Product p = new Product(1, "Alat pertanian", 150000);

        assertEquals(1, p.getId());
        assertEquals("Alat pertanian", p.getName());
        assertEquals(150000, p.getPrice());
    }
}
