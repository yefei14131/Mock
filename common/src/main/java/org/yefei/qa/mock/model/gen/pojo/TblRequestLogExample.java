package org.yefei.qa.mock.model.gen.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TblRequestLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblRequestLogExample() {
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

        public Criteria andRequestPathIsNull() {
            addCriterion("requestPath is null");
            return (Criteria) this;
        }

        public Criteria andRequestPathIsNotNull() {
            addCriterion("requestPath is not null");
            return (Criteria) this;
        }

        public Criteria andRequestPathEqualTo(String value) {
            addCriterion("requestPath =", value, "requestPath");
            return (Criteria) this;
        }

        public Criteria andRequestPathNotEqualTo(String value) {
            addCriterion("requestPath <>", value, "requestPath");
            return (Criteria) this;
        }

        public Criteria andRequestPathGreaterThan(String value) {
            addCriterion("requestPath >", value, "requestPath");
            return (Criteria) this;
        }

        public Criteria andRequestPathGreaterThanOrEqualTo(String value) {
            addCriterion("requestPath >=", value, "requestPath");
            return (Criteria) this;
        }

        public Criteria andRequestPathLessThan(String value) {
            addCriterion("requestPath <", value, "requestPath");
            return (Criteria) this;
        }

        public Criteria andRequestPathLessThanOrEqualTo(String value) {
            addCriterion("requestPath <=", value, "requestPath");
            return (Criteria) this;
        }

        public Criteria andRequestPathLike(String value) {
            addCriterion("requestPath like", value, "requestPath");
            return (Criteria) this;
        }

        public Criteria andRequestPathNotLike(String value) {
            addCriterion("requestPath not like", value, "requestPath");
            return (Criteria) this;
        }

        public Criteria andRequestPathIn(List<String> values) {
            addCriterion("requestPath in", values, "requestPath");
            return (Criteria) this;
        }

        public Criteria andRequestPathNotIn(List<String> values) {
            addCriterion("requestPath not in", values, "requestPath");
            return (Criteria) this;
        }

        public Criteria andRequestPathBetween(String value1, String value2) {
            addCriterion("requestPath between", value1, value2, "requestPath");
            return (Criteria) this;
        }

        public Criteria andRequestPathNotBetween(String value1, String value2) {
            addCriterion("requestPath not between", value1, value2, "requestPath");
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

        public Criteria andRequestTimeIsNull() {
            addCriterion("requestTime is null");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIsNotNull() {
            addCriterion("requestTime is not null");
            return (Criteria) this;
        }

        public Criteria andRequestTimeEqualTo(Date value) {
            addCriterion("requestTime =", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotEqualTo(Date value) {
            addCriterion("requestTime <>", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeGreaterThan(Date value) {
            addCriterion("requestTime >", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("requestTime >=", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeLessThan(Date value) {
            addCriterion("requestTime <", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeLessThanOrEqualTo(Date value) {
            addCriterion("requestTime <=", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIn(List<Date> values) {
            addCriterion("requestTime in", values, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotIn(List<Date> values) {
            addCriterion("requestTime not in", values, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeBetween(Date value1, Date value2) {
            addCriterion("requestTime between", value1, value2, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotBetween(Date value1, Date value2) {
            addCriterion("requestTime not between", value1, value2, "requestTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeIsNull() {
            addCriterion("responseTime is null");
            return (Criteria) this;
        }

        public Criteria andResponseTimeIsNotNull() {
            addCriterion("responseTime is not null");
            return (Criteria) this;
        }

        public Criteria andResponseTimeEqualTo(Date value) {
            addCriterion("responseTime =", value, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeNotEqualTo(Date value) {
            addCriterion("responseTime <>", value, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeGreaterThan(Date value) {
            addCriterion("responseTime >", value, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("responseTime >=", value, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeLessThan(Date value) {
            addCriterion("responseTime <", value, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeLessThanOrEqualTo(Date value) {
            addCriterion("responseTime <=", value, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeIn(List<Date> values) {
            addCriterion("responseTime in", values, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeNotIn(List<Date> values) {
            addCriterion("responseTime not in", values, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeBetween(Date value1, Date value2) {
            addCriterion("responseTime between", value1, value2, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeNotBetween(Date value1, Date value2) {
            addCriterion("responseTime not between", value1, value2, "responseTime");
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
