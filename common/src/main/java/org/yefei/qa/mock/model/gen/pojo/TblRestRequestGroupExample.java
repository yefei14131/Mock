package org.yefei.qa.mock.model.gen.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TblRestRequestGroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblRestRequestGroupExample() {
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

        public Criteria andGroupCodeIsNull() {
            addCriterion("groupCode is null");
            return (Criteria) this;
        }

        public Criteria andGroupCodeIsNotNull() {
            addCriterion("groupCode is not null");
            return (Criteria) this;
        }

        public Criteria andGroupCodeEqualTo(String value) {
            addCriterion("groupCode =", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeNotEqualTo(String value) {
            addCriterion("groupCode <>", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeGreaterThan(String value) {
            addCriterion("groupCode >", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeGreaterThanOrEqualTo(String value) {
            addCriterion("groupCode >=", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeLessThan(String value) {
            addCriterion("groupCode <", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeLessThanOrEqualTo(String value) {
            addCriterion("groupCode <=", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeLike(String value) {
            addCriterion("groupCode like", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeNotLike(String value) {
            addCriterion("groupCode not like", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeIn(List<String> values) {
            addCriterion("groupCode in", values, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeNotIn(List<String> values) {
            addCriterion("groupCode not in", values, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeBetween(String value1, String value2) {
            addCriterion("groupCode between", value1, value2, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeNotBetween(String value1, String value2) {
            addCriterion("groupCode not between", value1, value2, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("groupName is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("groupName is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("groupName =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("groupName <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("groupName >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("groupName >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("groupName <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("groupName <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("groupName like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("groupName not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("groupName in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("groupName not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("groupName between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("groupName not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andSourceHostIsNull() {
            addCriterion("sourceHost is null");
            return (Criteria) this;
        }

        public Criteria andSourceHostIsNotNull() {
            addCriterion("sourceHost is not null");
            return (Criteria) this;
        }

        public Criteria andSourceHostEqualTo(String value) {
            addCriterion("sourceHost =", value, "sourceHost");
            return (Criteria) this;
        }

        public Criteria andSourceHostNotEqualTo(String value) {
            addCriterion("sourceHost <>", value, "sourceHost");
            return (Criteria) this;
        }

        public Criteria andSourceHostGreaterThan(String value) {
            addCriterion("sourceHost >", value, "sourceHost");
            return (Criteria) this;
        }

        public Criteria andSourceHostGreaterThanOrEqualTo(String value) {
            addCriterion("sourceHost >=", value, "sourceHost");
            return (Criteria) this;
        }

        public Criteria andSourceHostLessThan(String value) {
            addCriterion("sourceHost <", value, "sourceHost");
            return (Criteria) this;
        }

        public Criteria andSourceHostLessThanOrEqualTo(String value) {
            addCriterion("sourceHost <=", value, "sourceHost");
            return (Criteria) this;
        }

        public Criteria andSourceHostLike(String value) {
            addCriterion("sourceHost like", value, "sourceHost");
            return (Criteria) this;
        }

        public Criteria andSourceHostNotLike(String value) {
            addCriterion("sourceHost not like", value, "sourceHost");
            return (Criteria) this;
        }

        public Criteria andSourceHostIn(List<String> values) {
            addCriterion("sourceHost in", values, "sourceHost");
            return (Criteria) this;
        }

        public Criteria andSourceHostNotIn(List<String> values) {
            addCriterion("sourceHost not in", values, "sourceHost");
            return (Criteria) this;
        }

        public Criteria andSourceHostBetween(String value1, String value2) {
            addCriterion("sourceHost between", value1, value2, "sourceHost");
            return (Criteria) this;
        }

        public Criteria andSourceHostNotBetween(String value1, String value2) {
            addCriterion("sourceHost not between", value1, value2, "sourceHost");
            return (Criteria) this;
        }

        public Criteria andSourcePortIsNull() {
            addCriterion("sourcePort is null");
            return (Criteria) this;
        }

        public Criteria andSourcePortIsNotNull() {
            addCriterion("sourcePort is not null");
            return (Criteria) this;
        }

        public Criteria andSourcePortEqualTo(Integer value) {
            addCriterion("sourcePort =", value, "sourcePort");
            return (Criteria) this;
        }

        public Criteria andSourcePortNotEqualTo(Integer value) {
            addCriterion("sourcePort <>", value, "sourcePort");
            return (Criteria) this;
        }

        public Criteria andSourcePortGreaterThan(Integer value) {
            addCriterion("sourcePort >", value, "sourcePort");
            return (Criteria) this;
        }

        public Criteria andSourcePortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sourcePort >=", value, "sourcePort");
            return (Criteria) this;
        }

        public Criteria andSourcePortLessThan(Integer value) {
            addCriterion("sourcePort <", value, "sourcePort");
            return (Criteria) this;
        }

        public Criteria andSourcePortLessThanOrEqualTo(Integer value) {
            addCriterion("sourcePort <=", value, "sourcePort");
            return (Criteria) this;
        }

        public Criteria andSourcePortIn(List<Integer> values) {
            addCriterion("sourcePort in", values, "sourcePort");
            return (Criteria) this;
        }

        public Criteria andSourcePortNotIn(List<Integer> values) {
            addCriterion("sourcePort not in", values, "sourcePort");
            return (Criteria) this;
        }

        public Criteria andSourcePortBetween(Integer value1, Integer value2) {
            addCriterion("sourcePort between", value1, value2, "sourcePort");
            return (Criteria) this;
        }

        public Criteria andSourcePortNotBetween(Integer value1, Integer value2) {
            addCriterion("sourcePort not between", value1, value2, "sourcePort");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("memo is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("memo is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("memo =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("memo <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("memo >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("memo >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("memo <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("memo <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("memo like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("memo not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("memo in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("memo not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("memo between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("memo not between", value1, value2, "memo");
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

        public Criteria andProxyIPIsNull() {
            addCriterion("proxyIP is null");
            return (Criteria) this;
        }

        public Criteria andProxyIPIsNotNull() {
            addCriterion("proxyIP is not null");
            return (Criteria) this;
        }

        public Criteria andProxyIPEqualTo(String value) {
            addCriterion("proxyIP =", value, "proxyIP");
            return (Criteria) this;
        }

        public Criteria andProxyIPNotEqualTo(String value) {
            addCriterion("proxyIP <>", value, "proxyIP");
            return (Criteria) this;
        }

        public Criteria andProxyIPGreaterThan(String value) {
            addCriterion("proxyIP >", value, "proxyIP");
            return (Criteria) this;
        }

        public Criteria andProxyIPGreaterThanOrEqualTo(String value) {
            addCriterion("proxyIP >=", value, "proxyIP");
            return (Criteria) this;
        }

        public Criteria andProxyIPLessThan(String value) {
            addCriterion("proxyIP <", value, "proxyIP");
            return (Criteria) this;
        }

        public Criteria andProxyIPLessThanOrEqualTo(String value) {
            addCriterion("proxyIP <=", value, "proxyIP");
            return (Criteria) this;
        }

        public Criteria andProxyIPLike(String value) {
            addCriterion("proxyIP like", value, "proxyIP");
            return (Criteria) this;
        }

        public Criteria andProxyIPNotLike(String value) {
            addCriterion("proxyIP not like", value, "proxyIP");
            return (Criteria) this;
        }

        public Criteria andProxyIPIn(List<String> values) {
            addCriterion("proxyIP in", values, "proxyIP");
            return (Criteria) this;
        }

        public Criteria andProxyIPNotIn(List<String> values) {
            addCriterion("proxyIP not in", values, "proxyIP");
            return (Criteria) this;
        }

        public Criteria andProxyIPBetween(String value1, String value2) {
            addCriterion("proxyIP between", value1, value2, "proxyIP");
            return (Criteria) this;
        }

        public Criteria andProxyIPNotBetween(String value1, String value2) {
            addCriterion("proxyIP not between", value1, value2, "proxyIP");
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
