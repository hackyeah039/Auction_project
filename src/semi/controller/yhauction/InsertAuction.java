package semi.controller.yhauction;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/InsertAuction.do")
public class InsertAuction extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String encoding = "utf-8";
		req.setCharacterEncoding(encoding);
		// 세션에 저장된 m_num값 가져오기
		//int m_num = Integer.parseInt((String)req.getAttribute("m_num"));
		//변수
		int c_des = 0;
		String a_title = null;
		String a_content = null;
		int a_startbid = 0;
		int a_bidunit = 0;
		int a_condition = 0;
		String a_startdate = null;
		String a_enddate = null;
		String s_way = null;
		int s_price = 0;
		Long account = 0L;
		String sel_number1 = null;
		int sel_number = 0;
		ArrayList<String> fList;

		// 파일 업로드 저장소 위치, 나중에 cp + 폴더명 하면 될듯
		File currentPath = new File("C:\\file_repo");
		if(currentPath.mkdir()) {
			System.out.println("해당디렉토리가 생성되었습니다.");			
		} else {
			System.out.println("해당디렉토리가 존재합니다.");
		}
		// 최대 업로드 파일 크기와 파일 저장소 위치 지정
		DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 1024 * 5, currentPath);

		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// List -> ArrayList와 같다고 생각하면 됨.
			// parseRequest -> request객체에서 매개변수를 List로 가져옴
			List items = upload.parseRequest(req);
			for (int i = 0; i < items.size(); i++) {
				// 파일 업로드창에서 업로드된 항목들을 하나씩 가져옴
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) { // 전송된 name value 값 출력
					// 변수 명 일치 할때 값을 담아주기 -> 저장해서 가공할 수 있도록
					switch (fileItem.getFieldName()) {
					case "c_des":
						c_des = Integer.parseInt(fileItem.getString(encoding));
						break;
					case "a_title":
						a_title = fileItem.getString(encoding);
						break;
					case "a_content":
						a_content = fileItem.getString(encoding);
						break;
					case "a_startbid":
						a_startbid = Integer.parseInt(fileItem.getString(encoding));
						break;
					case "a_bidunit":
						a_bidunit = Integer.parseInt(fileItem.getString(encoding));
						break;
					case "a_condition":
						a_condition = Integer.parseInt(fileItem.getString(encoding));
						break;
					case "a_startdate":
						a_startdate = fileItem.getString(encoding);
						break;
					case "a_enddate":
						a_enddate = fileItem.getString(encoding);
						String a = a_enddate.replaceAll("/", "-");

						break;
					case "s_way":
						s_way = fileItem.getString(encoding);
						break;
					case "s_price":
						s_price = Integer.parseInt(fileItem.getString(encoding));
						break;
					case "account":
						account = Long.parseLong(fileItem.getString(encoding));
						break;
					case "sel_number":
						sel_number1 = fileItem.getString(encoding);
						// 새로등록할경우 hidden field값이기때문에 변경해줘야함. -> DB에 입력시에는 시퀀스 사용.
						if(sel_number1 == "" || sel_number1.equals("")) {
							sel_number1 = "0";
							sel_number = Integer.parseInt(sel_number1);
						}
						break;
					default:
						System.out.println("오류발생");
						break;
					}
				} else { // 파일인경우
					if (fileItem.getSize() > 0) { // 첨부된 파일이 있을 경우
/*						삭제해도 문제는 없을 듯
						마지막 \\위치의 번호를 구해옴. -> 순수파일명 앞자리까지 자르기 위해 
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}

						// 순수 파일명 얻어오기
						String fileName = fileItem.getName().substring(idx + 1);
*/						String fileName = fileItem.getName();
						
// 기존					File uploadFile = new File(currentPath + "\\" + fileName); // 이름 포함한 경로
						fList = new ArrayList<String>(); // 파일 경로 담을 배열
						File uploadFile = new File(currentPath, fileName);
						// 업로드 폴더에 파일네임과 같은 파일이 존재시
						if(uploadFile.exists()) {
							// 파일명에 숫자 조합
							for(int j = 0 ; true ; j++) { // for문 무한루프 - 조건이 참일경우
								uploadFile = new File(currentPath, j+fileName);								
								//파일명에 숫자 조합한 파일이 존재하지 않으면
								if(!uploadFile.exists()) {
									//파일 이름을 수정하고 for문 탈출
									fileName = j + fileName;
									break;
								}
							}
						}
						fList.add(uploadFile.getAbsolutePath()); // 여러개 파일일경우를 대비하여 ArrayList에 저장
						fileItem.write(uploadFile); // 지정한 저장소 위치에 저장
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * @Override protected void service(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException { //ContextPath 받기 - 홈 컨트롤러에서
	 * application 영역으로 선언하므로 String cp = req.getContextPath(); ServletContext
	 * application = getServletConfig().getServletContext(); //이미지 저장 폴더 경로 얻어오기 -
	 * 폴더 미리 생성해 둬야함. - web 콘텐츠 하위 폴더 String upload =
	 * application.getRealPath("/upload"); System.out.println(upload); //0. 인코딩 설정
	 * req.setCharacterEncoding("utf-8"); //1.폼에서 넘어온 값 들 다 변수에 담기(MultipartRequest)
	 * MultipartRequest mr = new MultipartRequest(req, upload, 1024*1024*10,
	 * "utf-8", new DefaultFileRenamePolicy()); //경매번호는 시퀀스 a_num String a_title =
	 * mr.getParameter("a_title"); String a_content= mr.getParameter("a_content");
	 * String a_condition = mr.getParameter("a_condition"); //글등록일자는 sysdate
	 * a_regdate String a_startdate = mr.getParameter("a_startdate"); String
	 * a_enddate = mr.getParameter("a_enddate"); // 조회수 0 a_check int c_num =
	 * Integer.parseInt(mr.getParameter("c_num")); // 찜 0 a_jjim // 경매상태는 로직 처리
	 * sysdate랑 startdate랑 비교해서 처리 // 0 : 입찰전 1 : 입찰중 2 : 거래중 3 : 거래완료 int
	 * a_startbid = Integer.parseInt(mr.getParameter("a_startbid")); int a_bidunit =
	 * Integer.parseInt(mr.getParameter("a_bidunit")); int s_way =
	 * Integer.parseInt(mr.getParameter("s_way")); int s_price =
	 * Integer.parseInt(mr.getParameter("s_price")); int account =
	 * Integer.parseInt(mr.getParameter("account")); // 회원번호는 세션 아이디값을 받아서 출력 + 0514
	 * 추가 세션에 회원아이디랑, 번호 같이 넘겨 줄거임. //2. 세션 회원번호 값을 받아옴. HttpSession session =
	 * req.getSession(); int m_num =
	 * Integer.parseInt((String)session.getAttribute("m_num")); //3. 회원번호를 받아올 메소드
	 * 생성 /* SellerDao sdao = SellerDao.getInstance(); int m_num = sdao.getMnum(id);
	 * //4. seller테이블에 인서트 SellerVo svo = new sellerVo(account, m_num, sel_num) }
	 */
}
