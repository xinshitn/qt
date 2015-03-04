package com.qt.functionsPage.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.qt.functionsPage.model.QrInfo;

/**
 * 二维码信息
 */
@Repository
public interface QrInfoMapper {
    /**
     * 新增 QrInfo
     * @param qrInfo
     * @return
     */
    void insertQrInfo(QrInfo qrInfo);
    
    /**
     * 更新 QrInfo
     * @param qrInfo
     * @return
     */
    void updateQrInfo(QrInfo qrInfo);
    
    /**
     * 查询 QrInfo
     * @param scene_id
     * @return
     */
    QrInfo searchQrInfo(@Param("scene_id")String scene_id);
    
    /**
     * 查询 QrInfo的Info
     * @param scene_id
     * @return
     */
    String searchQrInformation(@Param("scene_id")String scene_id);
    
}
