package com.application.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.application.Entity.Blog;
import com.application.Entity.Resource;

public class ResourePage implements Page<Resource>,Serializable{

	private PageImpl<Resource> pageImpl=new PageImpl<Resource>(new ArrayList<Resource>());
	public int getNumber() {
		
		return pageImpl.getNumber();
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return pageImpl.getSize();
	}

	public int getNumberOfElements() {
		// TODO Auto-generated method stub
		return pageImpl.getNumberOfElements();
	}

	public List<Resource> getContent() {
		// TODO Auto-generated method stub
		return pageImpl.getContent();
	}

	public boolean hasContent() {
		// TODO Auto-generated method stub
		return pageImpl.hasContent();
	}

	public Sort getSort() {
		// TODO Auto-generated method stub
		return pageImpl.getSort();
	}

	public boolean isFirst() {
		// TODO Auto-generated method stub
		return pageImpl.isFirst();
	}

	public boolean isLast() {
		// TODO Auto-generated method stub
		return pageImpl.isLast();
	}

	public boolean hasNext() {
		// TODO Auto-generated method stub
		return pageImpl.hasNext();
	}

	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return pageImpl.hasPrevious();
	}

	public Pageable nextPageable() {
		// TODO Auto-generated method stub
		return pageImpl.nextPageable();
	}

	public Pageable previousPageable() {
		// TODO Auto-generated method stub
		return pageImpl.previousPageable();
	}

	public Iterator<Resource> iterator() {
		// TODO Auto-generated method stub
		return pageImpl.iterator();
	}

	public int getTotalPages() {
		// TODO Auto-generated method stub
		return pageImpl.getTotalPages();
	}

	public long getTotalElements() {
		// TODO Auto-generated method stub
		return pageImpl.getTotalElements();
	}

	public <U> Page<U> map(Function<? super Resource, ? extends U> converter) {
		// TODO Auto-generated method stub
		return pageImpl.map(converter);
	}


}
