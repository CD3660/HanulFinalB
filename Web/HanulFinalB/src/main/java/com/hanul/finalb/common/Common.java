package com.hanul.finalb.common;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;



@Service
@PropertySource("classpath:db/conninfo.properties")
public class Common {
	/**
	   * Application name.
	   */
	  private static final String APPLICATION_NAME = "hanul-finalb";
	  /**
	   * Global instance of the JSON factory.
	   */
	  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	  /**
	   * Directory to store authorization tokens for this application.
	   */
	  private static final String TOKENS_DIRECTORY_PATH = "D:/tokens";

	  /**
	   * Global instance of the scopes required by this quickstart.
	   * If modifying these scopes, delete your previously saved tokens/ folder.
	   */
	  private static final List<String> SCOPES =
	      Collections.singletonList(DriveScopes.DRIVE);
	  private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

	  /**
	   * Creates an authorized Credential object.
	   *
	   * @param HTTP_TRANSPORT The network HTTP Transport.
	   * @return An authorized Credential object.
	   * @throws IOException If the credentials.json file cannot be found.
	   */
	  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
	      throws IOException {
	    // Load client secrets.
	    InputStream in = TestGoogle.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
	    if (in == null) {
	      throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
	    }
	    GoogleClientSecrets clientSecrets =
	        GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

	    // Build flow and trigger user authorization request.
	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
	        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
	        .setAccessType("offline")
	        .build();
	    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
	    Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	    //returns an authorized Credential object.
	    return credential;
	  }

	/**
	 * 구글드라이브에 파일 업로드 파일 저장 후 아이디 반환
	 */
	public String fileUpload(MultipartFile multipartFile) throws GeneralSecurityException, IOException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
	    Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
	        .setApplicationName(APPLICATION_NAME)
	        .build();
		/*
		 * GoogleCredentials credentials =
		 * GoogleCredentials.getApplicationDefault().createScoped(Arrays.asList(
		 * DriveScopes.DRIVE)); HttpRequestInitializer requestInitializer = new
		 * HttpCredentialsAdapter(credentials); Drive service = new Drive.Builder(new
		 * NetHttpTransport(), GsonFactory.getDefaultInstance(), requestInitializer)
		 * .setApplicationName("hanul-finalb").build();
		 */
		

		// 멀티파트 파일을 이용해 파일 객체 생성
		java.io.File f = new java.io.File(multipartFile.getOriginalFilename());
		multipartFile.transferTo(f);

		// 구글 드라이브에서 제공하는 파일 객체 생성
		File fileMetaData = new File();
		// 업로드 하는 파일 이름
		fileMetaData.setName(multipartFile.getOriginalFilename());
		// 업로드 할 폴더 id
		fileMetaData.setParents(Collections.singletonList("1McmcIzcUSQAIpdkV0KmI6k6YMe-y1tqq"));
		// 파일 실물 정보를 담을 객체 생성
		FileContent fileContent = new FileContent("image/jpeg", f);
		// 드라이브 서비스를 이용하여 구글드라이브에 업로드 한다. File과 FileContent 객체를 같이 묶어서 전송하고, 전송 결과를
		// File의 형태로 반환한다.
		File file = service.files().create(fileMetaData, fileContent).execute();
		// 저장한 파일의 id 반환
		return file.getId();
	}

	/**
	 * 파일 아이디로 구글드라이브 저장된 파일 삭제
	 */
	public void fileDelete(String id) throws GeneralSecurityException, IOException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		// id를 받아와서 구글드라이브 파일 삭제
		service.files().delete(id).execute();
	}
	/**
	 * 파일 아이디로 구글드라이브 저장된 파일 다운로드
	 */
	public void fileDownload(String id, String filename, HttpServletRequest req, HttpServletResponse resp) throws GeneralSecurityException, IOException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		ByteArrayOutputStream byteArrayOutputStream;
		try {
			OutputStream outputStream = new ByteArrayOutputStream();

			service.files().get(id).executeMediaAndDownloadTo(outputStream);

			byteArrayOutputStream = (ByteArrayOutputStream) outputStream;
		} catch (GoogleJsonResponseException e) {
			// TODO(developer) - handle error appropriately
			System.err.println("Unable to move file: " + e.getDetails());
			throw e;
		}
		java.io.File dir = new java.io.File("/app/temp/");
		if (!dir.exists())
			dir.mkdirs();
		//파일 경로는 서버 드라이브 루트부터 시작(하는듯)
		java.io.File filePath = new java.io.File("/app/temp/", id+"_"+filename);
		FileOutputStream fos = new FileOutputStream(filePath);
		fos.write(byteArrayOutputStream.toByteArray());
		fos.flush();
		fos.close();
		filename = URLEncoder.encode(filename, "utf-8");
		resp.setContentType(req.getSession().getServletContext().getMimeType(filename));
		resp.setHeader("content-disposition", "attachment; filename=" + filename);
		FileCopyUtils.copy(new FileInputStream(filePath), resp.getOutputStream());
		filePath.delete();
		
	}
	

	/**
	 * HTML의 img 태그의 src에 들어갈 수 있는 형태의 URL을 반환
	 */
	public String fileURL(String id) {
		return "https://drive.google.com/thumbnail?sz=w640&id=" + id;
	}
	/**
	 * URL에서 fileId 추출하는 코드
	 */
	public String fileId(String url) {
		return url.replace("https://drive.google.com/thumbnail?sz=w640&id=", "");
	}
	
	public String requestAPI(String apiURL) {
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
			}
			String inputLine;
			StringBuilder res = new StringBuilder();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				apiURL = res.toString();
			}
		} catch (Exception e) {
			// Exception 로깅
		}
		return apiURL;
	}

	/**
	 * POST로 API 요청 보내기 
	 */
	public String requestAPI(String apiURL, String postData) {
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Content-Length", Integer.toString(postData.length()));
            con.setUseCaches(false);
            try (DataOutputStream dos = new DataOutputStream(con.getOutputStream())) {
                dos.writeBytes(postData);
            }
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
			}
			String inputLine;
			StringBuilder res = new StringBuilder();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				apiURL = res.toString();
			}
		} catch (Exception e) {
			// Exception 로깅
			System.out.println(e.getMessage());
		}
		return apiURL;
	}
	/**
	 * GET으로 portone API 요청 보내기 
	 */
	public String portone(String apiURL, String token) {
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "Bearer "+token);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
			}
			String inputLine;
			StringBuilder res = new StringBuilder();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				apiURL = res.toString();
			}
		} catch (Exception e) {
			// Exception 로깅
			System.out.println(e.getMessage());
		}
		return apiURL;
	}
	/**
	 * POST로 portone API 요청 보내기 
	 */
	public String portone(String apiURL, String postData, String token) {
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Content-Length", Integer.toString(postData.length()));
            con.setRequestProperty("Authorization", "Bearer "+token);
            con.setUseCaches(false);
            try (DataOutputStream dos = new DataOutputStream(con.getOutputStream())) {
                dos.writeBytes(postData);
            }
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
			}
			String inputLine;
			StringBuilder res = new StringBuilder();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				apiURL = res.toString();
			}
		} catch (Exception e) {
			// Exception 로깅
			System.out.println(e.getMessage());
		}
		return apiURL;
	}
	
	
	
	
	
	
	
	
	 //다중 파일업로드
	public ArrayList<FileVO> multipleFileUpload(String category,
				MultipartFile[] files, HttpServletRequest request) throws GeneralSecurityException, IOException {
	  
	 ArrayList<FileVO> list = null;
	 for( MultipartFile file: files ) {
		 if( file.isEmpty() ) continue;
		 
		 if( list== null ) list = new ArrayList<FileVO>();
		 
		 FileVO vo = new FileVO();
		 vo.setFilename( file.getOriginalFilename() );
		 String file_id = fileUpload(file);
		 vo.setFile_id(file_id);
		 /* vo.setFilepath;(fileURL(file_id)); */
		 list.add(vo);
		 }
	 		
	 return list;
	 }
	
	
	
	
	
	
	
	
	/* 구굴 드라이버로 사용
	 * 
	 * 
	 * //단일 파일업로드 public String fileUpload (String category, MultipartFile file,
	 * HttpServletRequest request ) {
	 * 
	 * String upload = "d://app/upload/" + category + new
	 * SimpleDateFormat("/yyyy/MM/dd").format(new Date());
	 * 
	 * 
	 * //해당 폴더가 있는지 확인해서 폴더가 없다면 폴더 만들기 java.io.File dir = new java.io.File( upload
	 * ); if( ! dir.exists() ) dir.mkdirs();
	 * 
	 * 
	 * //업로드할 파일명을 String filename = UUID.randomUUID().toString() + "." +
	 * StringUtils.getFilenameExtension( file.getOriginalFilename()) ;
	 * 
	 * 
	 * 
	 * 
	 * 
	 * try { file.transferTo(new java.io.File(upload, filename));
	 * 
	 * }catch(Exception e) {
	 * 
	 * }
	 * 
	 * 
	 * return upload.replace("d://app/upload", fileURL(request)) + filename;
	 * 
	 * }
	 */
	
	
	

	
	
//	구굴 드라이버로 사용
//
//	//파일서비스받을 URL
//	public String fileURL(HttpServletRequest request) {
//	  StringBuffer url = new StringBuffer("http://");
//	  url.append(request.getServerName()).append(":");
//	  url.append(request.getServerPort());
//	  url.append("/file");
//	  
//	  return url.toString(); 
//	}
	
	
	
	
//	구굴 드라이버로 사용
//	
//	파일다운로드
//	public void fileDownload(String filename, String filepath, HttpServletRequest request,
//			HttpServletResponse response) throws FileNotFoundException, IOException {
//		
//	
//	filepath = filepath.replace( fileURL(request), "d://app/upload");
//	java.io.File file = new java.io.File( filepath );
//	
//	
//	response.setContentType( request.getSession().getServletContext().getMimeType(filename));
//	
//	filename = URLEncoder.encode(filename, "utf-8");
//	
//	response.setHeader("content-disposition", "attachment; filename=" + filename);
//	FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
//	
//	}
	
	
	
	
}
