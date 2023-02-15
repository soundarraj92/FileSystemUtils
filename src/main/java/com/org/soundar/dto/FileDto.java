package com.org.soundar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {

	private String filename;

	private String fileNameWithPath;

	private long sizeInBytes;

	private String sizeReadable;

}
