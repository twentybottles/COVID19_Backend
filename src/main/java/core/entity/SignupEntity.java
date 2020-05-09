package core.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "MEMBER_INFORMATION")
@Data
public class SignupEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
	@Column(name = "password")
    private String password;
	
//    @Column(name = "created_timestamp")
//    private Date createdTimestamp;
//
//    @Column(name = "updated_timestamp")
//    private Date updatedTimestamp;
//
//    @PrePersist
//    public void onPrePersist() {
//        setCreatedTimestamp(new Date());
//        setUpdatedTimestamp(new Date());
//    }
//
//    @PreUpdate
//    public void onPreUpdate() {
//        setUpdatedTimestamp(new Date());
//    }
	

    @Override
    public String toString() {
        return "SignupEntity [id=" + id + ", emailAddress=" + emailAddress + "]";
    }
	
	
}