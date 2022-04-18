package com.example.demo;

import com.example.demo.domain.model.Multimedia;
import com.example.demo.domain.model.Recipe;
import com.example.demo.domain.repository.*;
import com.example.demo.domain.service.RecipeService;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.RecipeServiceImpl;
import org.assertj.core.api.Assertions;
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
public class RecipeServiceImplTest {
    @MockBean
    private RecipeRepository recipeRepository;

    @MockBean
    private TagRepository tagRepository;

    @MockBean
    private MultimediaRepository multimediaRepository;

    @MockBean
    private IngredientRepository ingredientRepository;

    @MockBean
    private CookbookRepository cookbookRepository;

    @MockBean
    private ScoreRepository scoreRepository;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private ProfileRepository profileRepository;


    @Autowired
    private RecipeService recipeService;

    @TestConfiguration
    static class RecipeServiceImplTestConfiguration {
        @Bean
        public RecipeService recipeService(){
            return new RecipeServiceImpl();
        }
    }

    @Test
    @DisplayName("When getRecipeById With Valid Id Then Returns Recipe")
    public void whenGetRecipeByIdWithValidIdThenReturnsRecipe(){

        //Arrange
        Long id= 1L;
        Recipe recipe = new Recipe();
        recipe.setId(id);


        when(recipeRepository.findById(id)).thenReturn(Optional.of(recipe));

        //Act
        Recipe foundRecipe=recipeService.getRecipeById(id);

        //Assert
        Assertions.assertThat(foundRecipe.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("When getRecipeById With Invalid Id Then Returns Resource Not Found Exception")
    public void whenGetRecipeByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        Long id= 1L;
        String template="Resource %s nor found for %s with value %s";
        when(recipeRepository.findById(id)).thenReturn(Optional.empty());
        String expectedMessage=String.format(template,"Recipe","Id",id);

        //Act
        Throwable exception= catchThrowable(()->{
            Recipe foundRecipe=recipeService.getRecipeById(id);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }
}
