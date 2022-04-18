package com.example.demo;

import com.example.demo.domain.model.Ingredient;
import com.example.demo.domain.repository.IngredientRepository;
import com.example.demo.domain.repository.RecipeRepository;
import com.example.demo.domain.service.IngredientService;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.IngredientServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class IngredientServiceImplTest {

    @MockBean
    private IngredientRepository ingredientRepository;

    @MockBean
    private RecipeRepository recipeRepository;


    @Autowired
    private IngredientService ingredientService;

    @TestConfiguration
    static class IngredientServiceImplTestConfiguration {
        @Bean
        public IngredientService ingredientService() {
            return new IngredientServiceImpl();
        }
    }

    @Test
    @DisplayName("When getIngredientByName With Valid Name Then Returns Ingredient")
    public void whenGetIngredientByNameWithValidNameThenReturnsName() {
        //Arrange
        String name = "Jengibre";
        Ingredient ingredient = new Ingredient().setId(1L).setName(name);

        //ingredientRepository.save(ingredient);

        when(ingredientRepository.findByName(name)).thenReturn(Optional.of(ingredient));

        //Act
        Ingredient foundIngredient = ingredientService.getIngredientByName(name);

        //Assert
        assertThat(foundIngredient.getName()).isEqualTo(name);

    }

    @Test
    @DisplayName("When getIngredientByName With Invalid Name Then Returns Resource Not Found Exception")
    public void whenGetIngredientByNameWithInvalidNameThenReturnsResourceNotFoundException() {
        //Arrange
        String name = "String";
        String template ="Resource %s nor found for %s with value %s";
        when(ingredientRepository.findByName(name)).thenReturn(Optional.empty());
        String expectedMessage = String.format(template,"Ingredient","Name",name);

        //Act
        Throwable exception= catchThrowable(()->{
            Ingredient foundIngredient = ingredientService.getIngredientByName(name);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}
