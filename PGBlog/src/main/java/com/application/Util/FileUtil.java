package com.application.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	
	public static void fileupload(byte [] file,String filePath,String fileName) throws IOException
	{
		File targetFile=new File(filePath);
		if(targetFile.exists())
		{
			targetFile.mkdirs();
		}
		
		//二进制流写入
		FileOutputStream outputStream=new FileOutputStream(filePath+fileName);
		outputStream.write(file);
		outputStream.flush();
		outputStream.close();
		
	}

}
