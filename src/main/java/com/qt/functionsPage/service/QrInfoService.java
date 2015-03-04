package com.qt.functionsPage.service;

import com.qt.functionsPage.model.QrInfo;

public interface QrInfoService {
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
    QrInfo searchQrInfo(String scene_id);
    
    /**
     * 查询 QrInfo的Info
     * @param scene_id
     * @return
     */
    String searchQrInformation(String scene_id);
}
