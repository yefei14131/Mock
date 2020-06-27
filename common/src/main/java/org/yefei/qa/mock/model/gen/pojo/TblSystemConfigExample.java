package org.yefei.qa.mock.model.gen.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TblSystemConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblSystemConfigExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andConfigIDIsNull() {
            addCriterion("configID is null");
            return (Criteria) this;
        }

        public Criteria andConfigIDIsNotNull() {
            addCriterion("configID is not null");
            return (Criteria) this;
        }

        public Criteria andConfigIDEqualTo(Integer value) {
            addCriterion("configID =", value, "configID");
            return (Criteria) this;
        }

        public Criteria andConfigIDNotEqualTo(Integer value) {
            addCriterion("configID <>", value, "configID");
            return (Criteria) this;
        }

        public Criteria andConfigIDGreaterThan(Integer value) {
            addCriterion("configID >", value, "configID");
            return (Criteria) this;
        }

        public Criteria andConfigIDGreaterThanOrEqualTo(Integer value) {
            addCriterion("configID >=", value, "configID");
            return (Criteria) this;
        }

        public Criteria andConfigIDLessThan(Integer value) {
            addCriterion("configID <", value, "configID");
            return (Criteria) this;
        }

        public Criteria andConfigIDLessThanOrEqualTo(Integer value) {
            addCriterion("configID <=", value, "configID");
            return (Criteria) this;
        }

        public Criteria andConfigIDIn(List<Integer> values) {
            addCriterion("configID in", values, "configID");
            return (Criteria) this;
        }

        public Criteria andConfigIDNotIn(List<Integer> values) {
            addCriterion("configID not in", values, "configID");
            return (Criteria) this;
        }

        public Criteria andConfigIDBetween(Integer value1, Integer value2) {
            addCriterion("configID between", value1, value2, "configID");
            return (Criteria) this;
        }

        public Criteria andConfigIDNotBetween(Integer value1, Integer value2) {
            addCriterion("configID not between", value1, value2, "configID");
            return (Criteria) this;
        }

        public Criteria andConfigTypeIsNull() {
            addCriterion("configType is null");
            return (Criteria) this;
        }

        public Criteria andConfigTypeIsNotNull() {
            addCriterion("configType is not null");
            return (Criteria) this;
        }

        public Criteria andConfigTypeEqualTo(Integer value) {
            addCriterion("configType =", value, "configType");
            return (Criteria) this;
        }

        public Criteria andConfigTypeNotEqualTo(Integer value) {
            addCriterion("configType <>", value, "configType");
            return (Criteria) this;
        }

        public Criteria andConfigTypeGreaterThan(Integer value) {
            addCriterion("configType >", value, "configType");
            return (Criteria) this;
        }

        public Criteria andConfigTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("configType >=", value, "configType");
            return (Criteria) this;
        }

        public Criteria andConfigTypeLessThan(Integer value) {
            addCriterion("configType <", value, "configType");
            return (Criteria) this;
        }

        public Criteria andConfigTypeLessThanOrEqualTo(Integer value) {
            addCriterion("configType <=", value, "configType");
            return (Criteria) this;
        }

        public Criteria andConfigTypeIn(List<Integer> values) {
            addCriterion("configType in", values, "configType");
            return (Criteria) this;
        }

        public Criteria andConfigTypeNotIn(List<Integer> values) {
            addCriterion("configType not in", values, "configType");
            return (Criteria) this;
        }

        public Criteria andConfigTypeBetween(Integer value1, Integer value2) {
            addCriterion("configType between", value1, value2, "configType");
            return (Criteria) this;
        }

        public Criteria andConfigTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("configType not between", value1, value2, "configType");
            return (Criteria) this;
        }

        public Criteria andConfigTitleIsNull() {
            addCriterion("configTitle is null");
            return (Criteria) this;
        }

        public Criteria andConfigTitleIsNotNull() {
            addCriterion("configTitle is not null");
            return (Criteria) this;
        }

        public Criteria andConfigTitleEqualTo(String value) {
            addCriterion("configTitle =", value, "configTitle");
            return (Criteria) this;
        }

        public Criteria andConfigTitleNotEqualTo(String value) {
            addCriterion("configTitle <>", value, "configTitle");
            return (Criteria) this;
        }

        public Criteria andConfigTitleGreaterThan(String value) {
            addCriterion("configTitle >", value, "configTitle");
            return (Criteria) this;
        }

        public Criteria andConfigTitleGreaterThanOrEqualTo(String value) {
            addCriterion("configTitle >=", value, "configTitle");
            return (Criteria) this;
        }

        public Criteria andConfigTitleLessThan(String value) {
            addCriterion("configTitle <", value, "configTitle");
            return (Criteria) this;
        }

        public Criteria andConfigTitleLessThanOrEqualTo(String value) {
            addCriterion("configTitle <=", value, "configTitle");
            return (Criteria) this;
        }

        public Criteria andConfigTitleLike(String value) {
            addCriterion("configTitle like", value, "configTitle");
            return (Criteria) this;
        }

        public Criteria andConfigTitleNotLike(String value) {
            addCriterion("configTitle not like", value, "configTitle");
            return (Criteria) this;
        }

        public Criteria andConfigTitleIn(List<String> values) {
            addCriterion("configTitle in", values, "configTitle");
            return (Criteria) this;
        }

        public Criteria andConfigTitleNotIn(List<String> values) {
            addCriterion("configTitle not in", values, "configTitle");
            return (Criteria) this;
        }

        public Criteria andConfigTitleBetween(String value1, String value2) {
            addCriterion("configTitle between", value1, value2, "configTitle");
            return (Criteria) this;
        }

        public Criteria andConfigTitleNotBetween(String value1, String value2) {
            addCriterion("configTitle not between", value1, value2, "configTitle");
            return (Criteria) this;
        }

        public Criteria andConfigDataIsNull() {
            addCriterion("configData is null");
            return (Criteria) this;
        }

        public Criteria andConfigDataIsNotNull() {
            addCriterion("configData is not null");
            return (Criteria) this;
        }

        public Criteria andConfigDataEqualTo(String value) {
            addCriterion("configData =", value, "configData");
            return (Criteria) this;
        }

        public Criteria andConfigDataNotEqualTo(String value) {
            addCriterion("configData <>", value, "configData");
            return (Criteria) this;
        }

        public Criteria andConfigDataGreaterThan(String value) {
            addCriterion("configData >", value, "configData");
            return (Criteria) this;
        }

        public Criteria andConfigDataGreaterThanOrEqualTo(String value) {
            addCriterion("configData >=", value, "configData");
            return (Criteria) this;
        }

        public Criteria andConfigDataLessThan(String value) {
            addCriterion("configData <", value, "configData");
            return (Criteria) this;
        }

        public Criteria andConfigDataLessThanOrEqualTo(String value) {
            addCriterion("configData <=", value, "configData");
            return (Criteria) this;
        }

        public Criteria andConfigDataLike(String value) {
            addCriterion("configData like", value, "configData");
            return (Criteria) this;
        }

        public Criteria andConfigDataNotLike(String value) {
            addCriterion("configData not like", value, "configData");
            return (Criteria) this;
        }

        public Criteria andConfigDataIn(List<String> values) {
            addCriterion("configData in", values, "configData");
            return (Criteria) this;
        }

        public Criteria andConfigDataNotIn(List<String> values) {
            addCriterion("configData not in", values, "configData");
            return (Criteria) this;
        }

        public Criteria andConfigDataBetween(String value1, String value2) {
            addCriterion("configData between", value1, value2, "configData");
            return (Criteria) this;
        }

        public Criteria andConfigDataNotBetween(String value1, String value2) {
            addCriterion("configData not between", value1, value2, "configData");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("updateTime =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("updateTime <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("updateTime >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTime >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("updateTime <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("updateTime <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("updateTime in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("updateTime not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("updateTime between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("updateTime not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
