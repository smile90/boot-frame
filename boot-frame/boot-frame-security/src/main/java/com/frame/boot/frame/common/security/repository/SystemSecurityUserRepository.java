package com.frame.boot.frame.common.security.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.frame.common.base.repository.BaseRepository;
import com.frame.boot.frame.common.security.entity.SystemSecurityUser;

@Repository("systemSecurityUserRepository")
public interface SystemSecurityUserRepository extends BaseRepository<SystemSecurityUser, Long> {

	@Query("from SystemSecurityUser where username = ?1 ")
	SystemSecurityUser findByUsername(String username);
}
