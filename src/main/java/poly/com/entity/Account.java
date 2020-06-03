package poly.com.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Accounts")
@Data 
@AllArgsConstructor  
@NoArgsConstructor
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8471397695997290950L;

	@Id
	@Column(name = "user_id")
	private Integer id;
	
	@OneToOne
	@MapsId
    private User user;
	
	@NotNull
	@Column(unique = true, length = 20)
	private String username;
	
	@NotNull
	@Column(length = 120)
	private String password;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",//user_roles bảng trung gian
					joinColumns = @JoinColumn(name= "userId"),
					inverseJoinColumns = @JoinColumn(name="roleId"))
	private Set<Role> roles = new HashSet<>();
	
}

