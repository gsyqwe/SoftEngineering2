package persistence.txt.service;

import java.util.Iterator;

public interface IOStrategy<T> {
	public Boolean inOne(T object);

	public Boolean inAll(Iterator<T> objects);

	public Iterator<T> outAll(Class<T> clazz);

	public T outOne(Class<T> clazz);

	public Boolean replaceAll(Iterator<T> objects);

	public Boolean replaceOne(T object);

}
