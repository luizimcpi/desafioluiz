package com.concrete.desafioluiz.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concrete.desafioluiz.model.User;

@Transactional
@Repository
public class UserRepositoryImpl implements UserRepositoryInterface{
	
	@PersistenceContext	
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		String hql = "FROM User as user ORDER BY user.id";
		return (List<User>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public User getUserById(Long userId) {
		return entityManager.find(User.class, userId);
	}

	@Override
	public void addUser(User user) {
		entityManager.persist(user);
	}

	@Override
	public void updateUser(User user) {
		User userDao = getUserById(user.getId());
		userDao.setEmail(user.getEmail());
		userDao.setPassword(user.getPassword());
		entityManager.flush();
	}

	@Override
	public void deleteUser(Long userId) {
		entityManager.remove(getUserById(userId));
	}

	@Override
	public boolean userExists(String email) {
		String hql = "FROM User WHERE email = :email";
		int count = entityManager.createQuery(hql).setParameter("email", email).getResultList().size();
		return count > 0 ? true : false;
	}	
	
}
