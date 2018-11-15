package export;

/**
 * 需導出的類需要implement這個接口, 並保證導出字段都有Getter方法
 * 如String name, 需有String getName(){return name;}
 * 
 * @author 161250051
 *
 * @param <T>
 */
public interface Exportable<T> {

}
