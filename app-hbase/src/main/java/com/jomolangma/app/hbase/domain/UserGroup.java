package com.jomolangma.app.hbase.domain;

import java.util.Set;

public class UserGroup {
    private String groupName;
    private Common common;
    private Set<String> taglibs;
    private Set<String> hotspots;
    private Set<String> regions;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public Set<String> getTaglibs() {
        return taglibs;
    }

    public void setTaglibs(Set<String> taglibs) {
        this.taglibs = taglibs;
    }

    public Set<String> getHotspots() {
        return hotspots;
    }

    public void setHotspots(Set<String> hotspots) {
        this.hotspots = hotspots;
    }

    public Set<String> getRegions() {
        return regions;
    }

    public void setRegions(Set<String> regions) {
        this.regions = regions;
    }

    public boolean hasTag(String tag) {
        return taglibs != null && taglibs.contains(tag);
    }

    public boolean hasHotsport(String hotspot) {
        return hotspots != null && hotspots.contains(hotspot);
    }

    public boolean hasRegion(String region) {
        return regions != null && regions.contains(region);
    }

    public boolean invalidGender(String genderParam) {
        if (common == null) {
            return true;
        }

        return common.invalidGender(genderParam);
    }

    public boolean invalidAge(int ageParam) {
        if (common == null) {
            return true;
        }

        return common.invalidAge(ageParam);
    }

    public boolean invalidSalary(int salaryParam) {
        if (common == null) {
            return true;
        }

        return common.invalidSalary(salaryParam);
    }
}
