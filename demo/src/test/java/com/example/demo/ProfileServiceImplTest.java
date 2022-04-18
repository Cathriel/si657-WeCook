package com.example.demo;

import com.example.demo.domain.model.Profile;
import com.example.demo.domain.repository.ProfileRepository;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.domain.service.ProfileService;
import com.example.demo.exception.ResourceNotFoundException;
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
public class ProfileServiceImplTest {
    @MockBean
    private ProfileRepository profileRepository;
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ProfileService profileService;

    @TestConfiguration
    static class ProfileServiceImplTestConfiguration{
        @Bean
        public ProfileService profileService(){return new ProfileServiceImpl();
        }
    }

    @Test
    @DisplayName("When getProfileById With Valid Name Then Returns Profile")
    public void whenGetProfileByNameWithValidIdThenReturnsProfile(){
        //Arrange
        String name="Perfil1";
        Profile profile = new Profile().setId(1L).setName(name);
        when(profileRepository.findProfileByName(name)).thenReturn(Optional.of(profile));

        //Act
        Profile profileFound = profileService.getProfileByName(name);

        //Assert
        assertThat(profileFound.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("When getProfileById With Invalid Id Then Returns Message")
    public void whenGetProfileByIdWithInvalidIdThenReturnsMessage(){
        //Arrange
        String name="Perfil1";
        String template="Resource %s nor found for %s with value %s";
        Profile profile = new Profile().setId(1L).setName(name);
        when(profileRepository.findById(1L)).thenReturn(Optional.empty());
        String expectedMessage= String.format(template,"Profile","Id",1L);

        //Act
        Throwable exception= catchThrowable(()->{
            Profile foundProfile=profileService.getProfileById(1L);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}
