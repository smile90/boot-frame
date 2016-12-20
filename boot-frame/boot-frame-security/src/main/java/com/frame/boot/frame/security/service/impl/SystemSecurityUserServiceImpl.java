package com.frame.boot.frame.security.service.impl;

import javax.transaction.Transactional;

import com.frame.boot.frame.security.service.SystemSecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.frame.boot.frame.security.repository.SystemSecurityUserRepository;

@Service("systemSecurityUserService")
public class SystemSecurityUserServiceImpl implements SystemSecurityUserService, UserDetailsService {

	@Autowired
	private SystemSecurityUserRepository systemSecurityUserRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return systemSecurityUserRepository.findByUsername(username);
	}
}
