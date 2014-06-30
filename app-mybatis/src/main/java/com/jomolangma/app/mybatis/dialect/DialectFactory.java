package com.jomolangma.app.mybatis.dialect;

public class DialectFactory {
	
	public static Dialect createDialect(String type) {
		if (type != null) {
			if (type.indexOf("sqlserver") >= 0){
				return new SQLServerDialect();
			}
			if (type.indexOf("oracle") >= 0)
				return new OracleDialect();
			if (type.indexOf("postgresql") >= 0)
				return new PostgreSQLDialect();
			if (type.indexOf("db2") >= 0)
				return new DB2Dialect();
		}
		return new UndefinedDialect();
	}
	
	public static Dialect createDialect(String type, String compatibility) {
		if (type != null) {
			if (type.indexOf("sqlserver") >= 0){
				if("sqlserver2000".equalsIgnoreCase(compatibility))
					return new SQLServer2000Dialect();
				return new SQLServerDialect();
			}
			if (type.indexOf("oracle") >= 0)
				return new OracleDialect();
			if (type.indexOf("postgresql") >= 0)
				return new PostgreSQLDialect();
			if (type.indexOf("db2") >= 0)
				return new DB2Dialect();
		}
		return new UndefinedDialect();
	}
	
	
}
