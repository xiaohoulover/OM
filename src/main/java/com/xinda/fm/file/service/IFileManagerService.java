package com.xinda.fm.file.service;

import com.xinda.fm.file.dto.FileManagerDto;
import com.xinda.fm.file.exception.FileException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 文件管理接口.
 *
 * @Author Coundy.
 * @Date 2017/3/21 17:33
 */
public interface IFileManagerService {

    /**
     * 上传文件实现.
     *
     * @param request  上下文请求参数
     * @param response 上下文应答参数
     * @param salesOrderId  订单Id
     * @return false-上传失败;true-上传成功
     * @throws IOException
     * @throws FileException
     */
    public void uploadFile(HttpServletRequest request, HttpServletResponse response, Integer salesOrderId)
            throws IOException, FileException;

    /**
     * 文件下载.
     *
     * @param request  上下文请求参数
     * @param response 上下文应答参数
     * @return false-上传失败;true-上传成功
     * @throws IOException
     */
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * 保存上传文件信息.
     *
     * @param fileManagerDto 文件对象
     * @return FileManagerDto 保存后的文件信息对象
     * @throws FileException
     */
    public FileManagerDto saveFileOperateInfo(FileManagerDto fileManagerDto) throws FileException;

    /**
     * 查询上传文件信息.
     *
     * @param fileManagerDto  文件对象
     * @param pageNum  当前页码
     * @param pageSize 每页显示条数
     * @return List<FileManagerDto> 文件对象集合
     */
    public List<FileManagerDto> queryFileOperate(int pageNum, int pageSize, FileManagerDto fileManagerDto);

    /**
     * 根据fileId删除记录.
     *
     * @param fileManagerDto
     * @return int
     * @throws FileException
     */
    public void batchDeleteByParam(List<FileManagerDto> fileManagerDto) throws FileException;

}
