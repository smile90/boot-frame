package com.frame.boot.frame.security.repository;

import com.frame.boot.frame.core.repository.BaseRepository;
import com.frame.boot.frame.security.entity.SystemAuthority;
import org.springframework.stereotype.Repository;

@Repository("systemAuthorityRepository")
public interface SystemAuthorityRepository extends BaseRepository<SystemAuthority, String> {

}
