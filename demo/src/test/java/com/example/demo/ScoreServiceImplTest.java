package com.example.demo;

import com.example.demo.domain.model.Score;
import com.example.demo.domain.repository.ProfileRepository;
import com.example.demo.domain.repository.RecipeRepository;
import com.example.demo.domain.repository.ScoreRepository;
import com.example.demo.domain.service.ScoreService;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.ScoreServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ScoreServiceImplTest {
    @MockBean
    private ScoreRepository scoreRepository;

    @MockBean
    private RecipeRepository recipeRepository;

    @MockBean
    private ProfileRepository profileRepository;

    @Autowired
    private ScoreService scoreService;

    @TestConfiguration
    static class ScoreServiceImplTestConfiguration {
        @Bean
        public ScoreService scoreService() {
            return new ScoreServiceImpl();
        }
    }

    @Test
    @DisplayName("When Get Score By Score Id With Valid Id Then Returns Score")
    public void whenGetScoreByScoreIdWithValidIdThenReturnsScore() {
        // Arrange
        Long id = 1000L;
        Score score = new Score();
        score.setId(id);
        when(scoreRepository.findById(id))
                .thenReturn(Optional.of(score));

        // Act
        Score foundScore = scoreService.getScoreById(id);

        // Assert
        assertThat(foundScore.getId()).isEqualTo(id);

    }

    @Test
    @DisplayName("When Get Score By Score Id With Invalid Id The ReturnsException")
    public void WhenGetScoreByScoreIdWithInvalidIdTheReturnsException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s nor found for %s with value %s";
        when(scoreRepository.findById(id))
                .thenReturn(Optional.empty());

        String expectedMessage = String.format(template, "Score", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            Score score = scoreService.getScoreById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }


}
