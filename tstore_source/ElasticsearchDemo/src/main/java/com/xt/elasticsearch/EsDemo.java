package com.xt.elasticsearch;

import java.net.InetAddress;
import java.util.Iterator;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

public class EsDemo {

	/* ���������������� */
	@Test
	public void createIndex() throws Exception {

		// 1: �����ͻ�������
		TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
		// 2������ 
		// 2.1 �����ĵ�
		XContentBuilder builder = XContentFactory.jsonBuilder();
		builder.startObject().field("id",1)
			        .field("title", "elasticsearch��һ������lucene����������")
					.field("content","ElasticSearch��һ������Lucene������������")
					.endObject(); 
		// 2.2 ��������
		client.prepareIndex("blog", "article", "1").setSource(builder).get();
		// 3���ر�
		client.close();
	}

	// �ַ�����ѯȫ��
	@Test
	public void searchByKeyword() throws Exception {
		// 1:�����ͻ���
		// 1: �����ͻ�������
		TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

		// QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("����");
		// QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
		// QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("title","��");//�������Ϊ0
		// �Ƿִʵ�����
		//QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "����");
		QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("����", "title");
		//QueryBuilder queryBuilder = QueryBuilders.termQuery("title","��");
		SearchResponse response = client.prepareSearch("blog").setTypes("article").setQuery(queryBuilder).get();
		// 3:��ʾ��ѯ���
		SearchHits searchHits = response.getHits();
		System.out.println("�������ǣ�" + searchHits.totalHits);
		// ��ѯչʾ�Ľ��
		Iterator<SearchHit> iterator = searchHits.iterator();
		while (iterator.hasNext()) {
			SearchHit searchHit = iterator.next();
			System.out.println("��ѯ����ǣ�" + searchHit.getSourceAsString());
			System.out.println("id��" + searchHit.getSource().get("id"));
			System.out.println("title��" + searchHit.getSource().get("title"));
			System.out.println("content��" + searchHit.getSource().get("content"));
		}
		// 4:�ر���Դ
		client.close();
	}
}
