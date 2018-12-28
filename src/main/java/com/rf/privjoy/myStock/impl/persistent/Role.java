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
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany(fetch=FetchType.EAGER,
			cascade={CascadeType.DETACH, CascadeType.MERGE, 
			 CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name="user_role",
			joinColumns=@JoinColumn(name="role_id"),
			inverseJoinColumns=@JoinColumn(name="user_id"))
	private Set<User> users;
	
	public Role() {
		
	}
	
	public Role(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<User> getUsers() {
		return users;
	}
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/**
	 * Add new user to role
	 * @param user
	 */
	public void addUser(User user) {
		
		if (users == null) {
			users = new HashSet<User>();
		}
		
		users.add(user);
	}
	
	/**
	 * Remove an existing user from role
	 * @param user
	 * @return true if user gets successfully removed
	 */
	public boolean removeUser(User user) {
		
		if (users == null || !users.contains(user)) {
			return false;
		}
		
		users.remove(user);
		return true;
	}
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
}
