package com.example.demo;

import com.example.demo.domain.model.Subscription;
import com.example.demo.domain.repository.*;
import com.example.demo.domain.service.SubscriptionService;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.SubscriptionServiceImpl;
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
public class SubscriptionServiceImplTest {

    @MockBean
    private SubscriptionRepository subscriptionRepository;

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
    private SubscriptionService subscriptionService;

    @TestConfiguration
    static class SubscriptionServiceImplTestConfiguration {
        @Bean
        public SubscriptionService subscriptionService() { return new SubscriptionServiceImpl();}
    }

    @Test
    @DisplayName("When getSubscriptionById With Valid Id Then Returns Subscription")
    public void whenGetSubscriptionByIdWithValidIdThenReturnsSubscription(){
        //Arrange
        Long id = 1L;
        Subscription subscription = new Subscription();
        subscription.setId(id);

        when(subscriptionRepository.findById(id)).thenReturn(Optional.of(subscription));

        //Act
        Subscription foundSubscription = subscriptionService.getSubscriptionById(id);

        //Assert
        Assertions.assertThat(foundSubscription.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("When getSubscriptionById With Invalid Id Then Returns Resource Not Found Exception")
    public void whenGetSubscriptionByIdWithInvalidIdThenReturnsResourceNotFoundException(){
        //Arrange
        Long id = 1L;
        String template="Resource %s nor found for %s with value %s";
        when(subscriptionRepository.findById(id)).thenReturn(Optional.empty());
        String expectedMessage = String.format(template,"Subscription","Id",id);

        //Act
        Throwable exception = catchThrowable(()->{
           Subscription foundSubscription=subscriptionService.getSubscriptionById(id);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }

}
