package com.meacial.lucene;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.util.Collection;
import java.util.Iterator;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.IndexInput;
import org.apache.lucene.store.IndexOutput;
import org.apache.lucene.store.Lock;
import org.apache.lucene.store.SimpleFSDirectory;

import com.meacial.util.Const;

/**
 * 文本文件索引
 * 
 * 
 * @author ligangping
 *
 */
public class TxtFileIndexer {

	public static void main(String[] args) throws IOException {
		
		
		//indexDir is the directory that hosts Lucene's index files
		File indexDir = new File(Const.BASEDIR+"\\luceneIndex");
		//dataDir is the directory that hosts the text files that to be indexed 
		File dataDir = new File(Const.BASEDIR+"\\luceneData");
		Analyzer analyzer = new StandardAnalyzer();
		File[] dataFiles = dataDir.listFiles();
		IndexWriter indexWriter = new IndexWriter(new SimpleFSDirectory(null, null),new IndexWriterConfig(analyzer));
		
		
		
		
		
		
		
		
		
	}
}
