package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.IconCategory;
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
    private IconCategoryRepository iconCategoryRepository;

    @Test
    public void IconCategoriesRepository_Save_ReturnSaved() {
        IconCategory iconCategory = IconCategory.builder().name("abc").build();

        iconCategoryRepository.save(iconCategory);

        List<IconCategory> iconCategoryList = iconCategoryRepository.findAll();

        Assertions.assertThat(iconCategoryList).isNotEmpty();
    }

    @Test
    public void IconCategoriesRepository_Delete_ReturnEmptyList() {
        IconCategory iconCategory = IconCategory.builder().name("abc").build();

        iconCategoryRepository.save(iconCategory);

        iconCategoryRepository.deleteAll();

        List<IconCategory> iconCategoryList = iconCategoryRepository.findAll();

        Assertions.assertThat(iconCategoryList).isEmpty();
    }
}
