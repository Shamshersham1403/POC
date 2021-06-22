package com.POC.UserRegistration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.POC.UserRegistration.models.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {

	public List<UserInfo> findByStatus(int Status);

	public List<UserInfo> findAllByStatusOrderByDobAsc(int status);
	public List<UserInfo> findAllByStatusOrderByDobDesc(int status);
	public List<UserInfo> findAllByStatusOrderByDojAsc(int status);
	public List<UserInfo> findAllByStatusOrderByDojDesc(int status);
}
