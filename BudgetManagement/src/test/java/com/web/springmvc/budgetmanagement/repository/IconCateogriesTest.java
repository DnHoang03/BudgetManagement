package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.IconCategories;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class IconCateogriesTest {
    @Autowired
    private IconCategoriesRepository iconCategoriesRepository;

    @Test
    public void IconCategoriesRepository_Save_ReturnSaved() {
        IconCategories iconCategories = IconCategories.builder().name("abc").build();

        iconCategoriesRepository.save(iconCategories);

        List<IconCategories> iconCategoriesList = iconCategoriesRepository.findAll();

        Assertions.assertThat(iconCategoriesList).isNotEmpty();
    }

    @Test
    public void IconCategoriesRepository_Delete_ReturnEmptyList() {
        IconCategories iconCategories = IconCategories.builder().name("abc").build();

        iconCategoriesRepository.save(iconCategories);

        iconCategoriesRepository.deleteAll();

        List<IconCategories> iconCategoriesList = iconCategoriesRepository.findAll();

        Assertions.assertThat(iconCategoriesList).isEmpty();
    }
}
