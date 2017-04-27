package com.frame.common.frame.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

/**
 * 文件工具类
 * @author duancq
 * 2014-12-23 上午11:56:21
 */
public class FileUtil extends FileUtils {

	private FileUtil() {}

	/**
	 * 删除文件夹
	 * @param path 文件夹位置
	 * @throws IOException
	 */
	public static void deleteDir(String path) throws IOException {
		File directory = new File(path);
		deleteDirectory(directory);
	}

	/**
	 * 创建文件夹
	 * @param path 文件夹位置
	 * @throws IOException
	 */
	public static void createDir(String path) throws IOException{
		File directory = new File(path);
		forceMkdir(directory);
	}

	/**
	 * ZIP压缩，并返回文件的MD5
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static String zipAndGetMD5(List<File> srcFiles, File destFile) throws IOException {
		// 压缩
		zip(srcFiles, destFile);

		// 获取MD5
		String md5 = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(destFile);
			md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
		} finally {
			IOUtils.closeQuietly(fis);
		}
		return md5;
	}

	/**
	 * 压缩文件
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void zip(List<File> srcFiles, File destFile) throws IOException {
		InputStream fileIn = null;
		InputStream buffIn = null;

		ZipArchiveOutputStream zipOut = null;
		OutputStream fileOut = null;
		OutputStream buffOut = null;
		try {
			fileOut = new FileOutputStream(destFile);
			buffOut = new BufferedOutputStream(fileOut);
			zipOut = new ZipArchiveOutputStream(buffOut);

			for (File srcFile : srcFiles) {
				try {
					fileIn = new FileInputStream(srcFile);
					buffIn = new BufferedInputStream(fileIn);

					ZipArchiveEntry entry = new ZipArchiveEntry(srcFile.getName());
					entry.setSize(srcFile.length());
					zipOut.putArchiveEntry(entry);
					IOUtils.copy(buffIn, zipOut);
				} finally {
					IOUtils.closeQuietly(buffIn);
					IOUtils.closeQuietly(fileIn);
				}
			}
			try {
				zipOut.closeArchiveEntry();
			} finally {
				IOUtils.closeQuietly(zipOut);
			}
		} finally {
			IOUtils.closeQuietly(fileOut);
			IOUtils.closeQuietly(buffOut);
		}
	}

	/**
	 * 写入
	 * @param file		文件流
	 * @param output	输出流
	 * @param skip		跳过字节
	 * @param length	写入字节长度
	 * @return
	 * @throws IOException
	 */
	public static long write(File file, OutputStream output, Long skip, Long length) throws IOException {
		InputStream fis = null;
        InputStream bis = null;
        try {
        	fis = new FileInputStream(file);
        	bis = new BufferedInputStream(fis);
        	long skipTemp = (skip == null ? 0 : skip);
        	long lenTemp = (length == null ? -1 : length);
        	return IOUtils.copyLarge(bis, output, skipTemp, lenTemp);
        } finally {
        	IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(bis);
        }
	}
}
