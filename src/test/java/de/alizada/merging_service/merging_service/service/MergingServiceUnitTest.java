package de.alizada.merging_service.merging_service.service;

import de.alizada.merging_service.merging_service.exception.BadRequestException;
import de.alizada.merging_service.merging_service.repository.MergingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MergingServiceUnitTest {
    private MergingRepository mergingRepository;
    private MergingService mergingService;

    @Before
    public void setUp(){
        mergingRepository = mock(MergingRepository.class);
        mergingService = new MergingService(mergingRepository);
    }

    @Test
    public void shouldResponseCorrectDataById(){
        when(mergingRepository.findUserById(1)).thenReturn("User");
        when(mergingRepository.findCommentsById(1)).thenReturn("Comments");

        assertEquals(mergingService.merge(1), "UserComments");
    }

    @Test
    public void shouldThrowBadRequestForWrongId(){
        when(mergingRepository.findUserById(2)).thenThrow(HttpClientErrorException.class);
        when(mergingRepository.findCommentsById(2)).thenThrow(HttpClientErrorException.class);

        assertThrows(BadRequestException.class, () -> {
            mergingService.merge(2);
        });
    }

}
