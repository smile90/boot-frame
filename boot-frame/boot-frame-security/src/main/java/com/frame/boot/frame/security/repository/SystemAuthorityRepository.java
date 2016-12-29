package com.frame.boot.frame.security.repository;

import com.frame.boot.frame.security.entity.SystemAuthority;
import com.frame.common.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository("systemAuthorityRepository")
public interface SystemAuthorityRepository extends BaseRepository<SystemAuthority, Long> {

}
