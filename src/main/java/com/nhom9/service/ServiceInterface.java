package com.nhom9.service;

import java.util.ArrayList;

public interface ServiceInterface<T> {
	public int insert(T t);
	public int update(T t);
	public int delete(T t);
	public ArrayList<T> findAll();
	public T findById(T t);
}
