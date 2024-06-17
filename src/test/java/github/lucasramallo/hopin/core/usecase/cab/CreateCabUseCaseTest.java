package github.lucasramallo.hopin.core.usecase.cab;

import github.lucasramallo.hopin.api.dtos.CreateDriverRequestDTO;
import github.lucasramallo.hopin.core.domain.cab.Cab;
import github.lucasramallo.hopin.data.jpa.CabRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CreateCabUseCaseTest {
    @Mock
    private CabRepository repository;

    @Autowired
    @InjectMocks
    private CreateCabUseCase createCabUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a new Cab")
    void case01() {
        CreateDriverRequestDTO requestDTO = new CreateDriverRequestDTO(
                "João Lucas",
                "senha", // TODO: Create password test
                LocalDate.parse("1998-12-13"),
                "Toyota Corolla",
                "Prata",
                "XYZ-1234"
        );

        Cab cab = createCabUseCase.execute(requestDTO);

        Mockito.verify(repository, Mockito.times(1)).save(any());

        assertEquals(cab.getModel(), requestDTO.model());
        assertEquals(cab.getColor(), requestDTO.color());
        assertEquals(cab.getPlateNum(), requestDTO.plateNum());
    }

    @Test
    @DisplayName("Should throw an exception when the plateNum is invalid")
    void case02() {
        CreateDriverRequestDTO requestDTO = new CreateDriverRequestDTO(
                "João Lucas",
                "senha",
                LocalDate.parse("1998-12-13"),
                "Toyota Corolla",
                "Prata",
                "1234" // invalid
        );

        assertThrows(RuntimeException.class, () -> createCabUseCase.execute(requestDTO));
    }

}