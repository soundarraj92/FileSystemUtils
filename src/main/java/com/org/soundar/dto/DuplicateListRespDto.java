package com.org.soundar.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DuplicateListRespDto {

	private Map<String, List<String>> duplicatedFilesMap;

	private String inputPath;

	private String fileTypeExclusions;

	private String totalSize;

	private long noOfFiles;

}
