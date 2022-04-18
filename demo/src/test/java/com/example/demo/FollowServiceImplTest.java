package com.example.demo;

import com.example.demo.domain.model.Follow;
import com.example.demo.domain.model.Profile;
import com.example.demo.domain.repository.FollowRepository;
import com.example.demo.domain.repository.ProfileRepository;
import com.example.demo.domain.service.FollowService;
import com.example.demo.domain.service.ProfileService;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.FollowServiceImpl;
import com.example.demo.service.ProfileServiceImpl;
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
public class FollowServiceImplTest {

    @MockBean
    private FollowRepository followRepository;

    @MockBean
    private ProfileRepository profileRepository;

    @Autowired
    private FollowService followService;

    @TestConfiguration
    static class FollowServiceImplTestConfiguration{
        @Bean
        public FollowService followService(){return new FollowServiceImpl();
        }
    }

    @Test
    @DisplayName("When getFollowById With Valid Id Then Returns Follow")
    public void whenGetFollowByIdWithValidIdThenReturnsFollow(){
        //Arrange
        String template="Resource %s nor found for %s with value %s";
        Follow follow = new Follow().setId(1L);
        when(followRepository.findById(1L)).thenReturn(Optional.of(follow));

        //Act
        Follow foundFollow=followService.getFollowById(1L);


        //Assert
        assertThat(follow).isEqualTo(foundFollow);
    }

    @Test
    @DisplayName("When getFollowById With Invalid Id Then Returns Message")
    public void whenGetFollowByIdWithValidIdThenReturnsMessage(){
        //Arrange
        String template="Resource %s nor found for %s with value %s";
        Follow follow = new Follow().setId(1L);
        when(followRepository.findById(1L)).thenReturn(Optional.empty());
        String expectedMessage= String.format(template,"Follow","Id",1L);

        //Act
        Throwable exception= catchThrowable(()->{
            Follow foundFollow=followService.getFollowById(1L);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
