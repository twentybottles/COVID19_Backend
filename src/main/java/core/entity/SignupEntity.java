package core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MEMBER_INFORMATION")
@Data
public class SignupEntity {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "emailAddress")
    private String emailAddress;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "password")
    private String password;

    @Override
    public String toString() {
        return "SignupEntity [id=" + id + ", emailAddress=" + emailAddress + "]";
    }
	
	
}