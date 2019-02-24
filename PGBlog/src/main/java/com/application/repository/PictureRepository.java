package com.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.Entity.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long>{
	

	@Query(value="select * from picture where path=?1",nativeQuery=true)
	public List<Picture> getPictureByName(String path);
}
