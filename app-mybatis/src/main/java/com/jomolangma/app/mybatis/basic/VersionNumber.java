package com.jomolangma.app.mybatis.basic;

public class VersionNumber extends BaseDomain implements Comparable<VersionNumber> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String versionStr;

	public VersionNumber(String versionStr) {
		this.versionStr = versionStr;
	}

	@Override
	public int compareTo(VersionNumber o) {
		if(versionStr.equalsIgnoreCase(o.versionStr))
			return 0;
		String mySections[] = versionStr.split("\\.");
		String otherSections[] = o.versionStr.split("\\.");		
		for(int idx=0; idx<mySections.length && idx<otherSections.length; idx++){
			int compared = mySections[idx].compareToIgnoreCase(otherSections[idx]);
			if(compared!=0)
				return compared;
		}
		return mySections.length-otherSections.length;
	}

}
