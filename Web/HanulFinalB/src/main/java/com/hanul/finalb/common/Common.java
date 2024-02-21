package com.hanul.finalb.common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
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
	 * 어플 이름
	 */
	private static final String APPLICATION_NAME = "hanul-b";
	/**
	 * Global instance of the JSON factory.
	 */
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	/**
	 * 토큰 저장 위치 정보
	 */
	private static final String TOKENS_DIRECTORY_PATH = "tokens";

	/**
	 * Global instance of the scopes required by this quickstart. If modifying these
	 * scopes, delete your previously saved tokens/ folder.
	 *
	 * 여기 권한을 수정해야 파일 읽기만 할건지 쓰기말한거지 등등을 결정할 수 있고, 매번 토큰 새로 처리하는게 아니라 StoredToken이
	 * 저장되기 때문에 tokens폴더에 있는 StoredCredential를 삭제해줘야함
	 */
//    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_METADATA_READONLY);
	private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
	private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

	/**
	 * 인증서 객체 생성
	 */
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		// 인증서 파일정보 가져오기, Common 부분은 인증서 주소가 위치한 파일을 import, 현재는 Common파일에 저장되어있음.
		InputStream in = Common.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline").build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
		// returns an authorized Credential object.
		return credential;
	}

	/**
	 * 구글드라이브에 파일 업로드 파일 저장 후 아이디 반환
	 */
	public String fileUpload(MultipartFile multipartFile) throws GeneralSecurityException, IOException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

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
	 * HTML의 img 태그의 src에 들어갈 수 있는 형태의 URL을 반환
	 */
	public String fileURL(String id) {
		return "https://drive.google.com/thumbnail?sz=w640&id=" + id;
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
	
	
	
	
	
	
	
	//다중 파일업로드
	public ArrayList<FileVO> multipleFileUpload(String category, MultipartFile[] files,
			HttpServletRequest request) {

		ArrayList<FileVO> list = null;
		for( MultipartFile file: files ) {
			if( file.isEmpty() ) continue;
			if( list== null ) list = new ArrayList<FileVO>();
			FileVO vo = new FileVO();
			vo.setFilename( file.getOriginalFilename() );
			vo.setFilepath( fileUpload( category, file, request ));
			list.add(vo);
		}
		return list;
	}
	
	
	
	
	//단일 파일업로드
	public String fileUpload (String category, MultipartFile file, HttpServletRequest request ) {
		
		String upload = "d://app/upload/" + category
				+ new SimpleDateFormat("/yyyy/MM/dd").format(new Date()); 
		
			
		//해당 폴더가 있는지 확인해서 폴더가 없다면 폴더 만들기
		java.io.File dir = new java.io.File( upload );
		if( ! dir.exists()	) dir.mkdirs();
		
		
		//업로드할 파일명을 
		String filename = UUID.randomUUID().toString() + "." + 
						StringUtils.getFilenameExtension( file.getOriginalFilename()) ;
		
		
		
		
		
		try {
			file.transferTo(new java.io.File(upload, filename));
			
		}catch(Exception e) {
			
		}
		
		
		return upload.replace("d://app/upload", fileURL(request)) + filename;
		
	}
	
	
	

	
	
	//파일서비스받을 URL
	public String fileURL(HttpServletRequest request) {
		StringBuffer url = new StringBuffer("http://");
		url.append(request.getServerName()).append(":");
		url.append(request.getServerPort());
		url.append("/file");
		
		return url.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
