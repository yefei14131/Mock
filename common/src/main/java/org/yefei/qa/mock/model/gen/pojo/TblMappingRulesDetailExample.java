package org.yefei.qa.mock.model.gen.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TblMappingRulesDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblMappingRulesDetailExample() {
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

        public Criteria andRulesDetailIDIsNull() {
            addCriterion("rulesDetailID is null");
            return (Criteria) this;
        }

        public Criteria andRulesDetailIDIsNotNull() {
            addCriterion("rulesDetailID is not null");
            return (Criteria) this;
        }

        public Criteria andRulesDetailIDEqualTo(Integer value) {
            addCriterion("rulesDetailID =", value, "rulesDetailID");
            return (Criteria) this;
        }

        public Criteria andRulesDetailIDNotEqualTo(Integer value) {
            addCriterion("rulesDetailID <>", value, "rulesDetailID");
            return (Criteria) this;
        }

        public Criteria andRulesDetailIDGreaterThan(Integer value) {
            addCriterion("rulesDetailID >", value, "rulesDetailID");
            return (Criteria) this;
        }

        public Criteria andRulesDetailIDGreaterThanOrEqualTo(Integer value) {
            addCriterion("rulesDetailID >=", value, "rulesDetailID");
            return (Criteria) this;
        }

        public Criteria andRulesDetailIDLessThan(Integer value) {
            addCriterion("rulesDetailID <", value, "rulesDetailID");
            return (Criteria) this;
        }

        public Criteria andRulesDetailIDLessThanOrEqualTo(Integer value) {
            addCriterion("rulesDetailID <=", value, "rulesDetailID");
            return (Criteria) this;
        }

        public Criteria andRulesDetailIDIn(List<Integer> values) {
            addCriterion("rulesDetailID in", values, "rulesDetailID");
            return (Criteria) this;
        }

        public Criteria andRulesDetailIDNotIn(List<Integer> values) {
            addCriterion("rulesDetailID not in", values, "rulesDetailID");
            return (Criteria) this;
        }

        public Criteria andRulesDetailIDBetween(Integer value1, Integer value2) {
            addCriterion("rulesDetailID between", value1, value2, "rulesDetailID");
            return (Criteria) this;
        }

        public Criteria andRulesDetailIDNotBetween(Integer value1, Integer value2) {
            addCriterion("rulesDetailID not between", value1, value2, "rulesDetailID");
            return (Criteria) this;
        }

        public Criteria andRequestIDIsNull() {
            addCriterion("requestID is null");
            return (Criteria) this;
        }

        public Criteria andRequestIDIsNotNull() {
            addCriterion("requestID is not null");
            return (Criteria) this;
        }

        public Criteria andRequestIDEqualTo(Integer value) {
            addCriterion("requestID =", value, "requestID");
            return (Criteria) this;
        }

        public Criteria andRequestIDNotEqualTo(Integer value) {
            addCriterion("requestID <>", value, "requestID");
            return (Criteria) this;
        }

        public Criteria andRequestIDGreaterThan(Integer value) {
            addCriterion("requestID >", value, "requestID");
            return (Criteria) this;
        }

        public Criteria andRequestIDGreaterThanOrEqualTo(Integer value) {
            addCriterion("requestID >=", value, "requestID");
            return (Criteria) this;
        }

        public Criteria andRequestIDLessThan(Integer value) {
            addCriterion("requestID <", value, "requestID");
            return (Criteria) this;
        }

        public Criteria andRequestIDLessThanOrEqualTo(Integer value) {
            addCriterion("requestID <=", value, "requestID");
            return (Criteria) this;
        }

        public Criteria andRequestIDIn(List<Integer> values) {
            addCriterion("requestID in", values, "requestID");
            return (Criteria) this;
        }

        public Criteria andRequestIDNotIn(List<Integer> values) {
            addCriterion("requestID not in", values, "requestID");
            return (Criteria) this;
        }

        public Criteria andRequestIDBetween(Integer value1, Integer value2) {
            addCriterion("requestID between", value1, value2, "requestID");
            return (Criteria) this;
        }

        public Criteria andRequestIDNotBetween(Integer value1, Integer value2) {
            addCriterion("requestID not between", value1, value2, "requestID");
            return (Criteria) this;
        }

        public Criteria andProtocolIsNull() {
            addCriterion("protocol is null");
            return (Criteria) this;
        }

        public Criteria andProtocolIsNotNull() {
            addCriterion("protocol is not null");
            return (Criteria) this;
        }

        public Criteria andProtocolEqualTo(String value) {
            addCriterion("protocol =", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolNotEqualTo(String value) {
            addCriterion("protocol <>", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolGreaterThan(String value) {
            addCriterion("protocol >", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolGreaterThanOrEqualTo(String value) {
            addCriterion("protocol >=", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolLessThan(String value) {
            addCriterion("protocol <", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolLessThanOrEqualTo(String value) {
            addCriterion("protocol <=", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolLike(String value) {
            addCriterion("protocol like", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolNotLike(String value) {
            addCriterion("protocol not like", value, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolIn(List<String> values) {
            addCriterion("protocol in", values, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolNotIn(List<String> values) {
            addCriterion("protocol not in", values, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolBetween(String value1, String value2) {
            addCriterion("protocol between", value1, value2, "protocol");
            return (Criteria) this;
        }

        public Criteria andProtocolNotBetween(String value1, String value2) {
            addCriterion("protocol not between", value1, value2, "protocol");
            return (Criteria) this;
        }

        public Criteria andVariableNameIsNull() {
            addCriterion("variableName is null");
            return (Criteria) this;
        }

        public Criteria andVariableNameIsNotNull() {
            addCriterion("variableName is not null");
            return (Criteria) this;
        }

        public Criteria andVariableNameEqualTo(String value) {
            addCriterion("variableName =", value, "variableName");
            return (Criteria) this;
        }

        public Criteria andVariableNameNotEqualTo(String value) {
            addCriterion("variableName <>", value, "variableName");
            return (Criteria) this;
        }

        public Criteria andVariableNameGreaterThan(String value) {
            addCriterion("variableName >", value, "variableName");
            return (Criteria) this;
        }

        public Criteria andVariableNameGreaterThanOrEqualTo(String value) {
            addCriterion("variableName >=", value, "variableName");
            return (Criteria) this;
        }

        public Criteria andVariableNameLessThan(String value) {
            addCriterion("variableName <", value, "variableName");
            return (Criteria) this;
        }

        public Criteria andVariableNameLessThanOrEqualTo(String value) {
            addCriterion("variableName <=", value, "variableName");
            return (Criteria) this;
        }

        public Criteria andVariableNameLike(String value) {
            addCriterion("variableName like", value, "variableName");
            return (Criteria) this;
        }

        public Criteria andVariableNameNotLike(String value) {
            addCriterion("variableName not like", value, "variableName");
            return (Criteria) this;
        }

        public Criteria andVariableNameIn(List<String> values) {
            addCriterion("variableName in", values, "variableName");
            return (Criteria) this;
        }

        public Criteria andVariableNameNotIn(List<String> values) {
            addCriterion("variableName not in", values, "variableName");
            return (Criteria) this;
        }

        public Criteria andVariableNameBetween(String value1, String value2) {
            addCriterion("variableName between", value1, value2, "variableName");
            return (Criteria) this;
        }

        public Criteria andVariableNameNotBetween(String value1, String value2) {
            addCriterion("variableName not between", value1, value2, "variableName");
            return (Criteria) this;
        }

        public Criteria andVariableSourceIsNull() {
            addCriterion("variableSource is null");
            return (Criteria) this;
        }

        public Criteria andVariableSourceIsNotNull() {
            addCriterion("variableSource is not null");
            return (Criteria) this;
        }

        public Criteria andVariableSourceEqualTo(String value) {
            addCriterion("variableSource =", value, "variableSource");
            return (Criteria) this;
        }

        public Criteria andVariableSourceNotEqualTo(String value) {
            addCriterion("variableSource <>", value, "variableSource");
            return (Criteria) this;
        }

        public Criteria andVariableSourceGreaterThan(String value) {
            addCriterion("variableSource >", value, "variableSource");
            return (Criteria) this;
        }

        public Criteria andVariableSourceGreaterThanOrEqualTo(String value) {
            addCriterion("variableSource >=", value, "variableSource");
            return (Criteria) this;
        }

        public Criteria andVariableSourceLessThan(String value) {
            addCriterion("variableSource <", value, "variableSource");
            return (Criteria) this;
        }

        public Criteria andVariableSourceLessThanOrEqualTo(String value) {
            addCriterion("variableSource <=", value, "variableSource");
            return (Criteria) this;
        }

        public Criteria andVariableSourceLike(String value) {
            addCriterion("variableSource like", value, "variableSource");
            return (Criteria) this;
        }

        public Criteria andVariableSourceNotLike(String value) {
            addCriterion("variableSource not like", value, "variableSource");
            return (Criteria) this;
        }

        public Criteria andVariableSourceIn(List<String> values) {
            addCriterion("variableSource in", values, "variableSource");
            return (Criteria) this;
        }

        public Criteria andVariableSourceNotIn(List<String> values) {
            addCriterion("variableSource not in", values, "variableSource");
            return (Criteria) this;
        }

        public Criteria andVariableSourceBetween(String value1, String value2) {
            addCriterion("variableSource between", value1, value2, "variableSource");
            return (Criteria) this;
        }

        public Criteria andVariableSourceNotBetween(String value1, String value2) {
            addCriterion("variableSource not between", value1, value2, "variableSource");
            return (Criteria) this;
        }

        public Criteria andVariableValueIsNull() {
            addCriterion("variableValue is null");
            return (Criteria) this;
        }

        public Criteria andVariableValueIsNotNull() {
            addCriterion("variableValue is not null");
            return (Criteria) this;
        }

        public Criteria andVariableValueEqualTo(String value) {
            addCriterion("variableValue =", value, "variableValue");
            return (Criteria) this;
        }

        public Criteria andVariableValueNotEqualTo(String value) {
            addCriterion("variableValue <>", value, "variableValue");
            return (Criteria) this;
        }

        public Criteria andVariableValueGreaterThan(String value) {
            addCriterion("variableValue >", value, "variableValue");
            return (Criteria) this;
        }

        public Criteria andVariableValueGreaterThanOrEqualTo(String value) {
            addCriterion("variableValue >=", value, "variableValue");
            return (Criteria) this;
        }

        public Criteria andVariableValueLessThan(String value) {
            addCriterion("variableValue <", value, "variableValue");
            return (Criteria) this;
        }

        public Criteria andVariableValueLessThanOrEqualTo(String value) {
            addCriterion("variableValue <=", value, "variableValue");
            return (Criteria) this;
        }

        public Criteria andVariableValueLike(String value) {
            addCriterion("variableValue like", value, "variableValue");
            return (Criteria) this;
        }

        public Criteria andVariableValueNotLike(String value) {
            addCriterion("variableValue not like", value, "variableValue");
            return (Criteria) this;
        }

        public Criteria andVariableValueIn(List<String> values) {
            addCriterion("variableValue in", values, "variableValue");
            return (Criteria) this;
        }

        public Criteria andVariableValueNotIn(List<String> values) {
            addCriterion("variableValue not in", values, "variableValue");
            return (Criteria) this;
        }

        public Criteria andVariableValueBetween(String value1, String value2) {
            addCriterion("variableValue between", value1, value2, "variableValue");
            return (Criteria) this;
        }

        public Criteria andVariableValueNotBetween(String value1, String value2) {
            addCriterion("variableValue not between", value1, value2, "variableValue");
            return (Criteria) this;
        }

        public Criteria andCompareWayIsNull() {
            addCriterion("compareWay is null");
            return (Criteria) this;
        }

        public Criteria andCompareWayIsNotNull() {
            addCriterion("compareWay is not null");
            return (Criteria) this;
        }

        public Criteria andCompareWayEqualTo(String value) {
            addCriterion("compareWay =", value, "compareWay");
            return (Criteria) this;
        }

        public Criteria andCompareWayNotEqualTo(String value) {
            addCriterion("compareWay <>", value, "compareWay");
            return (Criteria) this;
        }

        public Criteria andCompareWayGreaterThan(String value) {
            addCriterion("compareWay >", value, "compareWay");
            return (Criteria) this;
        }

        public Criteria andCompareWayGreaterThanOrEqualTo(String value) {
            addCriterion("compareWay >=", value, "compareWay");
            return (Criteria) this;
        }

        public Criteria andCompareWayLessThan(String value) {
            addCriterion("compareWay <", value, "compareWay");
            return (Criteria) this;
        }

        public Criteria andCompareWayLessThanOrEqualTo(String value) {
            addCriterion("compareWay <=", value, "compareWay");
            return (Criteria) this;
        }

        public Criteria andCompareWayLike(String value) {
            addCriterion("compareWay like", value, "compareWay");
            return (Criteria) this;
        }

        public Criteria andCompareWayNotLike(String value) {
            addCriterion("compareWay not like", value, "compareWay");
            return (Criteria) this;
        }

        public Criteria andCompareWayIn(List<String> values) {
            addCriterion("compareWay in", values, "compareWay");
            return (Criteria) this;
        }

        public Criteria andCompareWayNotIn(List<String> values) {
            addCriterion("compareWay not in", values, "compareWay");
            return (Criteria) this;
        }

        public Criteria andCompareWayBetween(String value1, String value2) {
            addCriterion("compareWay between", value1, value2, "compareWay");
            return (Criteria) this;
        }

        public Criteria andCompareWayNotBetween(String value1, String value2) {
            addCriterion("compareWay not between", value1, value2, "compareWay");
            return (Criteria) this;
        }

        public Criteria andParentIDIsNull() {
            addCriterion("parentID is null");
            return (Criteria) this;
        }

        public Criteria andParentIDIsNotNull() {
            addCriterion("parentID is not null");
            return (Criteria) this;
        }

        public Criteria andParentIDEqualTo(Integer value) {
            addCriterion("parentID =", value, "parentID");
            return (Criteria) this;
        }

        public Criteria andParentIDNotEqualTo(Integer value) {
            addCriterion("parentID <>", value, "parentID");
            return (Criteria) this;
        }

        public Criteria andParentIDGreaterThan(Integer value) {
            addCriterion("parentID >", value, "parentID");
            return (Criteria) this;
        }

        public Criteria andParentIDGreaterThanOrEqualTo(Integer value) {
            addCriterion("parentID >=", value, "parentID");
            return (Criteria) this;
        }

        public Criteria andParentIDLessThan(Integer value) {
            addCriterion("parentID <", value, "parentID");
            return (Criteria) this;
        }

        public Criteria andParentIDLessThanOrEqualTo(Integer value) {
            addCriterion("parentID <=", value, "parentID");
            return (Criteria) this;
        }

        public Criteria andParentIDIn(List<Integer> values) {
            addCriterion("parentID in", values, "parentID");
            return (Criteria) this;
        }

        public Criteria andParentIDNotIn(List<Integer> values) {
            addCriterion("parentID not in", values, "parentID");
            return (Criteria) this;
        }

        public Criteria andParentIDBetween(Integer value1, Integer value2) {
            addCriterion("parentID between", value1, value2, "parentID");
            return (Criteria) this;
        }

        public Criteria andParentIDNotBetween(Integer value1, Integer value2) {
            addCriterion("parentID not between", value1, value2, "parentID");
            return (Criteria) this;
        }

        public Criteria andSortIndexIsNull() {
            addCriterion("sortIndex is null");
            return (Criteria) this;
        }

        public Criteria andSortIndexIsNotNull() {
            addCriterion("sortIndex is not null");
            return (Criteria) this;
        }

        public Criteria andSortIndexEqualTo(Integer value) {
            addCriterion("sortIndex =", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexNotEqualTo(Integer value) {
            addCriterion("sortIndex <>", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexGreaterThan(Integer value) {
            addCriterion("sortIndex >", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("sortIndex >=", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexLessThan(Integer value) {
            addCriterion("sortIndex <", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexLessThanOrEqualTo(Integer value) {
            addCriterion("sortIndex <=", value, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexIn(List<Integer> values) {
            addCriterion("sortIndex in", values, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexNotIn(List<Integer> values) {
            addCriterion("sortIndex not in", values, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexBetween(Integer value1, Integer value2) {
            addCriterion("sortIndex between", value1, value2, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andSortIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("sortIndex not between", value1, value2, "sortIndex");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("operator not between", value1, value2, "operator");
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
