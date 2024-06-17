package github.lucasramallo.hopin.core.usecase.driver;

import github.lucasramallo.hopin.api.dtos.CreateDriverRequestDTO;
import github.lucasramallo.hopin.core.domain.cab.Cab;
import github.lucasramallo.hopin.core.domain.driver.Driver;
import github.lucasramallo.hopin.core.usecase.cab.CreateCabUseCase;
import github.lucasramallo.hopin.data.jpa.DriverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

class CreateDriverUseCaseTest {
    @Mock
    private DriverRepository repository;
    @Mock
    private CreateCabUseCase createCabUseCase;

    @Autowired
    @InjectMocks
    private CreateDriverUseCase createDriverUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a new Driver")
    void Case01() {
        CreateDriverRequestDTO requestDTO = new CreateDriverRequestDTO(
                "João Lucas",
                LocalDate.parse("1998-12-13"),
                "Toyota Corolla",
                "Prata",
                "XYZ-1234"
        );
        Cab cab = new Cab(UUID.randomUUID(), "Toyota Corolla", "Prata", "XYZ-1234");
        when(createCabUseCase.execute(requestDTO)).thenReturn(cab);

        Driver createdDriver = createDriverUseCase.execute(requestDTO);

        verify(createCabUseCase, times(1)).execute(requestDTO);
        verify(repository, times(1)).save(any());

        assertNotNull(createdDriver);
        assertNotNull(createdDriver.getCab());

        assertEquals(requestDTO.name(), createdDriver.getName());
        assertEquals(requestDTO.dateOfBirth(), createdDriver.getDateOfBirth());
        assertEquals(requestDTO.model(), createdDriver.getCab().getModel());
        assertEquals(requestDTO.color(), createdDriver.getCab().getColor());
        assertEquals(requestDTO.plateNum(), createdDriver.getCab().getPlateNum());
    }

    @Test
    @DisplayName("Should throw an exception when the driver name is invalid")
    void case02() {
        CreateDriverRequestDTO requestDTO = new CreateDriverRequestDTO(
                "invalid",
                LocalDate.parse("1998-12-13"),
                "Toyota Corolla", "Prata",
                "XYZ-1234"
        );

        assertThrows(RuntimeException.class, () -> createDriverUseCase.execute(requestDTO));
    }

    @Test
    @DisplayName("Should throw an exception when the driver is a minor")
    void case03() {
        CreateDriverRequestDTO requestDTO = new CreateDriverRequestDTO(
                "invalid",
                LocalDate.now(),
                "Toyota Corolla", "Prata",
                "XYZ-1234"
        );

        assertThrows(RuntimeException.class, () -> createDriverUseCase.execute(requestDTO));
    }
}