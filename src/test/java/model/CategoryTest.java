package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void test01_toString() {
        // Arrange
        Category category = new Category("Business");
        category.setId(1L);


        // Act


        // Assert
        final String expected = "1: Business";
        assertThat(category.toString()).isEqualTo(expected);
    }


}