package com.example.demo;

import com.example.demo.domain.model.Cookbook;
import com.example.demo.domain.repository.CookbookRepository;
import com.example.demo.domain.repository.ProfileRepository;
import com.example.demo.domain.service.CookbookService;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.CookbookServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CookbookServiceImpTest {
    @MockBean
    private CookbookRepository cookbookRepository;

    @MockBean
    private ProfileRepository profileRepository;

    @Autowired
    private CookbookService cookbookService;

    @TestConfiguration
    static class CookbookServiceImplTestConfiguration {
        @Bean
        public CookbookService cookbookService() { return new CookbookServiceImpl(); }
    }

    @Test
    @DisplayName("When getCookbook0ByName With Invalid Name Then Returns Resource Not Found Exception")
    public void whenGetCookbook0ByNameWithInvalidNameThenReturnsResourceNotFoundException() {
        //Arrange
        String name = "Example";
        String template = "Resource %s nor found for %s with value %s";
        when(cookbookRepository.findByName(name)).thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Cookbook", "Name", name);

        //Act
        Throwable exception = catchThrowable(()->{
            Cookbook foundCookbook = cookbookService.getCookbookByName(name);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("When getCookbookByName With Valid Name Then Returns Cookbook")
    public void whenGetCookbookByNameWithValidNameThenReturnsCookbook() {
        //Arrange
        String name = "Para fiestas";
        Cookbook cookbook = new Cookbook().setId(1L).setName(name);

        when(cookbookRepository.findByName(name)).thenReturn(Optional.of(cookbook));

        //Act
        Cookbook foundCookbook = cookbookService.getCookbookByName(name);

        //Assert
        assertThat(foundCookbook.getName()).isEqualTo(name);
    }
}
