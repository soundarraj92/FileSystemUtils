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
public class InputDto {

	@JsonProperty(value = "fsPath", required = true)
	private String fsPath;

	@JsonProperty(value = "fileTypeExclusions", required = false)
	private String fileTypeExclusions;

	@JsonProperty(value = "detailedInfo", required = false, defaultValue = "false")
	private boolean detailedInfo = false;
}
