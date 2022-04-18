package com.example.demo;

import com.example.demo.domain.model.Tag;
import com.example.demo.domain.repository.TagRepository;
import com.example.demo.domain.service.TagService;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.TagServiceImpl;
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
public class TagServiceImplTest {
    @MockBean
    private TagRepository tagRepository;


    @Autowired
    private TagService tagService;

    @TestConfiguration
    static class TagServiceImplTestConfiguration {
        @Bean
        public TagService tagService(){
            return new TagServiceImpl();
        }
    }

    @Test
    @DisplayName("When getTagByName With Valid Name Then Returns Tag")
    public void whenGetTagByNameWithValidNameThenReturnsTag(){

        //Arrange
        String name="Postre";
        Long id= 1L;
        Tag tag=new Tag().setId(1L).setName(name);

        when(tagRepository.findByName(name)).thenReturn(Optional.of(tag));

        //Act
        Tag foundTag=tagService.getTagByName(name);

        //Assert
        assertThat(foundTag.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("When getTagByName With Invalid Name Then Returns Resource Not Found Exception")
    public void whenGetTagByNameWithInvalidNameThenReturnsResourceNotFoundException(){
        //Arrange
        String name="Example";
        String template="Resource %s nor found for %s with value %s";
        when(tagRepository.findByName(name)).thenReturn(Optional.empty());
        String expectedMessage=String.format(template,"Tag","Name",name);

        //Act
        Throwable exception= catchThrowable(()->{
            Tag foundTag=tagService.getTagByName(name);
        });

        //Assert
        assertThat(exception).isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);

    }
}
