package org.yefei.qa.mock.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;
import java.util.UUID;

/**
 * 
 * 文件工具类
 * 
 * @author jiankang
 * @date 2013-11-25
 * @version 1.0
 */
public class FileUtil {
	
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static int getFileSize(long fileSize, long sourceSize) {
		if (sourceSize != fileSize) {
			// 修正图片大小
			return new Long(sourceSize).intValue();
		}
		return new Long(fileSize).intValue();
	}

	public static String getFileName(String fileName) {
		if (fileName != null) {
			int endIndex = fileName.lastIndexOf(".");
			if (endIndex == -1) {
				return UUID.randomUUID().toString() + fileName;
			}
			
			String subStr = fileName.substring(endIndex, fileName.length());
			return UUID.randomUUID().toString() + subStr;
			
		}
		return fileName;
	}

	public static void deleteFile(String srcFile) {
		File file = new File(srcFile);
		if (file.exists()) {
			file.delete();
		}
	}

	public static String createFilePath(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
			return file.getPath();
		}
		File[] fileList = file.listFiles();
		if (fileList != null) {
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isFile()) {
					fileList[i].delete();
				}
			}
		}
		return path;
	}

	public static void writeInputStream(InputStream is, String toPath) throws IOException {
		FileOutputStream fos = new FileOutputStream(toPath);
		byte[] buf = new byte[4096];
		for (int len = -1; true;) {
			len = is.read(buf);
			if (len == -1)
				break;
			fos.write(buf, 0, len);
		}
		fos.close();
	}

	public static String readContent(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		return readContent(fis);
	}

	public static String readContent(InputStream inputStream) throws IOException {
		int totalLen = inputStream.available();
		byte[] buf = new byte[totalLen];

		for (int readlen = 0, len = -1; readlen < totalLen || len == -1;) {
			len = inputStream.read(buf, readlen, totalLen - readlen);
			readlen += len;
		}
		inputStream.close();
		return new String(buf, "utf-8");
	}

	public static void writeFile(String filePath, String secKeyContent) throws IOException {
		FileOutputStream fos = new FileOutputStream(filePath);
		fos.write(secKeyContent.getBytes("utf-8"));
		fos.close();

	}
	
	public static Properties getProperties(InputStream is,String encoding)
	{
		try{
			Properties prop = new Properties();
			prop.load( new InputStreamReader(is,encoding));
			return prop;
		}catch(Exception ex){
			logger.error(ex.toString(),ex);
			throw new RuntimeException(ex);
		}
	}
	
	public static String getContent(InputStream is,String encoding){
		try{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[4096];
			for (int len = -1; true;) {
				len = is.read(buf);
				if (len == -1)
					break;
				bos.write(buf, 0, len);
			}
			
			String content = new String(bos.toByteArray(),encoding);
			return content;
		}catch(Exception ex){
			logger.error(ex.toString(),ex);
			return "";
		}
	}
	
	public static void writeFileData(String filePath, byte[] data) {
		if(data ==null){
			return;
		}
		
		FileOutputStream fos= null;
		try {
			fos = new FileOutputStream(filePath);
			fos.write(data);
			fos.close();
		} catch (IOException e) {
			logger.error(e.toString(),e);
		}
	}
}
