package com.xinda.fm.file.mapper;

import com.xinda.fm.file.dto.FileManagerDto;

import java.util.List;

/**
 * 文件管理Mapper.
 *
 * @Author Coundy.
 * @Date 2017/3/21 17:32
 */
public interface FileManagerMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(FileManagerDto record);

    int insertSelective(FileManagerDto record);

    FileManagerDto selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(FileManagerDto record);

    int updateByPrimaryKey(FileManagerDto record);

    /**
     * 根据参数条件查询文件信息.
     *
     * @param fileManagerDto
     * @return List<FileManagerDto>
     */
    List<FileManagerDto> queryFileOperateByParms(FileManagerDto fileManagerDto);

}
