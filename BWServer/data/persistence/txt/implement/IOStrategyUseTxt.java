package persistence.txt.implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import persistence.txt.format.FormatStrategy;
import persistence.txt.format.JsonFormat;
import persistence.txt.service.IOStrategy;

/**
 * 使用txt作為持久化方式, 不具備檢查重覆內容的能力
 * 
 * @since 2017/11/22
 * @author 161250051
 */
public class IOStrategyUseTxt<T> implements IOStrategy<T> {
	private static String FILE_SUFFIX = ".txt";
	private static String FILE_LOCATION = "persistence\\";
	private String fileName;

	private FormatStrategy formatStrategy;

	/**
	 * @param fileName不包含.txt
	 */
	public IOStrategyUseTxt(String fileName) {
		checkFileName(fileName);
		this.fileName = FILE_LOCATION + fileName + FILE_SUFFIX;
		this.formatStrategy = new JsonFormat(); // 默認使用JSON格式
	}

	private void checkFileName(String fileName) throws IllegalArgumentException {
		// window下文件命名規則
		if (fileName.charAt(0) == ' ')
			throw new IllegalArgumentException();
		if (fileName.length() > 255)
			throw new IllegalArgumentException();
		if (fileName.contains("?") || fileName.contains("、") || fileName.contains("\\") || fileName.contains("//")
				|| fileName.contains("*") || fileName.contains("\"") || fileName.contains(">") || fileName.contains(">")
				|| fileName.contains("|"))
			throw new IllegalArgumentException();
	}

	/**
	 * 刪除fileName內的所有內容
	 * 
	 * @throws IOException
	 */
	private void deleteAll() throws IOException {
		write("", false);
	}

	@Override
	public Boolean inAll(Iterator<T> objects) {
		try {
			while (objects.hasNext()) {
				// true 代表是在文件末尾寫
				write(formatStrategy.format(objects.next()), true);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean inOne(T object) {
		try {
			// true 代表是在文件末尾寫
			write(formatStrategy.format(object), true);
		} catch (IOException e) {
			// System.err.println("Bingo");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Iterator<T> outAll(Class<T> clazz) {
		ArrayList<String> dataFromFile = read(); // 調用輔助函數

		// 文件已開啟, 但只有一個空行的時候,讀出空但經過unformat後會變成null,造成nullpointer
		if (null == dataFromFile || dataFromFile.isEmpty()) {
			return Collections.emptyIterator(); // better than returning null
		}
		ArrayList<Object> ret = new ArrayList<Object>(); // return object
		for (String str : dataFromFile) {
			ret.add(formatStrategy.unFormat(str, clazz));
			// System.err.println(str);
		}
		return (Iterator<T>) ret.parallelStream().filter(obj -> obj != null).collect(Collectors.toList()).iterator();
	}

	@Override
	public T outOne(Class<T> clazz) {
		throw new UnsupportedOperationException();
		// return null;
	}

	/**
	 * 讀取fileName(以行為單位)
	 * 
	 * @return
	 */
	private ArrayList<String> read() {
		List<String> ret = new ArrayList<String>(); // return object
		File file = new File(fileName);
		FileInputStream fiStream = null;
		InputStreamReader isReader = null;
		BufferedReader brReader = null;
		if (file.isFile() && file.exists()) {
			try {
				fiStream = new FileInputStream(file);
				isReader = new InputStreamReader(fiStream);
				brReader = new BufferedReader(isReader);
				String temp = null; // 臨時變量,保存從文件裡讀取的一行
				while ((temp = brReader.readLine()) != null) {
					if (temp != "" || !temp.isEmpty() || temp.length() != 0)
						ret.add(temp);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} finally {
				// 在finally關閉流
				try {
					if (brReader != null)
						brReader.close();
					if (isReader != null)
						isReader.close();
					if (fiStream != null)
						fiStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		return (ArrayList<String>) ret;
	}

	@Override
	public Boolean replaceAll(Iterator<T> objects) {
		try {
			deleteAll();
			while (objects.hasNext())
				write(formatStrategy.format(objects.next()), true);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean replaceOne(T object) {
		throw new UnsupportedOperationException();
		// return null;
	}

	/**
	 * 暫時未用到
	 * 
	 * @param formatStrategy
	 */
	protected void setFormatStrategy(FormatStrategy formatStrategy) {
		this.formatStrategy = formatStrategy;
	}

	/**
	 * 往fileName寫一行內容,
	 * 
	 * @param line內容
	 * @param isAppend是否在文件末尾寫
	 * @throws IOException
	 */
	private void write(String line, Boolean isAppend) throws IOException {
		File file = new File(fileName);
		FileWriter fWriter = new FileWriter(file, isAppend);
		BufferedWriter bWriter = new BufferedWriter(fWriter);
		bWriter.write((line));
		if (line != "")
			bWriter.write(System.lineSeparator());
		bWriter.close();
		fWriter.close();
	}

}
