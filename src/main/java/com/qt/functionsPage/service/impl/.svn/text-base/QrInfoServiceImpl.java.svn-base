package com.qt.functionsPage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qt.functionsPage.dao.QrInfoMapper;
import com.qt.functionsPage.model.QrInfo;
import com.qt.functionsPage.service.QrInfoService;

@Service
@Transactional
public class QrInfoServiceImpl implements QrInfoService {

	@Autowired
	private QrInfoMapper qrInfoMapper;
	
	@Override
	public void insertQrInfo(QrInfo qrInfo) {
		qrInfoMapper.insertQrInfo(qrInfo);
	}

	@Override
	public void updateQrInfo(QrInfo qrInfo) {
		qrInfoMapper.updateQrInfo(qrInfo);
	}

	@Override
	public QrInfo searchQrInfo(String scene_id) {
		return qrInfoMapper.searchQrInfo(scene_id);
	}

	@Override
	public String searchQrInformation(String scene_id) {
		return qrInfoMapper.searchQrInformation(scene_id);
	}

}
