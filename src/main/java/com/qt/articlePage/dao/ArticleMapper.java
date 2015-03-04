package com.qt.articlePage.dao;

import org.springframework.stereotype.Repository;

import com.qt.articlePage.model.CureArticle;

/**
 * 文章查询
 * @author VicenTN
 *
 */
@Repository
public interface ArticleMapper {

	/**
	 * 查询最新治愈系文章
	 * @return
	 */
	public CureArticle findCureArticle();
}
