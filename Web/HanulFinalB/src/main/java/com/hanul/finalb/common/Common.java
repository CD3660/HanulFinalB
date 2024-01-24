package com.hanul.finalb.common;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@PropertySource("classpath:db/conninfo.properties")
public class Common {
	/** 서버에 저장된 파일 삭제 */
	public void fileDelete(String filepath, HttpServletRequest req) {
		if (filepath != null) {
			File file = new File(filepath.replace(fileURL(req), "D://app/upload/"));
			if (file.exists())
				file.delete();
		}
	}
	
	public void fileDownload(String filePath ,String fileName, HttpServletRequest req, HttpServletResponse resp) {
		File file = new File(filePath.replace(fileURL(req), "D://app/upload/"));
		// 파일정보로부터 Mimetype을 알수 있다.
		try {
			String filename = URLEncoder.encode(fileName, "utf-8");
			resp.setContentType(req.getSession().getServletContext().getMimeType(filename));
			resp.setHeader("content-disposition", "attachment; filename=" + filename);

			FileCopyUtils.copy(new FileInputStream(file), resp.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String fileUpload(String category, MultipartFile file, HttpServletRequest req) {
		String upload = "D://app/upload/" + category + new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());

		File dir = new File(upload);
		if (!dir.exists())
			dir.mkdirs();

		String filename = UUID.randomUUID().toString() + "."
				+ StringUtils.getFilenameExtension(file.getOriginalFilename());

		try {
			file.transferTo(new File(upload, filename));
		} catch (Exception e) {
			e.getMessage();
		}

		return upload.replace("D://app/upload/", fileURL(req)) + filename;
	}

	public String fileURL(HttpServletRequest req) {
		StringBuilder url = new StringBuilder();
		url.append("http://");
		url.append(req.getServerName()).append(":");
		url.append(req.getServerPort());
		url.append("/file/");

		return url.toString();
	}

}
