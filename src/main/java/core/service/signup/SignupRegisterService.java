package core.service.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.entity.SignupEntity;
import core.repository.SignupRepository;

@Service
public class SignupRegisterService {

    @Autowired
    private SignupRepository repository;
    
    public void save(SignupEntity entity) {
        this.repository.save(entity);
    }
}