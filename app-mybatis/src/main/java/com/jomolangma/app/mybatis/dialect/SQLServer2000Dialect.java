package com.jomolangma.app.mybatis.dialect;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author Allen
 *
 */
public class SQLServer2000Dialect extends SQLServerDialect {


	public String getLimitString(String sql, int offset, int limit ) {
		String lowerCaseSQL = sql.toLowerCase().replace("\n", " ").replace("\t", " ");
		int index = lowerCaseSQL.indexOf(" order ");
		if(index<0)
			return sql;
		String orderByClause = sql.substring(index);
		String reversedOrderByClause = this.reverseOrder(orderByClause);
		String topAll =new StringBuilder(sql)
		.insert(getAfterSelectPoint(sql), " top ("+(offset+limit)+")").toString();
		String topLimit = " select top ("+ limit +") * from ("+ topAll +") topAll "+ reversedOrderByClause;
		String page = " select * from ("+ topLimit +") topLimit "+ orderByClause;
		return page.toString();
	}
	
	private String reverseOrder(String orderByClause){
		String clause = orderByClause.toLowerCase();
		Pattern defaultOrderPattern = Pattern.compile("(\\s)?(\\w+)(\\s*),");
		Matcher matcher = defaultOrderPattern.matcher(clause);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()){
			if(!matcher.group(2).equals("asc") && !matcher.group(2).equals("desc") )
				matcher.appendReplacement(sb, (matcher.group(1)==null?"":matcher.group(1))+matcher.group(2)+" asc, ");
		}
		matcher.appendTail(sb);
		clause = sb.toString();
		
		Pattern tailPattern = Pattern.compile("(\\s)?(\\w+)(\\s*)$");
		matcher = tailPattern.matcher(clause);
		if(matcher.find()){
			if(!matcher.group(2).equals("asc") && !matcher.group(2).equals("desc") )
				clause = matcher.replaceFirst((matcher.group(1)==null?"":matcher.group(1))+matcher.group(2)+" asc");
		}
		clause = clause.replaceAll(" asc", " xxx").replaceAll(" desc", " asc").replaceAll(" xxx", " desc");
		return clause;
	}
	
	public static void main(String args[]){
		System.out.println(new SQLServer2000Dialect().reverseOrder(" order by 1 desc, 2 asc, name "));
	}
	
}
