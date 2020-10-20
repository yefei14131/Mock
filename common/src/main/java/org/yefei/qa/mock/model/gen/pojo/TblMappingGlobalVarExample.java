package org.yefei.qa.mock.model.gen.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TblMappingGlobalVarExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblMappingGlobalVarExample() {
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

        public Criteria andGlobalVarIDIsNull() {
            addCriterion("globalVarID is null");
            return (Criteria) this;
        }

        public Criteria andGlobalVarIDIsNotNull() {
            addCriterion("globalVarID is not null");
            return (Criteria) this;
        }

        public Criteria andGlobalVarIDEqualTo(Integer value) {
            addCriterion("globalVarID =", value, "globalVarID");
            return (Criteria) this;
        }

        public Criteria andGlobalVarIDNotEqualTo(Integer value) {
            addCriterion("globalVarID <>", value, "globalVarID");
            return (Criteria) this;
        }

        public Criteria andGlobalVarIDGreaterThan(Integer value) {
            addCriterion("globalVarID >", value, "globalVarID");
            return (Criteria) this;
        }

        public Criteria andGlobalVarIDGreaterThanOrEqualTo(Integer value) {
            addCriterion("globalVarID >=", value, "globalVarID");
            return (Criteria) this;
        }

        public Criteria andGlobalVarIDLessThan(Integer value) {
            addCriterion("globalVarID <", value, "globalVarID");
            return (Criteria) this;
        }

        public Criteria andGlobalVarIDLessThanOrEqualTo(Integer value) {
            addCriterion("globalVarID <=", value, "globalVarID");
            return (Criteria) this;
        }

        public Criteria andGlobalVarIDIn(List<Integer> values) {
            addCriterion("globalVarID in", values, "globalVarID");
            return (Criteria) this;
        }

        public Criteria andGlobalVarIDNotIn(List<Integer> values) {
            addCriterion("globalVarID not in", values, "globalVarID");
            return (Criteria) this;
        }

        public Criteria andGlobalVarIDBetween(Integer value1, Integer value2) {
            addCriterion("globalVarID between", value1, value2, "globalVarID");
            return (Criteria) this;
        }

        public Criteria andGlobalVarIDNotBetween(Integer value1, Integer value2) {
            addCriterion("globalVarID not between", value1, value2, "globalVarID");
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

        public Criteria andVarNameIsNull() {
            addCriterion("varName is null");
            return (Criteria) this;
        }

        public Criteria andVarNameIsNotNull() {
            addCriterion("varName is not null");
            return (Criteria) this;
        }

        public Criteria andVarNameEqualTo(String value) {
            addCriterion("varName =", value, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameNotEqualTo(String value) {
            addCriterion("varName <>", value, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameGreaterThan(String value) {
            addCriterion("varName >", value, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameGreaterThanOrEqualTo(String value) {
            addCriterion("varName >=", value, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameLessThan(String value) {
            addCriterion("varName <", value, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameLessThanOrEqualTo(String value) {
            addCriterion("varName <=", value, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameLike(String value) {
            addCriterion("varName like", value, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameNotLike(String value) {
            addCriterion("varName not like", value, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameIn(List<String> values) {
            addCriterion("varName in", values, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameNotIn(List<String> values) {
            addCriterion("varName not in", values, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameBetween(String value1, String value2) {
            addCriterion("varName between", value1, value2, "varName");
            return (Criteria) this;
        }

        public Criteria andVarNameNotBetween(String value1, String value2) {
            addCriterion("varName not between", value1, value2, "varName");
            return (Criteria) this;
        }

        public Criteria andVarValueIsNull() {
            addCriterion("varValue is null");
            return (Criteria) this;
        }

        public Criteria andVarValueIsNotNull() {
            addCriterion("varValue is not null");
            return (Criteria) this;
        }

        public Criteria andVarValueEqualTo(String value) {
            addCriterion("varValue =", value, "varValue");
            return (Criteria) this;
        }

        public Criteria andVarValueNotEqualTo(String value) {
            addCriterion("varValue <>", value, "varValue");
            return (Criteria) this;
        }

        public Criteria andVarValueGreaterThan(String value) {
            addCriterion("varValue >", value, "varValue");
            return (Criteria) this;
        }

        public Criteria andVarValueGreaterThanOrEqualTo(String value) {
            addCriterion("varValue >=", value, "varValue");
            return (Criteria) this;
        }

        public Criteria andVarValueLessThan(String value) {
            addCriterion("varValue <", value, "varValue");
            return (Criteria) this;
        }

        public Criteria andVarValueLessThanOrEqualTo(String value) {
            addCriterion("varValue <=", value, "varValue");
            return (Criteria) this;
        }

        public Criteria andVarValueLike(String value) {
            addCriterion("varValue like", value, "varValue");
            return (Criteria) this;
        }

        public Criteria andVarValueNotLike(String value) {
            addCriterion("varValue not like", value, "varValue");
            return (Criteria) this;
        }

        public Criteria andVarValueIn(List<String> values) {
            addCriterion("varValue in", values, "varValue");
            return (Criteria) this;
        }

        public Criteria andVarValueNotIn(List<String> values) {
            addCriterion("varValue not in", values, "varValue");
            return (Criteria) this;
        }

        public Criteria andVarValueBetween(String value1, String value2) {
            addCriterion("varValue between", value1, value2, "varValue");
            return (Criteria) this;
        }

        public Criteria andVarValueNotBetween(String value1, String value2) {
            addCriterion("varValue not between", value1, value2, "varValue");
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