package github.lucasramallo.hopin.core.usecase.cab;

import github.lucasramallo.hopin.api.dtos.driver.CreateDriverRequestDTO;
import github.lucasramallo.hopin.core.domain.cab.Cab;
import github.lucasramallo.hopin.core.domain.cab.exceptions.InvalidPlateException;
import github.lucasramallo.hopin.data.jpa.CabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CreateCabUseCase {
    @Autowired
    private CabRepository repository;

    public Cab execute(CreateDriverRequestDTO resquest) {
        Cab newCab = new Cab();
        newCab.setId(UUID.randomUUID());
        newCab.setModel(resquest.model());

        validatePlate(resquest.plateNum());
        newCab.setPlateNum(resquest.plateNum());

        newCab.setColor(resquest.color());

        repository.save(newCab);
        return newCab;
    }

    public void validatePlate(String plate) {
        Pattern pattern = Pattern.compile("[A-Z]{3}-[0-9]{4}");
        Matcher matcher = pattern.matcher(plate);

        if(!matcher.matches()) {
            throw new InvalidPlateException("Placa Inválida");
        }
    }
}
