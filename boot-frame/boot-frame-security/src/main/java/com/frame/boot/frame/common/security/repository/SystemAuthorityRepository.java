package com.frame.security.repository;

import org.springframework.stereotype.Repository;

import com.frame.common.base.repository.BaseRepository;
import com.frame.security.entity.SystemAuthority;

@Repository("systemAuthorityRepository")
public interface SystemAuthorityRepository extends BaseRepository<SystemAuthority, Long> {

}
