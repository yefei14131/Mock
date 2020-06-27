package org.yefei.qa.mock.model.gen.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TblGrpcRequestScriptExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblGrpcRequestScriptExample() {
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

        public Criteria andScriptIDIsNull() {
            addCriterion("scriptID is null");
            return (Criteria) this;
        }

        public Criteria andScriptIDIsNotNull() {
            addCriterion("scriptID is not null");
            return (Criteria) this;
        }

        public Criteria andScriptIDEqualTo(Integer value) {
            addCriterion("scriptID =", value, "scriptID");
            return (Criteria) this;
        }

        public Criteria andScriptIDNotEqualTo(Integer value) {
            addCriterion("scriptID <>", value, "scriptID");
            return (Criteria) this;
        }

        public Criteria andScriptIDGreaterThan(Integer value) {
            addCriterion("scriptID >", value, "scriptID");
            return (Criteria) this;
        }

        public Criteria andScriptIDGreaterThanOrEqualTo(Integer value) {
            addCriterion("scriptID >=", value, "scriptID");
            return (Criteria) this;
        }

        public Criteria andScriptIDLessThan(Integer value) {
            addCriterion("scriptID <", value, "scriptID");
            return (Criteria) this;
        }

        public Criteria andScriptIDLessThanOrEqualTo(Integer value) {
            addCriterion("scriptID <=", value, "scriptID");
            return (Criteria) this;
        }

        public Criteria andScriptIDIn(List<Integer> values) {
            addCriterion("scriptID in", values, "scriptID");
            return (Criteria) this;
        }

        public Criteria andScriptIDNotIn(List<Integer> values) {
            addCriterion("scriptID not in", values, "scriptID");
            return (Criteria) this;
        }

        public Criteria andScriptIDBetween(Integer value1, Integer value2) {
            addCriterion("scriptID between", value1, value2, "scriptID");
            return (Criteria) this;
        }

        public Criteria andScriptIDNotBetween(Integer value1, Integer value2) {
            addCriterion("scriptID not between", value1, value2, "scriptID");
            return (Criteria) this;
        }

        public Criteria andGroupIDIsNull() {
            addCriterion("groupID is null");
            return (Criteria) this;
        }

        public Criteria andGroupIDIsNotNull() {
            addCriterion("groupID is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIDEqualTo(Integer value) {
            addCriterion("groupID =", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDNotEqualTo(Integer value) {
            addCriterion("groupID <>", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDGreaterThan(Integer value) {
            addCriterion("groupID >", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDGreaterThanOrEqualTo(Integer value) {
            addCriterion("groupID >=", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDLessThan(Integer value) {
            addCriterion("groupID <", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDLessThanOrEqualTo(Integer value) {
            addCriterion("groupID <=", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDIn(List<Integer> values) {
            addCriterion("groupID in", values, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDNotIn(List<Integer> values) {
            addCriterion("groupID not in", values, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDBetween(Integer value1, Integer value2) {
            addCriterion("groupID between", value1, value2, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDNotBetween(Integer value1, Integer value2) {
            addCriterion("groupID not between", value1, value2, "groupID");
            return (Criteria) this;
        }

        public Criteria andScriptLanguageIsNull() {
            addCriterion("scriptLanguage is null");
            return (Criteria) this;
        }

        public Criteria andScriptLanguageIsNotNull() {
            addCriterion("scriptLanguage is not null");
            return (Criteria) this;
        }

        public Criteria andScriptLanguageEqualTo(String value) {
            addCriterion("scriptLanguage =", value, "scriptLanguage");
            return (Criteria) this;
        }

        public Criteria andScriptLanguageNotEqualTo(String value) {
            addCriterion("scriptLanguage <>", value, "scriptLanguage");
            return (Criteria) this;
        }

        public Criteria andScriptLanguageGreaterThan(String value) {
            addCriterion("scriptLanguage >", value, "scriptLanguage");
            return (Criteria) this;
        }

        public Criteria andScriptLanguageGreaterThanOrEqualTo(String value) {
            addCriterion("scriptLanguage >=", value, "scriptLanguage");
            return (Criteria) this;
        }

        public Criteria andScriptLanguageLessThan(String value) {
            addCriterion("scriptLanguage <", value, "scriptLanguage");
            return (Criteria) this;
        }

        public Criteria andScriptLanguageLessThanOrEqualTo(String value) {
            addCriterion("scriptLanguage <=", value, "scriptLanguage");
            return (Criteria) this;
        }

        public Criteria andScriptLanguageLike(String value) {
            addCriterion("scriptLanguage like", value, "scriptLanguage");
            return (Criteria) this;
        }

        public Criteria andScriptLanguageNotLike(String value) {
            addCriterion("scriptLanguage not like", value, "scriptLanguage");
            return (Criteria) this;
        }

        public Criteria andScriptLanguageIn(List<String> values) {
            addCriterion("scriptLanguage in", values, "scriptLanguage");
            return (Criteria) this;
        }

        public Criteria andScriptLanguageNotIn(List<String> values) {
            addCriterion("scriptLanguage not in", values, "scriptLanguage");
            return (Criteria) this;
        }

        public Criteria andScriptLanguageBetween(String value1, String value2) {
            addCriterion("scriptLanguage between", value1, value2, "scriptLanguage");
            return (Criteria) this;
        }

        public Criteria andScriptLanguageNotBetween(String value1, String value2) {
            addCriterion("scriptLanguage not between", value1, value2, "scriptLanguage");
            return (Criteria) this;
        }

        public Criteria andServiceNameIsNull() {
            addCriterion("serviceName is null");
            return (Criteria) this;
        }

        public Criteria andServiceNameIsNotNull() {
            addCriterion("serviceName is not null");
            return (Criteria) this;
        }

        public Criteria andServiceNameEqualTo(String value) {
            addCriterion("serviceName =", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotEqualTo(String value) {
            addCriterion("serviceName <>", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameGreaterThan(String value) {
            addCriterion("serviceName >", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameGreaterThanOrEqualTo(String value) {
            addCriterion("serviceName >=", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameLessThan(String value) {
            addCriterion("serviceName <", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameLessThanOrEqualTo(String value) {
            addCriterion("serviceName <=", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameLike(String value) {
            addCriterion("serviceName like", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotLike(String value) {
            addCriterion("serviceName not like", value, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameIn(List<String> values) {
            addCriterion("serviceName in", values, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotIn(List<String> values) {
            addCriterion("serviceName not in", values, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameBetween(String value1, String value2) {
            addCriterion("serviceName between", value1, value2, "serviceName");
            return (Criteria) this;
        }

        public Criteria andServiceNameNotBetween(String value1, String value2) {
            addCriterion("serviceName not between", value1, value2, "serviceName");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNull() {
            addCriterion("methodName is null");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNotNull() {
            addCriterion("methodName is not null");
            return (Criteria) this;
        }

        public Criteria andMethodNameEqualTo(String value) {
            addCriterion("methodName =", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotEqualTo(String value) {
            addCriterion("methodName <>", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThan(String value) {
            addCriterion("methodName >", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThanOrEqualTo(String value) {
            addCriterion("methodName >=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThan(String value) {
            addCriterion("methodName <", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThanOrEqualTo(String value) {
            addCriterion("methodName <=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLike(String value) {
            addCriterion("methodName like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotLike(String value) {
            addCriterion("methodName not like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameIn(List<String> values) {
            addCriterion("methodName in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotIn(List<String> values) {
            addCriterion("methodName not in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameBetween(String value1, String value2) {
            addCriterion("methodName between", value1, value2, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotBetween(String value1, String value2) {
            addCriterion("methodName not between", value1, value2, "methodName");
            return (Criteria) this;
        }

        public Criteria andIsActiveIsNull() {
            addCriterion("isActive is null");
            return (Criteria) this;
        }

        public Criteria andIsActiveIsNotNull() {
            addCriterion("isActive is not null");
            return (Criteria) this;
        }

        public Criteria andIsActiveEqualTo(Boolean value) {
            addCriterion("isActive =", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotEqualTo(Boolean value) {
            addCriterion("isActive <>", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThan(Boolean value) {
            addCriterion("isActive >", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isActive >=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThan(Boolean value) {
            addCriterion("isActive <", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThanOrEqualTo(Boolean value) {
            addCriterion("isActive <=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveIn(List<Boolean> values) {
            addCriterion("isActive in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotIn(List<Boolean> values) {
            addCriterion("isActive not in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveBetween(Boolean value1, Boolean value2) {
            addCriterion("isActive between", value1, value2, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isActive not between", value1, value2, "isActive");
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
