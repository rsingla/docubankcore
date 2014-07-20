package com.doc.banks.util;

public class ByteConvertor {

	/**
	 * 1000 bytes - 1 KB 1000,000 bytes - 1 MB 1000,000,000 - 1 GB
	 */

	public static String byteValue(Long byteVal) {
		if (byteVal > 1000) {
			return byteVal / 1000 + " KB";
		} else if (byteVal > 1000000) {
			return byteVal / 1000000 + " MB";
		} else if (byteVal > 1000000000) {
			return byteVal / 1000000000 + " GB";
		} else {
			return byteVal + " bytes";
		}
	}

}
