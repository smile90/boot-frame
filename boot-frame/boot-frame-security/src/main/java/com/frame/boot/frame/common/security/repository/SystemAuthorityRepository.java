package com.frame.boot.frame.common.security.repository;

import org.springframework.stereotype.Repository;

import com.frame.common.base.repository.BaseRepository;
import com.frame.boot.frame.common.security.entity.SystemAuthority;

@Repository("systemAuthorityRepository")
public interface SystemAuthorityRepository extends BaseRepository<SystemAuthority, Long> {

}
