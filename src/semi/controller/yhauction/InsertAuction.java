package semi.controller.yhauction;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import semi.dao.yh.AuctionDao;
import semi.dao.yh.CategoryDao;
import semi.dao.yh.ImgDao;
import semi.dao.yh.SellerDao;
import semi.vo.yh.AuctionVo;
import semi.vo.yh.CategoryVo;
import semi.vo.yh.IMGVo;
import semi.vo.yh.SellerVo;
import semi.vo.yh.ShipVo;

@WebServlet("/InsertAuction.do")
public class InsertAuction extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("file", "/Auction/insertAuction.jsp");
		
		
		//카테고리 리스트 넘겨주기 
		CategoryDao cdao = CategoryDao.getInstance();
		ArrayList<CategoryVo> clist = cdao.ShowList();
		req.setAttribute("clist", clist);

		req.getRequestDispatcher("/main_sh/layoutTest.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String encoding = "utf-8";
		req.setCharacterEncoding(encoding);
		// 세션에 저장된 m_num값 가져오기
		//int m_num = Integer.parseInt((String)req.getAttribute("m_num"));
		int m_num = 1;
		//변수
		int c_num = 0;
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
		int bidstatus = 0;
		ArrayList<String> fList = null;
		int n = 0; // 데이터 입력이 잘 되었는지 확인하기 위한 변수 

		// 파일 업로드 저장소 위치, 나중에 cp + 폴더명 하면 될듯
		File currentPath = new File("C:\\Users\\JHTA\\git\\semiproject_auctionsite\\WebContent\\img");
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
			fList = new ArrayList<String>(); // 파일 경로 담을 배열 - 밑에서 자꾸 초기화되서 위로 올림
			for (int i = 0; i < items.size(); i++) {
				// 파일 업로드창에서 업로드된 항목들을 하나씩 가져옴
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) { // 전송된 name value 값 출력
					// 변수 명 일치 할때 값을 담아주기 -> 저장해서 가공할 수 있도록
					switch (fileItem.getFieldName()) {
					case "c_num":
						c_num = Integer.parseInt(fileItem.getString(encoding));
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
						System.out.println(a_startdate + "처음 변환");
						break;
					case "a_enddate":
						a_enddate = fileItem.getString(encoding);
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
						// 새로등록할경우 hidden field값이 없기때문에 변경해줘야함. -> DB에 입력시에는 시퀀스 사용.
						if(sel_number1 == "" || sel_number1.equals("")) {
							sel_number1 = "0";
							sel_number = Integer.parseInt(sel_number1);
						} else {
							sel_number = Integer.parseInt(sel_number1);
						}
						break;
					default:
						System.out.println("오류발생");
						break;
					}
				} else { // 파일인경우
					
					if (fileItem.getSize() > 0) { // 첨부된 파일이 있을 경우

						String fileName = fileItem.getName();
						
// 기존					File uploadFile = new File(currentPath + "\\" + fileName); // 이름 포함한 경로
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
						// 여러개 파일일경우를 대비하여 ArrayList에 저장
						fList.add(uploadFile.getAbsolutePath());
						// 지정한 저장소 위치에 저장
						fileItem.write(uploadFile); 
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 경매상태 구하기 (형식에 해당하는 값들이 다 있어야 에러 안남.)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");					
		Calendar cal = Calendar.getInstance();
		String currTime = sdf.format(cal.getTime());
		try {
			Date today = sdf.parse(currTime);
			System.out.println(today + "  1");
			Date startdate = sdf.parse(a_startdate);
			System.out.println(startdate + "  2");
			if(today.getTime() < startdate.getTime()) {
				bidstatus = 0;
			} else {
				bidstatus = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 테이블에 입력 할 값들 vo에 담기 
		SellerVo sevo = new SellerVo(account, m_num, sel_number);
		AuctionVo avo = new AuctionVo(0, a_title, a_content, a_condition, null, a_startdate, a_enddate, 0, c_num, 0, sel_number, bidstatus, a_startbid, a_bidunit);
		ShipVo shvo = new ShipVo(0, s_way, s_price, 0);
		
		AuctionDao adao = AuctionDao.getInstance();
		n = adao.InsertTables(sel_number, sevo, avo, shvo, fList);
		// DB저장 결과 페이지로 이동
		if(n>0) {
			//req.setAttribute("code","success");
		}else {
			//req.setAttribute("code","fail");
		}
		
		//req.setAttribute("file", "/Auction/result.jsp");
		
		//req.getRequestDispatcher("/main_sh/layoutTest.jsp").forward(req, resp);
		resp.sendRedirect(req.getContextPath() + "/main_sh/layoutTest.jsp");
	}
}
