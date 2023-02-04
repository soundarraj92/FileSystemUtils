package com.org.soundar.utils;

import java.io.File;
import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

import com.org.soundar.exception.AppCustomException;
import com.org.soundar.exception.DirNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GenUtils {

	/**
	 * Utility to validate if given path is a directory
	 * 
	 * @param path
	 * @throws Exception
	 */
	public void validateDirectoryPath(String path) throws AppCustomException {

		File file = new File(path);
		if (file.exists() && file.isDirectory())
			log.info("Directory path valid - {}", path);
		else {
			log.error("Invalid path - {}", path);
			throw new DirNotFoundException(path);
		}
	}

	/**
	 * Utility to convert file size long to human readable format
	 * 
	 * @param size
	 * @return
	 */
	public String readableFileSize(long size) {
		if (size <= 0) {
			return "0";
		}
		final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}

	/**
	 * Util to calculate Directory size by traversing in
	 * 
	 * @param folder
	 * @return
	 */
	public long calculateDirSizeLegacy(File folder) {

		long length = 0;
		/* Lists files and folders */
		File[] files = folder.listFiles();
		int count = files != null ? files.length : 0;

		/* Loop for traversing the directory */
		for (int i = 0; i < count; i++) {
			if (files[i].isFile()) {
				length += files[i].length();
			} else {
				length += calculateDirSizeLegacy(files[i]);
			}
		}
//		log.info("Calculating size of Directory {} - {}", folder.getAbsolutePath(), length);
		return length;
	}
}
