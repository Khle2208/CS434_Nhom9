package com.nhom9.dao;

import java.util.ArrayList;

public interface DAOInterface<T> {
	public int insert(T t);
	public int update(T t);
	public int delete(T t);
	public ArrayList<T> findAll();
	public T findById(T t);
}
