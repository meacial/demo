/**
 * @author ligangping
 *
 *	IKAnalyzer是一个开源的，基于java语言开发的轻量级的中文分词工具包。
 *	采用了特有的“正向迭代最细粒度切分算法“，具有60万字/秒的高速处理能力。
	采用了多子处理器分析模式，支持：英文字母（IP地址、Email、URL）、数字（日期，常用中文数量词，罗马数字，科学计数法），中文词汇（姓名、地名处理）等分词处理。
	对中英联合支持不是很好,在这方面的处理比较麻烦.需再做一次查询,同时是支持个人词条的优化的词典存储，更小的内存占用。
	支持用户词典扩展定义。
	针对Lucene全文检索优化的查询分析器IKQueryParser；采用歧义分析算法优化查询关键字的搜索排列组合，能极大的提高Lucene检索的命中率。
	
	
	
	下载下来的Jar包是包含了结合Lucene使用的例子，先把要检索的内容，写入Lucene索引，然后根据需要查找的关键词，
	通过Lucene的QueryParser对象进行解析查找，构造该QueryParser对象的时候，传入了IKAnalyzer，
	进而通过IKAnalyzer进行分词：
	
	
	
 */
package com.meacial.IKAnalyzer;