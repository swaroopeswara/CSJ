package com.xpts.csg.service.bean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.xpts.csg.common.JndiUtils;
import com.xpts.csg.common.JsonSerializer;
import com.xpts.csg.model.CsgUser;
import com.xpts.csg.service.UserService;

@Stateless
public class UserServiceBean implements UserService {

	@PersistenceContext(unitName="csg_pu")
	private EntityManager em;
	
	@Override
	public void saveUser(String userJson){
		CsgUser	user = JsonSerializer.create(CsgUser.class, false).deserialize(userJson);		
		em.persist(user);
		System.out.println("user persisted to db: "+user.getId());
	}

	@Override
	public CsgUser findUser(Integer id){
		return em.find(CsgUser.class, id);
	}
	
	@Override
	public String getUserJson(Integer id){
	    return JsonSerializer.create(CsgUser.class, false).serialize(findUser(id));
	}
		
	private EntityManager getEm() {
		if (em == null) {
			em = JndiUtils.lookup("java:global/payhub/env/persistence/csg_pu");
		}
		return em;
	}
}
