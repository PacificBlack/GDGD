// Copyright 2018-2020 Twitter, Inc.
// Licensed under the MoPub SDK License Agreement
// http://www.mopub.com/legal/sdk-license-agreement/

package com.mopub.common.util;

import java.io.File;

public class Files {
    public static File createDirectory(String absolutePath) {
        if (absolutePath == null) {
            return null;
        }

        File directory = new File(absolutePath);

        if (directory.exists() && directory.isDirectory() ||
                directory.mkdirs() && directory.isDirectory()) {
            return directory;
        }

        return null;
    }

    public static int intLength(File file) {
        if (file == null) {
            return 0;
        }

        long length = file.length();

        if (length < Integer.MAX_VALUE) {
            return (int) length;
        } else {
            return Integer.MAX_VALUE;
        }
    }
}
