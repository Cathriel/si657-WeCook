package com.example.demo;

import com.example.demo.domain.model.Comment;
import com.example.demo.domain.model.Multimedia;
import com.example.demo.domain.repository.MultimediaRepository;
import com.example.demo.domain.repository.ProfileRepository;
import com.example.demo.domain.repository.RecipeRepository;
import com.example.demo.domain.service.MultimediaService;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.MultimediaServiceImpl;
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
public class MultimediaServiceImplTest {

    @MockBean
    private MultimediaRepository multimediaRepository;

    @MockBean
    private RecipeRepository recipeRepository;

    @Autowired
    private MultimediaService multimediaService;

    @TestConfiguration
    static class MultimediaServiceImplTestConfiguration {
        @Bean
        public MultimediaService multimediaService(){
            return new MultimediaServiceImpl();
        }
    }

    @Test
    @DisplayName("When getMultimediaById With Valid Id Then Returns Multimedia")
    public void whenGetMultimediaByIdWithValidIdThenReturnsMultimedia(){

        // Arrange
        Long id = 1L;
        Multimedia multimedia = new Multimedia();
        multimedia.setId(id);
        when(multimediaRepository.findById(id))
                .thenReturn(Optional.of(multimedia));

        // Act
        Multimedia foundMultimedia = multimediaService.getMultimediaById(id);

        // Assert
        Assertions.assertThat(foundMultimedia.getId()).isEqualTo(id);


    }

    @Test
    @DisplayName("When getMultimediaById With Invalid Id Then Returns Resource Not Found Exception")
    public void whenGetMultimediaByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        Long id= 1L;
        String template="Resource %s nor found for %s with value %s";
        when(multimediaRepository.findById(id)).thenReturn(Optional.empty());
        String expectedMessage=String.format(template,"Multimedia","Id",id);

        //Act
        Throwable exception= catchThrowable(()->{
            Multimedia foundMultimedia=multimediaService.getMultimediaById(id);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }

}
