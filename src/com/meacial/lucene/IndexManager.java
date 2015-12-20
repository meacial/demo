package com.meacial.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import com.meacial.util.Const;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
public class IndexManager {
	private static IndexManager indexManager;
	private static String content = "";
	private static String indexDir = Const.BASEDIR + "\\index";
	private static String dataDir = Const.BASEDIR + "\\data";
	private static Analyzer analyzer = null;
	private static Directory directory = null;
	private static IndexWriter indexWriter = null;
	/**
	 * 索引管理器
	 * @return
	 */
	private IndexManager getManager() {
		if (indexManager == null) {
			synchronized (indexManager) {
				indexManager = new IndexManager();
			}
		}
		return indexManager;
	}
	
	private static String txt2String(File file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			while(true) {
				line = br.readLine();
				if (null == line) break;
				result.append(line).append(Const.lineSeparator);
			}
			br.close();
		} catch (Exception e) {
		}
		return result.toString();
	}
	
	private static String doc2String(File file) {
		StringBuilder result = new StringBuilder();
		try {
			InputStream fis = new FileInputStream(file);
			HWPFDocument doc = new HWPFDocument(fis);
			Range rang = doc.getRange();
			result.append(rang.text());
			fis.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result.toString();
	}
	
	
	private static String xls2String(File file) {
		StringBuilder result = new StringBuilder();
		try {
			InputStream fis = new FileInputStream(file);
			Workbook rwb = Workbook.getWorkbook(fis);
			Sheet[] sheets = rwb.getSheets();
			for(Sheet sheet : Arrays.asList(sheets)) {
				for (int i = 0; i < sheet.getRows(); i++) {
					Cell[] cells = sheet.getRow(i);
					for (int j = 0; j < cells.length; j++) {
						result.append(cells[j].getContents());
					}
				}
			}
			fis.close();
		} catch (Exception e) {
		}
		return result.toString();
	}
	
	private static boolean createIndex(String path) {
		Date date1 = new Date();
		
		File pathDir = new File(path);
		File[] files = pathDir.listFiles();
		for (int a = 0 ; a < files.length ; a++) {
			File file = files[a];
			content = "";
			
			String type = file.getName().substring(file.getName().lastIndexOf(".")+1);
			
			if (type.equalsIgnoreCase("txt")) {
				content = txt2String(file);
			} else if (type.equalsIgnoreCase("doc")) {
				content = doc2String(file);
			} else if (type.equalsIgnoreCase("xls")) {
				content = xls2String(file);
			}
			
			System.out.println(content);
			
			try {
				analyzer = new StandardAnalyzer();
				
				directory = FSDirectory.open(Paths.get(indexDir));
				
				File indexFile = new File(indexDir);
				
				if (indexFile.exists() == false ) {
					//indexFile.createNewFile();
					indexFile.mkdir();
				}
				
				IndexWriterConfig config = new IndexWriterConfig(analyzer);
				
				indexWriter = new IndexWriter(directory, config);
				
				Document document = new Document();
				
				document.add(new TextField("filename", file.getName(), Store.YES));
				
				document.add(new TextField("content", content, Store.YES));
				
				document.add(new TextField("path", file.getPath(), Store.YES));
				
				indexWriter.addDocument(document);
				
				indexWriter.commit();
				
				indexWriter.close();

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		content = "";
		Date date2 = new Date();
		System.out.println("创建索引耗时：" + (date2.getTime() - date1.getTime()) + "ms\n");
		return true;
	}
	
	
	private static void indexSearch(String text) {
		
		Date date1 = new Date();
		
		try {
			
			directory = FSDirectory.open(Paths.get(indexDir));
			
			analyzer  = new StandardAnalyzer();
			
			DirectoryReader iReader = DirectoryReader.open(directory);
			
			IndexSearcher search = new IndexSearcher(iReader);
		
			QueryParser parse = new QueryParser("content", analyzer);
			
			Query query = parse.parse(text);
			
			ScoreDoc[] hits = search.search(query, null, 1000).scoreDocs;
			
			for (int i = 0; i < hits.length; i++) {
				Document docment = search.doc(hits[i].doc);
				
				System.out.println(docment.get("filename"));
				System.out.println(docment.get("content"));
				System.out.println(docment.get("path"));
			}
			iReader.close();
			
			directory.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		Date date2 = new Date();
		
		System.out.println("索引查找耗时" + (date2.getTime() - date1.getTime()) + "ms\n");
		
	}
	
	
	public static void main(String[] args) {
		
		
		createIndex(dataDir);
		
		indexSearch("man");
		
	}
	
}
