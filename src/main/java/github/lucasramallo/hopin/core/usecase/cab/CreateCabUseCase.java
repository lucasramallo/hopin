package github.lucasramallo.hopin.core.usecase.cab;

import github.lucasramallo.hopin.api.dtos.CreateDriverRequestDTO;
import github.lucasramallo.hopin.core.domain.cab.Cab;
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
        Pattern pattern = Pattern.compile("[A-Z]{3}-[0-9]{4}");
        Matcher matcher = pattern.matcher(resquest.plateNum());

        if(!matcher.matches()) {
            throw new RuntimeException("Placa Inválida");
        }

        Cab newCab = new Cab();
        newCab.setId(UUID.randomUUID());
        newCab.setModel(resquest.model());
        newCab.setPlateNum(resquest.plateNum());
        newCab.setColor(resquest.color());

        repository.save(newCab);

        return newCab;
    }
}
