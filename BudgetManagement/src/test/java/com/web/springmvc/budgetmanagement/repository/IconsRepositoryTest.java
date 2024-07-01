package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.IconCategories;
import com.web.springmvc.budgetmanagement.model.Icons;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class IconsRepositoryTest {
    @Autowired
    private IconsRepository iconsRepository;
    @Autowired
    private IconCategoriesRepository iconCategoriesRepository;
    @Test
    public void IconsRepository_FindAll_ReturnAllIcon() {
        List<Icons> icon = iconsRepository.findAll();

        Assertions.assertThat(icon).isNotNull();
    }
}
