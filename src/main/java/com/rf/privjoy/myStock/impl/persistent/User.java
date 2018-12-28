package com.rf.privjoy.myStock.impl.persistent;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="email")
	private String email;
	
	@Column(name="active")
	private Boolean active;
	
	@ManyToMany(fetch=FetchType.EAGER,
			cascade={CascadeType.DETACH, CascadeType.MERGE, 
			 CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name="user_role",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles;
	
	@OneToMany(fetch=FetchType.EAGER, 
			mappedBy="user",
			cascade={CascadeType.DETACH, CascadeType.MERGE, 
					 CascadeType.PERSIST, CascadeType.REFRESH})
	private Set<Inventory> inventories;
	
	@OneToMany(fetch=FetchType.EAGER, 
			mappedBy="user",
			cascade={CascadeType.DETACH, CascadeType.MERGE, 
					 CascadeType.PERSIST, CascadeType.REFRESH})
	private Set<Record> records;
	
	public User() {
		
	}
	
	public User(String username, String password, String lastname, String firstname, String email, Boolean active) {
		super();
		this.username = username;
		this.password = password;
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.active = active;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public Set<Inventory> getInventories() {
		return inventories;
	}
	
	public Set<Record> getRecords() {
		return records;
	}
	
	/**
	 * Add a new inventory to user
	 * @param inventory
	 */
	public void addInventory(Inventory inventory) {
		
		if (inventories == null) {
			inventories = new HashSet<Inventory>();
		}
		
		inventories.add(inventory);
		inventory.setUser(this);
	}
	
	/**
	 * Remove an existing inventory from user
	 * @param inventory
	 * @return true if inventory gets successfully removed
	 */
	public Boolean removeInventory(Inventory inventory) {
		
		if (inventories == null || !inventories.contains(inventory)) {
			return false;
		}
		
		inventory.setUser(null);
		inventories.remove(inventory);
		return true;
	}
	
	/**
	 * Add a new record to user
	 * @param record
	 */
	public void addRecord(Record record) {
		
		if (records == null) {
			records = new HashSet<Record>();
		}
		
		records.add(record);
		record.setUser(this);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", lastname=" + lastname
				+ ", firstname=" + firstname + ", email=" + email + ", active=" + active + "]";
	}
	
}
