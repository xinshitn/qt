package com.qt.articlePage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qt.articlePage.dao.ArticleMapper;
import com.qt.articlePage.service.ArticleService;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public byte[] findCureArticle() {
		return articleMapper.findCureArticle().getInfo();
	}

}
