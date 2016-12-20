package com.frame.security.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.frame.common.base.repository.BaseRepository;
import com.frame.security.entity.SystemSecurityUser;

@Repository("systemSecurityUserRepository")
public interface SystemSecurityUserRepository extends BaseRepository<SystemSecurityUser, Long> {

	@Query("from SystemSecurityUser where username = ?1 ")
	SystemSecurityUser findByUsername(String username);
}
