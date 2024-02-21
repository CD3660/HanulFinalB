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
	 * ���� �̸�
	 */
	private static final String APPLICATION_NAME = "hanul-b";
	/**
	 * Global instance of the JSON factory.
	 */
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	/**
	 * ��ū ���� ��ġ ����
	 */
	private static final String TOKENS_DIRECTORY_PATH = "tokens";

	/**
	 * Global instance of the scopes required by this quickstart. If modifying these
	 * scopes, delete your previously saved tokens/ folder.
	 *
	 * ���� ������ �����ؾ� ���� �б⸸ �Ұ��� ���⸻�Ѱ��� ����� ������ �� �ְ�, �Ź� ��ū ���� ó���ϴ°� �ƴ϶� StoredToken��
	 * ����Ǳ� ������ tokens������ �ִ� StoredCredential�� �����������
	 */
//    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_METADATA_READONLY);
	private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
	private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

	/**
	 * ������ ��ü ����
	 */
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		// ������ �������� ��������, Common �κ��� ������ �ּҰ� ��ġ�� ������ import, ����� Common���Ͽ� ����Ǿ�����.
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
	 * ���۵���̺꿡 ���� ���ε� ���� ���� �� ���̵� ��ȯ
	 */
	public String fileUpload(MultipartFile multipartFile) throws GeneralSecurityException, IOException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		// ��Ƽ��Ʈ ������ �̿��� ���� ��ü ����
		java.io.File f = new java.io.File(multipartFile.getOriginalFilename());
		multipartFile.transferTo(f);

		// ���� ����̺꿡�� �����ϴ� ���� ��ü ����
		File fileMetaData = new File();
		// ���ε� �ϴ� ���� �̸�
		fileMetaData.setName(multipartFile.getOriginalFilename());
		// ���ε� �� ���� id
		fileMetaData.setParents(Collections.singletonList("1McmcIzcUSQAIpdkV0KmI6k6YMe-y1tqq"));
		// ���� �ǹ� ������ ���� ��ü ����
		FileContent fileContent = new FileContent("image/jpeg", f);
		// ����̺� ���񽺸� �̿��Ͽ� ���۵���̺꿡 ���ε� �Ѵ�. File�� FileContent ��ü�� ���� ��� �����ϰ�, ���� �����
		// File�� ���·� ��ȯ�Ѵ�.
		File file = service.files().create(fileMetaData, fileContent).execute();
		// ������ ������ id ��ȯ
		return file.getId();
	}

	/**
	 * ���� ���̵�� ���۵���̺� ����� ���� ����
	 */
	public void fileDelete(String id) throws GeneralSecurityException, IOException {
		// Build a new authorized API client service.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		// id�� �޾ƿͼ� ���۵���̺� ���� ����
		service.files().delete(id).execute();
	}

	/**
	 * HTML�� img �±��� src�� �� �� �ִ� ������ URL�� ��ȯ
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
			if (responseCode == 200) { // ���� ȣ��
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			} else { // ���� �߻�
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
			// Exception �α�
		}
		return apiURL;
	}
	
	
	
	
	
	
	
	//���� ���Ͼ��ε�
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
	
	
	
	
	//���� ���Ͼ��ε�
	public String fileUpload (String category, MultipartFile file, HttpServletRequest request ) {
		
		String upload = "d://app/upload/" + category
				+ new SimpleDateFormat("/yyyy/MM/dd").format(new Date()); 
		
			
		//�ش� ������ �ִ��� Ȯ���ؼ� ������ ���ٸ� ���� �����
		java.io.File dir = new java.io.File( upload );
		if( ! dir.exists()	) dir.mkdirs();
		
		
		//���ε��� ���ϸ��� 
		String filename = UUID.randomUUID().toString() + "." + 
						StringUtils.getFilenameExtension( file.getOriginalFilename()) ;
		
		
		
		
		
		try {
			file.transferTo(new java.io.File(upload, filename));
			
		}catch(Exception e) {
			
		}
		
		
		return upload.replace("d://app/upload", fileURL(request)) + filename;
		
	}
	
	
	

	
	
	//���ϼ��񽺹��� URL
	public String fileURL(HttpServletRequest request) {
		StringBuffer url = new StringBuffer("http://");
		url.append(request.getServerName()).append(":");
		url.append(request.getServerPort());
		url.append("/file");
		
		return url.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
