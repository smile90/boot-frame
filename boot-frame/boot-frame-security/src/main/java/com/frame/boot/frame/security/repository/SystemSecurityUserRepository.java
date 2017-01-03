package com.frame.boot.frame.security.repository;

import com.frame.boot.frame.core.repository.BaseRepository;
import com.frame.boot.frame.security.entity.SystemSecurityUser;
import org.springframework.stereotype.Repository;

@Repository("systemSecurityUserRepository")
public interface SystemSecurityUserRepository extends BaseRepository<SystemSecurityUser, String> {

	SystemSecurityUser findByUsername(String username);
}
