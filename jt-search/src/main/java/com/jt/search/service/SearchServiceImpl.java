package com.jt.search.service;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.search.QueryRescorer;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.search.pojo.Item;

@Service
public class SearchServiceImpl implements SearchService{
	
	//通过solr的索引查询数据信息
	
	@Autowired	//虽然方法已经过时  但是还可以使用
	private HttpSolrServer httpSolrServer;
	
	@Override
	public List<Item> findItemListBykeyWord(String keyWord,Integer page,Integer rows) {
		//定义查询参数
		SolrQuery solrQuery = new SolrQuery();
		
		int startPage = (page - 1) * rows;
		//设置关键查询  根据关键字
		solrQuery.setQuery(keyWord);
		solrQuery.setStart(startPage); //定义分页开始
		solrQuery.setRows(rows); //定义分页的行数
		
		//获取solr的查询数据
		try {
			QueryResponse queryResponse = httpSolrServer.query(solrQuery);
			//获取item对象
			List<Item> itemList = queryResponse.getBeans(Item.class);	
			return itemList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
