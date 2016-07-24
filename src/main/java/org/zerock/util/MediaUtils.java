package org.zerock.util;

import com.google.common.collect.Maps;
import org.springframework.http.MediaType;

import java.util.Map;

/**
 * @author wayne
 * @version 1.0
 */
public class MediaUtils {
	private static Map<String, MediaType> mediaTypeMap;

	static {
		mediaTypeMap = Maps.newHashMap();
		mediaTypeMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaTypeMap.put("GIF", MediaType.IMAGE_GIF);
		mediaTypeMap.put("PNG", MediaType.IMAGE_PNG);
	}

	public static MediaType getMediaType(String type) {
		return mediaTypeMap.get(type);
	}
}
