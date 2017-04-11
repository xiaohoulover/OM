package com.xinda.fm.file.controller;

import com.xinda.fm.file.dto.FileManagerDto;
import com.xinda.fm.file.service.IFileManagerService;
import com.xinda.system.sys.contant.BaseConstants;
import com.xinda.system.sys.controller.BaseController;
import com.xinda.system.sys.dto.ResponseJsonData;
import com.xinda.system.sys.exception.FileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 文件管理Controller.
 *
 * @Author Coundy.
 * @Date 2017/3/21 17:40
 */
@Controller
@RequestMapping("/file")
public class FileOperateController extends BaseController {

    @Autowired
    private IFileManagerService fileManagerService;

    /**
     * 文件上传.
     *
     * @param request      上下文请求参数
     * @param response     上下文应答参数
     * @param salesOrderId 订单id
     * @return
     * @throws IOException
     * @throws FileException
     */
    @ResponseBody
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseJsonData uploadFile(HttpServletRequest request, HttpServletResponse response, Integer salesOrderId)
            throws IOException, FileException {
        fileManagerService.uploadFile(request, response, salesOrderId);
        return new ResponseJsonData(true);
    }

    /**
     * 文件下载.
     *
     * @param request  上下文请求参数
     * @param response 上下文应答参数
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public ResponseJsonData downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        fileManagerService.downloadFile(request, response);
        return new ResponseJsonData(true);
    }

    /**
     * 查询文件信息.
     *
     * @param request        请求参数
     * @param response       应答参数
     * @param fileManagerDto
     * @return ResponseJsonData
     */
    @RequestMapping(value = "/queryFileOperate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJsonData queryFileOperate(HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam(defaultValue = BaseConstants.DEFAULT_PAGE_NUM) int page,
                                             @RequestParam(defaultValue = BaseConstants.DEFAULT_PAGE_SIZE) int pagesize,
                                             FileManagerDto fileManagerDto) {
        return new ResponseJsonData(fileManagerService.queryFileOperate(page, pagesize, fileManagerDto));
    }

    /**
     * 删除文件信息.
     *
     * @param request  请求参数
     * @param response 应答参数
     * @return
     * @throws FileException
     */
    @RequestMapping(value = "/batchDeleteByParam", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJsonData deleteByFileId(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @RequestBody List<FileManagerDto> fileManagerDtoList) throws FileException {
        fileManagerService.batchDeleteByParam(fileManagerDtoList);
        return new ResponseJsonData(true);
    }

}
