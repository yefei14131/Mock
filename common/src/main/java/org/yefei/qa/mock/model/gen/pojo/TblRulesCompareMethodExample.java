package org.yefei.qa.mock.model.gen.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TblRulesCompareMethodExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblRulesCompareMethodExample() {
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

        public Criteria andCompareMethodIDIsNull() {
            addCriterion("compareMethodID is null");
            return (Criteria) this;
        }

        public Criteria andCompareMethodIDIsNotNull() {
            addCriterion("compareMethodID is not null");
            return (Criteria) this;
        }

        public Criteria andCompareMethodIDEqualTo(Integer value) {
            addCriterion("compareMethodID =", value, "compareMethodID");
            return (Criteria) this;
        }

        public Criteria andCompareMethodIDNotEqualTo(Integer value) {
            addCriterion("compareMethodID <>", value, "compareMethodID");
            return (Criteria) this;
        }

        public Criteria andCompareMethodIDGreaterThan(Integer value) {
            addCriterion("compareMethodID >", value, "compareMethodID");
            return (Criteria) this;
        }

        public Criteria andCompareMethodIDGreaterThanOrEqualTo(Integer value) {
            addCriterion("compareMethodID >=", value, "compareMethodID");
            return (Criteria) this;
        }

        public Criteria andCompareMethodIDLessThan(Integer value) {
            addCriterion("compareMethodID <", value, "compareMethodID");
            return (Criteria) this;
        }

        public Criteria andCompareMethodIDLessThanOrEqualTo(Integer value) {
            addCriterion("compareMethodID <=", value, "compareMethodID");
            return (Criteria) this;
        }

        public Criteria andCompareMethodIDIn(List<Integer> values) {
            addCriterion("compareMethodID in", values, "compareMethodID");
            return (Criteria) this;
        }

        public Criteria andCompareMethodIDNotIn(List<Integer> values) {
            addCriterion("compareMethodID not in", values, "compareMethodID");
            return (Criteria) this;
        }

        public Criteria andCompareMethodIDBetween(Integer value1, Integer value2) {
            addCriterion("compareMethodID between", value1, value2, "compareMethodID");
            return (Criteria) this;
        }

        public Criteria andCompareMethodIDNotBetween(Integer value1, Integer value2) {
            addCriterion("compareMethodID not between", value1, value2, "compareMethodID");
            return (Criteria) this;
        }

        public Criteria andCompareMethodIsNull() {
            addCriterion("compareMethod is null");
            return (Criteria) this;
        }

        public Criteria andCompareMethodIsNotNull() {
            addCriterion("compareMethod is not null");
            return (Criteria) this;
        }

        public Criteria andCompareMethodEqualTo(String value) {
            addCriterion("compareMethod =", value, "compareMethod");
            return (Criteria) this;
        }

        public Criteria andCompareMethodNotEqualTo(String value) {
            addCriterion("compareMethod <>", value, "compareMethod");
            return (Criteria) this;
        }

        public Criteria andCompareMethodGreaterThan(String value) {
            addCriterion("compareMethod >", value, "compareMethod");
            return (Criteria) this;
        }

        public Criteria andCompareMethodGreaterThanOrEqualTo(String value) {
            addCriterion("compareMethod >=", value, "compareMethod");
            return (Criteria) this;
        }

        public Criteria andCompareMethodLessThan(String value) {
            addCriterion("compareMethod <", value, "compareMethod");
            return (Criteria) this;
        }

        public Criteria andCompareMethodLessThanOrEqualTo(String value) {
            addCriterion("compareMethod <=", value, "compareMethod");
            return (Criteria) this;
        }

        public Criteria andCompareMethodLike(String value) {
            addCriterion("compareMethod like", value, "compareMethod");
            return (Criteria) this;
        }

        public Criteria andCompareMethodNotLike(String value) {
            addCriterion("compareMethod not like", value, "compareMethod");
            return (Criteria) this;
        }

        public Criteria andCompareMethodIn(List<String> values) {
            addCriterion("compareMethod in", values, "compareMethod");
            return (Criteria) this;
        }

        public Criteria andCompareMethodNotIn(List<String> values) {
            addCriterion("compareMethod not in", values, "compareMethod");
            return (Criteria) this;
        }

        public Criteria andCompareMethodBetween(String value1, String value2) {
            addCriterion("compareMethod between", value1, value2, "compareMethod");
            return (Criteria) this;
        }

        public Criteria andCompareMethodNotBetween(String value1, String value2) {
            addCriterion("compareMethod not between", value1, value2, "compareMethod");
            return (Criteria) this;
        }

        public Criteria andCompareMemoIsNull() {
            addCriterion("compareMemo is null");
            return (Criteria) this;
        }

        public Criteria andCompareMemoIsNotNull() {
            addCriterion("compareMemo is not null");
            return (Criteria) this;
        }

        public Criteria andCompareMemoEqualTo(String value) {
            addCriterion("compareMemo =", value, "compareMemo");
            return (Criteria) this;
        }

        public Criteria andCompareMemoNotEqualTo(String value) {
            addCriterion("compareMemo <>", value, "compareMemo");
            return (Criteria) this;
        }

        public Criteria andCompareMemoGreaterThan(String value) {
            addCriterion("compareMemo >", value, "compareMemo");
            return (Criteria) this;
        }

        public Criteria andCompareMemoGreaterThanOrEqualTo(String value) {
            addCriterion("compareMemo >=", value, "compareMemo");
            return (Criteria) this;
        }

        public Criteria andCompareMemoLessThan(String value) {
            addCriterion("compareMemo <", value, "compareMemo");
            return (Criteria) this;
        }

        public Criteria andCompareMemoLessThanOrEqualTo(String value) {
            addCriterion("compareMemo <=", value, "compareMemo");
            return (Criteria) this;
        }

        public Criteria andCompareMemoLike(String value) {
            addCriterion("compareMemo like", value, "compareMemo");
            return (Criteria) this;
        }

        public Criteria andCompareMemoNotLike(String value) {
            addCriterion("compareMemo not like", value, "compareMemo");
            return (Criteria) this;
        }

        public Criteria andCompareMemoIn(List<String> values) {
            addCriterion("compareMemo in", values, "compareMemo");
            return (Criteria) this;
        }

        public Criteria andCompareMemoNotIn(List<String> values) {
            addCriterion("compareMemo not in", values, "compareMemo");
            return (Criteria) this;
        }

        public Criteria andCompareMemoBetween(String value1, String value2) {
            addCriterion("compareMemo between", value1, value2, "compareMemo");
            return (Criteria) this;
        }

        public Criteria andCompareMemoNotBetween(String value1, String value2) {
            addCriterion("compareMemo not between", value1, value2, "compareMemo");
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
