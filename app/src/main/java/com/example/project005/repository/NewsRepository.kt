package com.example.project005.repository

import com.example.project005.api.RetrofitInstance
import com.example.project005.db.ArticleDatabase
import com.example.project005.models.Article

class NewsRepository(val db:ArticleDatabase) {

    suspend fun getHeadlines(countryCode:String,pageNumber:Int)=
        RetrofitInstance.api.getHeadlines(countryCode,pageNumber)

    suspend fun searchNews(searchQuery:String,pageNumber:Int)=
        RetrofitInstance.api.searchForNews(searchQuery,pageNumber)

    suspend fun upsert(article: Article)=
        db.getArticleDao().upsert(article)

    fun getFavouriteNews()=
        db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article)=
        db.getArticleDao().deleteArticle(article)



}