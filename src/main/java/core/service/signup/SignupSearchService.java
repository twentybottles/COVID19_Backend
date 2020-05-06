package core.service.signup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import core.entity.SignupEntity;
import core.repository.SignupRepository;

@Service
public class SignupSearchService {

    @Autowired
    private SignupRepository repository;
    
    public boolean find(String emailAddress) {
        this.repository.findAll();
        return true;
    }
    
	public List<SignupEntity> select(@RequestParam("emailAddress") String emailAddress) {
		List<SignupEntity> list = repository.findById(emailAddress);
		return list;
	}
}