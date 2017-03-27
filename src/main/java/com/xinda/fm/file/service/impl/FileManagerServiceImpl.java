package com.xinda.fm.file.service.impl;

import com.github.pagehelper.PageHelper;
import com.xinda.fm.file.dto.FileManagerDto;
import com.xinda.fm.file.mapper.FileManagerMapper;
import com.xinda.fm.file.service.IFileManagerService;
import com.xinda.system.sys.contant.BaseConstants;
import com.xinda.system.sys.exception.FileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * 文件管理实现类.
 *
 * @Author Coundy.
 * @Date 2017/3/21 17:33
 */
@Service
@Transactional
public class FileManagerServiceImpl implements IFileManagerService {

    private Logger logger = LoggerFactory.getLogger(FileManagerServiceImpl.class);

    @Autowired
    private FileManagerMapper fileManagerMapper;

    @Autowired
    private CommonsMultipartResolver multipartResolver;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void uploadFile(HttpServletRequest request, HttpServletResponse response, Integer salesOrderId)
            throws IOException, FileException {
        logger.info("Starting upload file ...");
        // 保存上传文件信息
        String orderNo = request.getParameter("orderNumber");
        // 多文件上传转换
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    String fileName = file.getOriginalFilename();
                    if (!StringUtils.isEmpty(fileName.trim())) {
                        File dir = new File(BaseConstants.FILE_SAVE_DIR + orderNo);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        String filePath = BaseConstants.FILE_SAVE_DIR + orderNo + File.separator + fileName;
                        // 获取输出流
                        FileOutputStream fos = new FileOutputStream(filePath);
                        // 获取输入流
                        InputStream fis = file.getInputStream();
                        byte[] buffer = new byte[1024 * 1024];
                        int byteNum = 0;
                        while ((byteNum = fis.read(buffer)) != -1) {
                            fos.write(buffer, 0, byteNum);
                            fos.flush();
                        }
                        fos.close();
                        fis.close();
                        FileManagerDto fileManagerDto = new FileManagerDto(fileName, filePath, salesOrderId, file.getContentType(),
                                file.getSize());
                        // 保存File信息
                        saveFileOperateInfo(fileManagerDto);
                    }
                }
            }
        }
        logger.info("Ended upload file ...");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Starting download file ...");
        Integer fileId = Integer.valueOf(request.getParameter("fileId"));
        FileManagerDto fileManagerDto = fileManagerMapper.selectByPrimaryKey(fileId);
        if (fileManagerDto != null && !StringUtils.isEmpty(fileManagerDto.getFilePath())) {
            File file = new File(fileManagerDto.getFilePath());
            if (file.exists()) {
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                String downLoadPath = file.getPath();
                // 文件名包含空格的话，浏览器会截断空格后内容，必须得用引号将filename引起来
                // URLEncoder.encode(downLoadPath.substring(downLoadPath.lastIndexOf("/")
                // + 1), "UTF8");
                String newFileName = "\"" + fileManagerDto.getFileName() + "\"";
                response.setHeader("Content-disposition",
                        "attachment; filename=" + new String(newFileName.getBytes("UTF-8"), "ISO8859-1"));
                response.setHeader("Content-Length", String.valueOf(fileManagerDto.getFileLength()));

                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downLoadPath));
                BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
                byte[] buff = new byte[1024 * 1024];
                int bytesRead = 0;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
                bis.close();
                bos.close();
            }
        }
        logger.info("Ended download file ...");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public FileManagerDto saveFileOperateInfo(FileManagerDto fileManagerDto) throws FileException {
        fileManagerMapper.insertSelective(fileManagerDto);
        return fileManagerDto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<FileManagerDto> queryFileOperate(int pageNum, int pageSize, FileManagerDto fileManagerDto) {
        PageHelper.startPage(pageNum, pageSize);
        return fileManagerMapper.queryFileOperateByParms(fileManagerDto);
    }

    @Override
    public int deleteByPrimaryKey(Integer fileId) throws FileException {
        return fileManagerMapper.deleteByPrimaryKey(fileId);
    }

}
