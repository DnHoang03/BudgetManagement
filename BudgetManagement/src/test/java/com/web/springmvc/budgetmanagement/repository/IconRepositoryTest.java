package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.Icon;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class IconRepositoryTest {
    @Autowired
    private IconRepository iconRepository;
    @Autowired
    private IconCategoryRepository iconCategoryRepository;
    @Test
    public void IconsRepository_FindAll_ReturnAllIcon() {
        List<Icon> icon = iconRepository.findAll();

        Assertions.assertThat(icon).isNotNull();
    }
}
