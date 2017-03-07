package com.benny.reader;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Marvin on 2017-03-07.
 */

public class ZipHelper {
    static final int BUFFER = 2048;

    public static void ZipDecode(Context context, String zipfile) throws IOException {
        ZipFile zipFile = new ZipFile(zipfile);
        Enumeration enu = zipFile.entries();
        while(enu.hasMoreElements()){
            ZipEntry entry = (ZipEntry) enu.nextElement();
            if(entry.isDirectory()){
//                new File(outpath + entry.getName()).mkdir();
                continue;
            }
            //File file = new File(outpath + entry.getName());
            //File parent = file.getParentFile();
//            if(parent==null || (!parent.exists())){
//                parent.mkdir();
//            }
            BufferedInputStream is = new BufferedInputStream(zipFile.getInputStream(entry));
            FileOutputStream fos = context.openFileOutput(entry.getName(), Context.MODE_PRIVATE);
            BufferedOutputStream os = new BufferedOutputStream(fos, BUFFER);

            int count = 0;
            byte[] data = new byte[BUFFER];
            while((count = is.read(data, 0, BUFFER)) != -1){
                os.write(data, 0, BUFFER);
            }
            os.flush();
            os.close();
            is.close();
        }
        zipFile.close();
    }
}
