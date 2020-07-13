package com.fhlzmy.web.dao;

import com.fhlzmy.web.model.Anut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnutRepository extends JpaRepository<Anut, String> {

    public List<Anut> findAll();

    public List<Anut> findAllByUserId(String userId);

}
