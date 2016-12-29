package com.frame.boot.frame.security.repository;

import com.frame.boot.frame.core.repository.BaseRepository;
import com.frame.boot.frame.security.entity.SystemRole;
import org.springframework.stereotype.Repository;

@Repository("systemRoleRepository")
public interface SystemRoleRepository extends BaseRepository<SystemRole, Long> {

}
