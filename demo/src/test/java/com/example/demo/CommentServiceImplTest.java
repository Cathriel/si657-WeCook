package com.example.demo;

import com.example.demo.domain.model.Comment;
import com.example.demo.domain.model.Score;
import com.example.demo.domain.repository.CommentRepository;
import com.example.demo.domain.repository.ProfileRepository;
import com.example.demo.domain.repository.RecipeRepository;
import com.example.demo.domain.service.CommentService;
import com.example.demo.domain.service.ScoreService;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.CommentServiceImpl;
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
public class CommentServiceImplTest {
    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private RecipeRepository recipeRepository;

    @MockBean
    private ProfileRepository profileRepository;

    @Autowired
    private CommentService commentService;

    @TestConfiguration
    static class CommentServiceImplTestConfiguration {
        @Bean
        public CommentService commentService() {
            return new CommentServiceImpl();
        }
    }

    @Test
    @DisplayName("When Get Comment By Comment Id With Valid Id Then Returns Comment")
    public void whenGetCommentByCommentIdWithValidIdThenReturnsComment() {
        // Arrange
        Long id = 1000L;
        Comment comment = new Comment();
        comment.setId(id);
        when(commentRepository.findById(id))
                .thenReturn(Optional.of(comment));

        // Act
        Comment foundComment = commentService.getCommentById(id);

        // Assert
        assertThat(foundComment.getId()).isEqualTo(id);

    }

    @Test
    @DisplayName("When Get Comment By Comment Id With Invalid Id The ReturnsException")
    public void WhenGetCommentByCommentIdWithInvalidIdTheReturnsException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s nor found for %s with value %s";
        when(commentRepository.findById(id))
                .thenReturn(Optional.empty());

        String expectedMessage = String.format(template, "Comment", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            Comment comment = commentService.getCommentById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}