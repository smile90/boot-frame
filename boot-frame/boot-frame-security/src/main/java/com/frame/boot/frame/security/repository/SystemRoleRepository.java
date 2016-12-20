package com.frame.boot.frame.security.repository;

import com.frame.boot.frame.security.entity.SystemRole;
import org.springframework.stereotype.Repository;

import com.frame.common.base.repository.BaseRepository;

@Repository("systemRoleRepository")
public interface SystemRoleRepository extends BaseRepository<SystemRole, Long> {

}
