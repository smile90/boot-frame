package com.frame.boot.frame.common.security.repository;

import org.springframework.stereotype.Repository;

import com.frame.common.base.repository.BaseRepository;
import com.frame.boot.frame.common.security.entity.SystemRole;

@Repository("systemRoleRepository")
public interface SystemRoleRepository extends BaseRepository<SystemRole, Long> {

}
