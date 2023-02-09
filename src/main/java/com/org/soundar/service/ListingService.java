package com.org.soundar.service;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.org.soundar.dto.ContentDto;
import com.org.soundar.dto.DirectoryDto;
import com.org.soundar.dto.DuplicateListRespDto;
import com.org.soundar.dto.FileListRespDto;
import com.org.soundar.dto.InputDto;
import com.org.soundar.dto.PropertiesRespDto;
import com.org.soundar.exception.AppCustomException;
import com.org.soundar.utils.GenUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ListingService {

	@Autowired
	GenUtils util;

	Gson gson = new Gson();

	/**
	 * Service method for folder properties
	 * 
	 * @param input
	 * @return
	 * @throws AppCustomException
	 */
	public String folderPropService(InputDto input) throws AppCustomException {
		log.info("Inside Service.folderPropService()");

		/* Response Object */
		PropertiesRespDto res = new PropertiesRespDto();
		res.setInputPath(input.getFsPath());
		res.setDetailedInfo(input.isDetailedInfo());
		List<DirectoryDto> dirList = new ArrayList<>();
		List<DirectoryDto> fileList = new ArrayList<>();
		ContentDto contents = new ContentDto(dirList, fileList);

		/* Validates if input path is a directory */
		util.validateDirectoryPath(input.getFsPath());

		File root = new File(input.getFsPath());
		/* Obtains a list of files and folders in input path */
		File[] list = root.listFiles();

		/* Iterate over above list and add to dirList and fileList */
		for (File f : list) {
			if (f.isDirectory())
				addDir(f, dirList);
			else
				addFile(f, fileList);
		}

		res.setContentsList(contents);
		res.setTotalSize(util.readableFileSize(FileUtils.sizeOfDirectory(root)));
		res.setDiskSize(util.readableFileSize(root.getTotalSpace()));
		res.setUnusedDiskSpace(util.readableFileSize(root.getFreeSpace()));
		Type refType = new TypeToken<PropertiesRespDto>() {
		}.getType();
		log.info("Folder Properties update complete - {}", input.getFsPath());

		return gson.toJson(res, refType);
	}

	/**
	 * Service method for file list
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public String fileListService(InputDto input) throws AppCustomException {
		log.info("Inside Service.fileListService()");

		/* Response Object */
		FileListRespDto res = new FileListRespDto();
		res.setInputPath(input.getFsPath());
		res.setDetailedInfo(input.isDetailedInfo());
		List<String> fileList = new ArrayList<>();

		/* Validates if input path is a directory */
		util.validateDirectoryPath(input.getFsPath());

		File root = new File(input.getFsPath());
		prepareFileList(root, fileList);

		res.setFileList(fileList);
		res.setTotalSize(util.readableFileSize(FileUtils.sizeOfDirectory(root)));
		res.setNoOfFiles(fileList.size());
		Type refType = new TypeToken<FileListRespDto>() {
		}.getType();
		log.info("File List update complete - {}", input.getFsPath());

		return gson.toJson(res, refType);
	}

	/**
	 * Service method for finding duplicates
	 * 
	 * @param input
	 * @return
	 * @throws AppCustomException
	 */
	public String findDuplicateService(InputDto input) throws AppCustomException {
		log.info("Inside Service.findDuplicateService()");

		/* Response Object */
		DuplicateListRespDto res = new DuplicateListRespDto();
		res.setInputPath(input.getFsPath());
		res.setFileTypeExclusions(input.getFileTypeExclusions());
		List<String> fileList = new ArrayList<>();
		Map<String, List<String>> duplicatedMap = new HashMap<>();

		/* Validates if input path is a directory */
		util.validateDirectoryPath(input.getFsPath());

		File root = new File(input.getFsPath());
		prepareFileList(root, fileList);

		duplicatedMap = filterDuplicates(fileList, input.getFileTypeExclusions());

		res.setDuplicatedFilesMap(duplicatedMap);
		res.setNoOfFiles(duplicatedMap.size());
		Type refType = new TypeToken<DuplicateListRespDto>() {
		}.getType();
		log.info("Find duplicate files complete - {}", input.getFsPath());

		return gson.toJson(res, refType);
	}

	/**
	 * Method to filter duplicate files after applying exclusions
	 * 
	 * @param fileListWithPaths
	 * @return
	 */
	private Map<String, List<String>> filterDuplicates(List<String> fileListWithPaths, String exclusionTypes) {
		Map<String, List<String>> duplicatedMap = new HashMap<>();

		/* Exclude the file extension */
		// TODO: need to add support for multiple exclusions
		List<String> listAfterExclusion = StringUtils.hasLength(exclusionTypes)
				? fileListWithPaths.stream().filter(f -> !f.endsWith(exclusionTypes)).collect(Collectors.toList())
				: fileListWithPaths;

		List<String> fileNamesList = new ArrayList<>();

		/* Extract just filenames into a list */
		for (String str : listAfterExclusion) {
			String s = str.substring(str.lastIndexOf("\\") + 1);
			fileNamesList.add(s);
		}

		/* Iterate over filenames and find duplicates */
		Set<String> set = new HashSet<>();
		for (String i : fileNamesList) {
			if (Collections.frequency(fileNamesList, i) > 1) {
				set.add(i);
			}
		}

		/* Iterate over duplicated files and add their paths */
		for (String s : set) {
			List<String> values = fileListWithPaths.stream().filter(f -> f.endsWith(s)).collect(Collectors.toList());
			duplicatedMap.put(s, values);
		}

		return duplicatedMap;
	}

	/**
	 * Add directory info to dir List
	 * 
	 * @param f
	 * @param dirList
	 */
	private void addDir(File f, List<DirectoryDto> dirList) {
		DirectoryDto dir = new DirectoryDto();
		dir.setName(f.getName());
		dir.setPath(f.getAbsolutePath());
		dir.setSize(util.readableFileSize(FileUtils.sizeOfDirectory(f)));
//		dir.setNoOfItems(Array.getLength(f.list()));
		dir.setNoOfItems(f.getName().equalsIgnoreCase("System Volume Information") ? 0l : Array.getLength(f.list()));
		dirList.add(dir);
	}

	/**
	 * Add file info file List
	 * 
	 * @param f
	 * @param fileList
	 */
	private void addFile(File f, List<DirectoryDto> fileList) {
		DirectoryDto file = new DirectoryDto();
		file.setName(f.getName());
		file.setPath(f.getAbsolutePath());
		file.setSize(util.readableFileSize(f.length()));
		file.setNoOfItems(0l);
		fileList.add(file);
	}

	/**
	 * Prepare files list
	 * 
	 * @param file
	 * @param fileList
	 */
	private void prepareFileList(File file, List<String> fileList) {
		/* Ignore contents of "System Volume Information" directory */
		if (file.getName().equals("System Volume Information")) {
			fileList.add(file.getAbsolutePath());
		} else {
			/* Obtains a list of files and folders in input path */
			File[] list = file.listFiles();
			/* Iterate over above list and add to fileList */
			for (File f : list) {
				if (f.isFile())
					fileList.add(f.getAbsolutePath());
				else
					prepareFileList(f, fileList);
			}
		}
	}
}