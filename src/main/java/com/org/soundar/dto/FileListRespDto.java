package com.org.soundar.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FileListRespDto {

	@JsonProperty(value = "contentsList")
	List<String> fileList = new ArrayList<>();

	@JsonProperty(value = "inputPath")
	private String inputPath;

	@JsonProperty(value = "totalSize")
	private String totalSize;

	@JsonProperty(value = "noOfFiles")
	private long noOfFiles;

	@JsonProperty(value = "detailedInfo", defaultValue = "false")
	private boolean detailedInfo = false;
}