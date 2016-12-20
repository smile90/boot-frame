package com.frame.boot.frame.security.repository;

import com.frame.boot.frame.security.entity.SystemSecurityUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.frame.common.base.repository.BaseRepository;

@Repository("systemSecurityUserRepository")
public interface SystemSecurityUserRepository extends BaseRepository<SystemSecurityUser, Long> {

	@Query("from SystemSecurityUser where username = ?1 ")
	SystemSecurityUser findByUsername(String username);
}
