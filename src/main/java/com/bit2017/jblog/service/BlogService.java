package com.bit2017.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bit2017.jblog.repository.BlogDao;
import com.bit2017.jblog.vo.BlogVo;
import com.bit2017.jblog.vo.PostVo;


@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	private static final String SAVE_PATH = "/gallery-upload";
	private static final String URL = "gallery/images/";
	
	public boolean join(BlogVo blogVo){
		boolean result = blogDao.insert(blogVo);
		return result;
	}

	public boolean blogWrite(PostVo postVo) {
		return blogDao.write(postVo);
	}

	public PostVo getPost(String usersId, Long pno) {
		if(pno == null){
			pno = getMaxPno(usersId);
			
		}
			return blogDao.getPost(pno);
	}

	public long getMaxPno(String blogId){
		
		return blogDao.getMaxPno(blogId);
	}

	public List<BlogVo> getList(BlogVo blogVo) {
		return blogDao.getList(blogVo);
	}
	public List<PostVo> getPostList(String blogId) {
		return blogDao.getPostList(blogId);
	}
	public boolean update(BlogVo blogVo, MultipartFile file) {
		String url = restore(file);
		blogVo.setLogo(url);
		return blogDao.update(blogVo);
		
	}

	public String restore(MultipartFile file) {
		String url = "";
		try{
		if(file.isEmpty() == true) {
			return null;
		}
		
		String originalFileName = file.getOriginalFilename();
		Long fileSize = file.getSize();
		String extName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1, originalFileName.length());
		String saveFileName = generateSaveFileName(extName);
		url = URL + saveFileName;
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@" + originalFileName);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@" + fileSize);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@" + extName);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@" + saveFileName);
		
		writeFile(file, saveFileName);
		} catch(IOException e) {
			new RuntimeException("upload file:" + e);
		}
		return url;
	}
	
	private void writeFile(MultipartFile file, String saveFileName) throws IOException{
		byte[] data = file.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(data);
		fos.close();
	}

	public String generateSaveFileName(String extName) {
		String fileName = "";
		
		Calendar calender = Calendar.getInstance();
		fileName += calender.get(Calendar.YEAR);
		fileName += calender.get(Calendar.MONTH);
		fileName += calender.get(Calendar.DATE);
		fileName += calender.get(Calendar.HOUR);
		fileName += calender.get(Calendar.MINUTE);
		fileName += calender.get(Calendar.SECOND);
		fileName += calender.get(Calendar.MILLISECOND);
		fileName += ("." + extName);
		
		return fileName;
	}

	


	
	

	
}
