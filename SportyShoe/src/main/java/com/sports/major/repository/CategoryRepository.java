package com.sports.major.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sports.major.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
