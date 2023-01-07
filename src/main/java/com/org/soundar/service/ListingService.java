package com.org.soundar.service;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.org.soundar.dto.ContentDto;
import com.org.soundar.dto.DirectoryDto;
import com.org.soundar.dto.InputDto;
import com.org.soundar.dto.ResponseDto;
import com.org.soundar.utils.GenUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ListingService {

	@Autowired
	GenUtils util;

	Gson gson = new Gson();

	public String folderPropService(InputDto input) {
		log.info("Inside Service.folderPropService()");

		/* Response Object */
		ResponseDto res = new ResponseDto();
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
		Type refType = new TypeToken<ResponseDto>() {
		}.getType();

		return gson.toJson(res, refType);
	}

	private void addDir(File f, List<DirectoryDto> dirList) {
		DirectoryDto dir = new DirectoryDto();
		dir.setName(f.getName());
		dir.setPath(f.getAbsolutePath());
		dir.setSize(util.readableFileSize(FileUtils.sizeOfDirectory(f)));
		dir.setNoOfItems(f.getName().equalsIgnoreCase("System Volume Information") ? 0l : Array.getLength(f.list()));
		dirList.add(dir);
	}

	private void addFile(File f, List<DirectoryDto> fileList) {
		DirectoryDto file = new DirectoryDto();
		file.setName(f.getName());
		file.setPath(f.getAbsolutePath());
		file.setSize(util.readableFileSize(f.length()));
		file.setNoOfItems(0l);
		fileList.add(file);

	}

}
