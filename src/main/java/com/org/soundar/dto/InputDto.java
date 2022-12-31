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

	@JsonProperty(value = "fsPath")
	private String fsPath;

	@JsonProperty(value = "detailedInfo", defaultValue = "false")
	private boolean detailedInfo = false;
}
