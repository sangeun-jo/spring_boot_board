package com.sej.firstboard.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sej.firstboard.model.BoardDTO;
import com.sej.firstboard.model.Criteria;
import com.sej.firstboard.model.FileDTO;
import com.sej.firstboard.model.PageDTO;
import com.sej.firstboard.service.BoardService;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller 
public class BoardController {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    
    @Resource(name="com.sej.firstboard.service.BoardService")
    BoardService mBoardService; 

    @RequestMapping("/")
    private String main(Model model) throws Exception {
        return "redirect:/list?pageNum=1&amount=5"; 
    }

    @RequestMapping("/test/board")
    public void boardTest() throws Exception{

        for(int i=0;i<1000;i++) {
            BoardDTO board = new BoardDTO();
            board.setSubject("테스트 게시글 " + i);
            board.setContent("본문 테스트 입니다.");
            board.setReg_date(new Date());
            board.setWriter("관리자");
            board.setView(0);
            mBoardService.boardInsertService(board);
            log.info("게시글 " + i +"개 삽입됨 ");
        }
        
    }


    //게시판 리스트 화면 호출 
    @RequestMapping("/list") 
    private String boardList(Criteria cri, Model model) throws Exception{
        model.addAttribute("list", mBoardService.getListWithPaging(cri));
        int total = mBoardService.getTotalCount(cri);
        model.addAttribute("pageMaker", new PageDTO(cri, total));
        return "post/list"; 
    }
    
    @RequestMapping("/detail")
    private String boardDetail(HttpServletRequest request, Model model) throws Exception {
        int bno = Integer.parseInt(request.getParameter("bno"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum")); 
        model.addAttribute("detail", mBoardService.boardDetailService(bno)); 
        model.addAttribute("files", mBoardService.fileDetailService(bno));
        model.addAttribute("pageNum", pageNum);
        return "post/view"; 
    }

    @RequestMapping("/fileDown/{bno}")
    private void fileDown(@PathVariable int bno, HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.setCharacterEncoding("UTF-8");
        FileDTO fileDTO = mBoardService.fileDetailService(bno);
        
        //파일 업로드된 경로 
        try{
            String fileUrl = fileDTO.getFileUrl();
            fileUrl += "/";
            String savePath = fileUrl;
            String fileName = fileDTO.getFileName();
            
            //실제 내보낼 파일명 
            String oriFileName = fileDTO.getFileOriName();
            InputStream in = null;
            OutputStream os = null;
            File file = null;
            boolean skip = false;
            String client = "";
            
            //파일을 읽어 스트림에 담기  
            try{
                file = new File(savePath, fileName);
                in = new FileInputStream(file);
            } catch (FileNotFoundException fe) {
                skip = true;
            }
            
            client = request.getHeader("User-Agent");
            
            //파일 다운로드 헤더 지정 
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Description", "JSP Generated Data");
            
            if (!skip) {
                // IE
                if (client.indexOf("MSIE") != -1) {
                    response.setHeader("Content-Disposition", "attachment; filename=\""
                            + java.net.URLEncoder.encode(oriFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                    // IE 11 이상.
                } else if (client.indexOf("Trident") != -1) {
                    response.setHeader("Content-Disposition", "attachment; filename=\""
                            + java.net.URLEncoder.encode(oriFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                } else {
                    // 한글 파일명 처리
                    response.setHeader("Content-Disposition",
                            "attachment; filename=\"" + new String(oriFileName.getBytes("UTF-8"), "ISO8859_1") + "\"");
                    response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
                }
                response.setHeader("Content-Length", "" + file.length());
                os = response.getOutputStream();
                byte b[] = new byte[(int) file.length()];
                int leng = 0;
                while ((leng = in.read(b)) > 0) {
                    os.write(b, 0, leng);
                }
            } else {
                response.setContentType("text/html;charset=UTF-8");
                System.out.println("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");
            }
            in.close();
            os.close();
        } catch (Exception e) {
            System.out.println("ERROR : " + e.getMessage());
        }
        
    }

    //게시글 작성폼 호출 
    @RequestMapping("/insert") 
    private String boardInsertForm() {
        return "post/write"; 
    }

    @RequestMapping("/insertProc")
    private String boardInsertProc(BoardDTO boardDTO, HttpServletRequest request, @RequestPart MultipartFile files) throws Exception {
        FileDTO file = new FileDTO();  
        if (files.isEmpty()) { //업로드할 파일 없음
            mBoardService.boardInsertService(boardDTO);
        } else {
            String fileName = files.getOriginalFilename(); 
            String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
            File destinationFile; 
            String destinationFileName;  
            String fileUrl = request.getServletContext().getRealPath("WEB-INF/uploadFiles/"); 

            do {
                destinationFileName = RandomStringUtils.randomAlphabetic(32) + "." + fileNameExtension; 
                destinationFile = new File(fileUrl + destinationFileName); 
            } while (destinationFile.exists()); 
    
            destinationFile.getParentFile().mkdirs();
            files.transferTo(destinationFile);

            mBoardService.boardInsertService(boardDTO);

            file.setBno(boardDTO.getBno());
            file.setFileName(destinationFileName);
            file.setFileOriName(fileName);
            file.setFileUrl(fileUrl);
            
            mBoardService.fileInsertServcie(file); 
        }

        return "redirect:/detail?bno=" + boardDTO.getBno() +"&pageNum=1"; 
    }

    //게시글 수정폼 
    @RequestMapping("/update")
    private String boardUpdateForm(HttpServletRequest request, Model model) throws Exception { 
        int bno = Integer.parseInt(request.getParameter("bno"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        model.addAttribute("detail", mBoardService.boardDetailService(bno)); 
        model.addAttribute("files", mBoardService.fileDetailService(bno));
        model.addAttribute("pageNum", pageNum);
        return "post/update"; 
    }

    @RequestMapping("/updateProc")
    private String boardUpdateProc(BoardDTO oldBoard, HttpServletRequest request, @RequestPart MultipartFile files) throws Exception {
        int bno = oldBoard.getBno();

        if (files.isEmpty()) { //업로드할 파일 없음
        } else { //업로드할 파일 있음 

            String fileName = files.getOriginalFilename(); 
            String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
            File destinationFile; 
            String destinationFileName;  
            String fileUrl = request.getServletContext().getRealPath("WEB-INF/uploadFiles/"); 

            do {
                destinationFileName = RandomStringUtils.randomAlphabetic(32) + "." + fileNameExtension; 
                destinationFile = new File(fileUrl + destinationFileName); 
            } while (destinationFile.exists()); 
    
            destinationFile.getParentFile().mkdirs();
            files.transferTo(destinationFile);

            FileDTO fileDTO = mBoardService.fileDetailService(bno);

        
            if (fileDTO != null) { //이전에 업로드한 파일이 있는 경우 
               //TODO 있는 파일 삭제 

                fileDTO.setFileName(destinationFileName);
                fileDTO.setFileOriName(fileName);
                fileDTO.setFileUrl(fileUrl);
                
                mBoardService.fileUPdateService(fileDTO);
            } else { //이전에 업ㄹ드한 파일이 없는 경우 
                FileDTO file = new FileDTO(); 

                file.setBno(bno);
                file.setFileName(destinationFileName);
                file.setFileOriName(fileName);
                file.setFileUrl(fileUrl);
            
                mBoardService.fileInsertServcie(file);
            }
        }

        oldBoard.setSubject(oldBoard.getSubject());
        oldBoard.setWriter(oldBoard.getWriter());
        oldBoard.setContent(oldBoard.getContent());

        mBoardService.boardUpdateService(oldBoard); 
        
        return "redirect:/detail?bno=" + bno  + "&pageNum=" + request.getParameter("pageNum"); 
    }

    //게시글 삭제 
    @RequestMapping("/delete")
    private String boardDelete(HttpServletRequest request) throws Exception{
        int bno = Integer.parseInt(request.getParameter("bno"));
        int pageNum =  Integer.parseInt(request.getParameter("pageNum")); 
        
        //파일 삭제 
        FileDTO fileDetail = mBoardService.fileDetailService(bno); 
        if (fileDetail != null) { //파일이 존재 
            File file = new File(fileDetail.getFileUrl() + fileDetail.getFileName());
            file.delete();  //서버 파일 삭제 
            mBoardService.fileDeleteService(bno); //db 파일 삭제 
        } 
        
        //글 삭제 
        mBoardService.boardDeleteService(bno); 

        return "redirect:/list?pageNum=" + pageNum; 
    }

}
