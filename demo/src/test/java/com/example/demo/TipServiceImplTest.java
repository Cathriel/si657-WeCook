package com.example.demo;

import com.example.demo.domain.model.Recipe;
import com.example.demo.domain.model.Tag;
import com.example.demo.domain.model.Tip;
import com.example.demo.domain.repository.*;
import com.example.demo.domain.service.TagService;
import com.example.demo.domain.service.TipService;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.TagServiceImpl;
import com.example.demo.service.TipServiceImpl;
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
public class TipServiceImplTest {

    @MockBean
    private TipRepository tipRepository;

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
    private TipService tipService;

    @TestConfiguration
    static class TipServiceImplTestConfiguration {
        @Bean
        public TipService tipService() { return new TipServiceImpl(); }
    }

    @Test
    @DisplayName("When getTipById With Valid Id Then Returns Recipe")
    public void whenGetTipByIdWithValidIdThenReturnsRecipe(){

        //Arrange
        Long id= 1L;
        Tip tip = new Tip();
        tip.setId(id);


        when(tipRepository.findById(id)).thenReturn(Optional.of(tip));

        //Act
        Tip foundTip= tipService.getTipById(id);

        //Assert
        Assertions.assertThat(foundTip.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("When getTipById With Invalid Id Then Returns Resource Not Found Exception")
    public void whenGetTipByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        Long id= 1L;
        String template="Resource %s nor found for %s with value %s";
        when(tipRepository.findById(id)).thenReturn(Optional.empty());
        String expectedMessage=String.format(template,"Tip","Id",id);

        //Act
        Throwable exception= catchThrowable(()->{
            Tip foundTip=tipService.getTipById(id);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }
}
