package com.meacial.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 结合Lucene使用
 */
public class LuceneAnalyzar {

	
	public static void main(String[] args) throws IOException {
		
		/*String fileName = "";
		String text = "据说WWDC要推出iPhone6要出了？与iPhone5s土豪金相比怎样呢？";
		//实例化IKAnalyzer分词器
		Analyzer analyzer = new IKAnalyzer(true);
		Directory directory = null;
		IndexWriter iwriter = null;
		IndexReader ireader = null;
		IndexSearcher isearcher = null;
		try {
			//建立内存索引对象
			directory = new RAMDirectory();
			//配置IndexWriterConfig
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			config.setOpenMode(OpenMode.CREATE_OR_APPEND);
			iwriter = new IndexWriter(directory, config);
			// 写入索引
			Document docment = new Document();
			docment.add(new Field("ID", "1000", Field.Store.YES,Field.Index.NOT_ANALYZED));
			docment.add(new Field(fileName, text, Field.Store.YES,Field.Index.ANALYZED));
			iwriter.addDocument(docment);
			iwriter.close();
			//搜索过程**********************************
		    //实例化搜索器   
		    isearcher = new IndexSearcher(ireader);          
		    IKSegmenter
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		
		
		
		/*String text="基于java语言开发的轻量级的中文分词工具包";
		//创建分词对象
		Analyzer anal=new IKAnalyzer(true);		
		StringReader reader=new StringReader(text);
		//分词
		TokenStream ts=anal.tokenStream("", reader);
		CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);
		//遍历分词数据
		while(ts.incrementToken()){
			System.out.print(term.toString()+"|");
		}
		reader.close();
		System.out.println();*/
		
		
		
		String text="基于java语言开发的轻量级的中文分词工具包";
		StringReader sr=new StringReader(text);
		IKSegmenter ik=new IKSegmenter(sr, true);
		Lexeme lex=null;
		while((lex=ik.next())!=null){
			System.out.print(lex.getLexemeText()+"|");
		}
	}
}
