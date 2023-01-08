package com.org.soundar.dto;

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
public class PropertiesRespDto {

	@JsonProperty(value = "contentsList")
	ContentDto contentsList = new ContentDto();

	@JsonProperty(value = "inputPath")
	private String inputPath;
	
	@JsonProperty(value = "totalSize")
	private String totalSize;

	@JsonProperty(value = "detailedInfo", defaultValue = "false")
	private boolean detailedInfo = false;

}