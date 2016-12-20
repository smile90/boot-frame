package com.frame.security.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.frame.security.repository.SystemSecurityUserRepository;
import com.frame.security.service.SystemSecurityUserService;

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
