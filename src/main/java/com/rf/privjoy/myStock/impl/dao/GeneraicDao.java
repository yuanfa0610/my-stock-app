package com.rf.privjoy.myStock.impl.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


public interface GeneraicDao<T, ID extends Serializable> {
	
	/**
	 * Save an entity
	 * @param entity the entity to save
	 * @return generated id
	 */
	public ID save(T entity);
	
	/**
	 * Update an entity
	 * @param entity the entity to update
	 */
	public void update(T entity);
	
	/**
	 * Save an entity if doesn't exist, update the entity if already exists
	 * @param entity the entity to save or update
	 */
	public void saveOrUpdate(T entity);
	
	/**
	 * For each entity, save it if doesn't exist, update it if already exists
	 * @param entities the entities to save or update
	 */
	public void saveOrUpdateAll(Collection<T> entities);
	
	/**
	 * Get the entity with given ID
	 * @param id the criteria ID used to search
	 * @return the entity with given ID
	 */
	public T getById(ID id);
	
	/**
	 * Get all entities with given IDs
	 * @param ids the criteria IDs used to search
	 * @return a list of entities with given IDs
	 */
	public List<T> getByIds(Collection<ID> ids);
	
	/**
	 * Get IDs for all existing entities
	 * @return a list of IDs for all existing entities
	 */
	public List<ID> getAllIds();
	
	/**
	 * Get all existing entities
	 * @return a list of all existing entities
	 */
	public List<T> getAll();
	
	/**
	 * Delete the entity
	 * @param entity the entity to delete
	 */
	public void delete(T entity);
	
	/**
	 * Delete all entities
	 * @param entities the entities to delete
	 */
	public void deleteAll(Collection<T> entities);

}
