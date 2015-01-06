package com.jomolangma.app.hbase.domain;

public class Common {
    private String gender;
    private String age;
    private String salary;

    private String agePrefix;
    private String ageSuffix;
    private int ageStart;
    private int ageEnd;

    private String salaryPrefix;
    private String salarySuffix;
    private double salaryStart;
    private double salaryEnd;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;

        if (age != null && age.length() > 4) {
            agePrefix = age.substring(0, 1);
            ageSuffix = age.substring(age.length() - 1);
            int pos = age.indexOf(",");
            ageStart = Integer.valueOf(age.substring(1, pos));
            ageEnd = Integer.valueOf(age.substring(pos + 1, age.length() - 1));
        }
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;

        if (salary != null && salary.length() > 4) {
            salaryPrefix = salary.substring(0, 1);
            salarySuffix = salary.substring(salary.length() - 1);
            int pos = salary.indexOf(",");
            salaryStart = Double.valueOf(salary.substring(1, pos));
            salaryEnd = Double.valueOf(salary.substring(pos + 1, salary.length() - 1));
        }
    }

    public boolean invalidGender(String genderParam) {
        //没有配置gender参数，所有gender都符合要求
        if (gender == null || gender.length() == 0) {
            return true;
        }

        return gender.equalsIgnoreCase(genderParam);
    }

    public boolean invalidAge(int ageParam) {
        //没有配置age参数，所有age都符合要求
        if (age == null || age.length() <= 4) {
            return true;
        }

        //配置了age参数，我们需要匹配年龄是否在制定范围内
        if (agePrefix.equals("#") && ageSuffix.equals("#")) {
            return ageParam >= ageStart && ageParam <= ageEnd;
        }

        if (agePrefix.equals("#") && ageSuffix.equals("$")) {
            return ageParam >= ageStart && ageParam < ageEnd;
        }

        if (agePrefix.equals("$") && ageSuffix.equals("#")) {
            return ageParam > ageStart && ageParam <= ageEnd;
        }

        if (agePrefix.equals("$") && ageSuffix.equals("$")) {
            return ageParam > ageStart && ageParam < ageEnd;
        }

        return false;
    }

    public boolean invalidSalary(double salaryParam) {
        //没有配置salary参数，所有salary都符合要求
        if (salary == null || salary.length() <= 4) {
            return true;
        }

        //配置了salary参数，我们需要匹配年薪是否在制定范围内
        if (salaryPrefix.equals("#") && salarySuffix.equals("#")) {
            return salaryParam >= salaryStart && salaryParam <= salaryEnd;
        }

        if (salaryPrefix.equals("#") && salarySuffix.equals("$")) {
            return salaryParam >= salaryStart && salaryParam < salaryEnd;
        }

        if (salaryPrefix.equals("$") && salarySuffix.equals("#")) {
            return salaryParam > salaryStart && salaryParam <= salaryEnd;
        }

        if (salaryPrefix.equals("$") && salarySuffix.equals("$")) {
            return salaryParam > salaryStart && salaryParam < salaryEnd;
        }

        return false;
    }
}
