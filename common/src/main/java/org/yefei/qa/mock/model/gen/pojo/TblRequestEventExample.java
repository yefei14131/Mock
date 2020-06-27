package org.yefei.qa.mock.model.gen.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TblRequestEventExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblRequestEventExample() {
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

        public Criteria andRequestEventIDIsNull() {
            addCriterion("requestEventID is null");
            return (Criteria) this;
        }

        public Criteria andRequestEventIDIsNotNull() {
            addCriterion("requestEventID is not null");
            return (Criteria) this;
        }

        public Criteria andRequestEventIDEqualTo(Integer value) {
            addCriterion("requestEventID =", value, "requestEventID");
            return (Criteria) this;
        }

        public Criteria andRequestEventIDNotEqualTo(Integer value) {
            addCriterion("requestEventID <>", value, "requestEventID");
            return (Criteria) this;
        }

        public Criteria andRequestEventIDGreaterThan(Integer value) {
            addCriterion("requestEventID >", value, "requestEventID");
            return (Criteria) this;
        }

        public Criteria andRequestEventIDGreaterThanOrEqualTo(Integer value) {
            addCriterion("requestEventID >=", value, "requestEventID");
            return (Criteria) this;
        }

        public Criteria andRequestEventIDLessThan(Integer value) {
            addCriterion("requestEventID <", value, "requestEventID");
            return (Criteria) this;
        }

        public Criteria andRequestEventIDLessThanOrEqualTo(Integer value) {
            addCriterion("requestEventID <=", value, "requestEventID");
            return (Criteria) this;
        }

        public Criteria andRequestEventIDIn(List<Integer> values) {
            addCriterion("requestEventID in", values, "requestEventID");
            return (Criteria) this;
        }

        public Criteria andRequestEventIDNotIn(List<Integer> values) {
            addCriterion("requestEventID not in", values, "requestEventID");
            return (Criteria) this;
        }

        public Criteria andRequestEventIDBetween(Integer value1, Integer value2) {
            addCriterion("requestEventID between", value1, value2, "requestEventID");
            return (Criteria) this;
        }

        public Criteria andRequestEventIDNotBetween(Integer value1, Integer value2) {
            addCriterion("requestEventID not between", value1, value2, "requestEventID");
            return (Criteria) this;
        }

        public Criteria andTraceIDIsNull() {
            addCriterion("traceID is null");
            return (Criteria) this;
        }

        public Criteria andTraceIDIsNotNull() {
            addCriterion("traceID is not null");
            return (Criteria) this;
        }

        public Criteria andTraceIDEqualTo(String value) {
            addCriterion("traceID =", value, "traceID");
            return (Criteria) this;
        }

        public Criteria andTraceIDNotEqualTo(String value) {
            addCriterion("traceID <>", value, "traceID");
            return (Criteria) this;
        }

        public Criteria andTraceIDGreaterThan(String value) {
            addCriterion("traceID >", value, "traceID");
            return (Criteria) this;
        }

        public Criteria andTraceIDGreaterThanOrEqualTo(String value) {
            addCriterion("traceID >=", value, "traceID");
            return (Criteria) this;
        }

        public Criteria andTraceIDLessThan(String value) {
            addCriterion("traceID <", value, "traceID");
            return (Criteria) this;
        }

        public Criteria andTraceIDLessThanOrEqualTo(String value) {
            addCriterion("traceID <=", value, "traceID");
            return (Criteria) this;
        }

        public Criteria andTraceIDLike(String value) {
            addCriterion("traceID like", value, "traceID");
            return (Criteria) this;
        }

        public Criteria andTraceIDNotLike(String value) {
            addCriterion("traceID not like", value, "traceID");
            return (Criteria) this;
        }

        public Criteria andTraceIDIn(List<String> values) {
            addCriterion("traceID in", values, "traceID");
            return (Criteria) this;
        }

        public Criteria andTraceIDNotIn(List<String> values) {
            addCriterion("traceID not in", values, "traceID");
            return (Criteria) this;
        }

        public Criteria andTraceIDBetween(String value1, String value2) {
            addCriterion("traceID between", value1, value2, "traceID");
            return (Criteria) this;
        }

        public Criteria andTraceIDNotBetween(String value1, String value2) {
            addCriterion("traceID not between", value1, value2, "traceID");
            return (Criteria) this;
        }

        public Criteria andEventNameIsNull() {
            addCriterion("eventName is null");
            return (Criteria) this;
        }

        public Criteria andEventNameIsNotNull() {
            addCriterion("eventName is not null");
            return (Criteria) this;
        }

        public Criteria andEventNameEqualTo(String value) {
            addCriterion("eventName =", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameNotEqualTo(String value) {
            addCriterion("eventName <>", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameGreaterThan(String value) {
            addCriterion("eventName >", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameGreaterThanOrEqualTo(String value) {
            addCriterion("eventName >=", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameLessThan(String value) {
            addCriterion("eventName <", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameLessThanOrEqualTo(String value) {
            addCriterion("eventName <=", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameLike(String value) {
            addCriterion("eventName like", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameNotLike(String value) {
            addCriterion("eventName not like", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameIn(List<String> values) {
            addCriterion("eventName in", values, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameNotIn(List<String> values) {
            addCriterion("eventName not in", values, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameBetween(String value1, String value2) {
            addCriterion("eventName between", value1, value2, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameNotBetween(String value1, String value2) {
            addCriterion("eventName not between", value1, value2, "eventName");
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
