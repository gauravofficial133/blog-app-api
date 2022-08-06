package com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Roles;

public interface RoleRepo extends JpaRepository<Roles,Integer>{

}
