package com.frame.security.repository;

import org.springframework.stereotype.Repository;

import com.frame.common.base.repository.BaseRepository;
import com.frame.security.entity.SystemRole;

@Repository("systemRoleRepository")
public interface SystemRoleRepository extends BaseRepository<SystemRole, Long> {

}
