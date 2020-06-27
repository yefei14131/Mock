package org.yefei.qa.mock.model.gen.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TblGrpcInterfaceJarExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblGrpcInterfaceJarExample() {
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

        public Criteria andItemIDIsNull() {
            addCriterion("itemID is null");
            return (Criteria) this;
        }

        public Criteria andItemIDIsNotNull() {
            addCriterion("itemID is not null");
            return (Criteria) this;
        }

        public Criteria andItemIDEqualTo(Integer value) {
            addCriterion("itemID =", value, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDNotEqualTo(Integer value) {
            addCriterion("itemID <>", value, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDGreaterThan(Integer value) {
            addCriterion("itemID >", value, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDGreaterThanOrEqualTo(Integer value) {
            addCriterion("itemID >=", value, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDLessThan(Integer value) {
            addCriterion("itemID <", value, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDLessThanOrEqualTo(Integer value) {
            addCriterion("itemID <=", value, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDIn(List<Integer> values) {
            addCriterion("itemID in", values, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDNotIn(List<Integer> values) {
            addCriterion("itemID not in", values, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDBetween(Integer value1, Integer value2) {
            addCriterion("itemID between", value1, value2, "itemID");
            return (Criteria) this;
        }

        public Criteria andItemIDNotBetween(Integer value1, Integer value2) {
            addCriterion("itemID not between", value1, value2, "itemID");
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

        public Criteria andGroupIDIsNull() {
            addCriterion("groupID is null");
            return (Criteria) this;
        }

        public Criteria andGroupIDIsNotNull() {
            addCriterion("groupID is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIDEqualTo(String value) {
            addCriterion("groupID =", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDNotEqualTo(String value) {
            addCriterion("groupID <>", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDGreaterThan(String value) {
            addCriterion("groupID >", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDGreaterThanOrEqualTo(String value) {
            addCriterion("groupID >=", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDLessThan(String value) {
            addCriterion("groupID <", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDLessThanOrEqualTo(String value) {
            addCriterion("groupID <=", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDLike(String value) {
            addCriterion("groupID like", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDNotLike(String value) {
            addCriterion("groupID not like", value, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDIn(List<String> values) {
            addCriterion("groupID in", values, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDNotIn(List<String> values) {
            addCriterion("groupID not in", values, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDBetween(String value1, String value2) {
            addCriterion("groupID between", value1, value2, "groupID");
            return (Criteria) this;
        }

        public Criteria andGroupIDNotBetween(String value1, String value2) {
            addCriterion("groupID not between", value1, value2, "groupID");
            return (Criteria) this;
        }

        public Criteria andArtifactIDIsNull() {
            addCriterion("artifactID is null");
            return (Criteria) this;
        }

        public Criteria andArtifactIDIsNotNull() {
            addCriterion("artifactID is not null");
            return (Criteria) this;
        }

        public Criteria andArtifactIDEqualTo(String value) {
            addCriterion("artifactID =", value, "artifactID");
            return (Criteria) this;
        }

        public Criteria andArtifactIDNotEqualTo(String value) {
            addCriterion("artifactID <>", value, "artifactID");
            return (Criteria) this;
        }

        public Criteria andArtifactIDGreaterThan(String value) {
            addCriterion("artifactID >", value, "artifactID");
            return (Criteria) this;
        }

        public Criteria andArtifactIDGreaterThanOrEqualTo(String value) {
            addCriterion("artifactID >=", value, "artifactID");
            return (Criteria) this;
        }

        public Criteria andArtifactIDLessThan(String value) {
            addCriterion("artifactID <", value, "artifactID");
            return (Criteria) this;
        }

        public Criteria andArtifactIDLessThanOrEqualTo(String value) {
            addCriterion("artifactID <=", value, "artifactID");
            return (Criteria) this;
        }

        public Criteria andArtifactIDLike(String value) {
            addCriterion("artifactID like", value, "artifactID");
            return (Criteria) this;
        }

        public Criteria andArtifactIDNotLike(String value) {
            addCriterion("artifactID not like", value, "artifactID");
            return (Criteria) this;
        }

        public Criteria andArtifactIDIn(List<String> values) {
            addCriterion("artifactID in", values, "artifactID");
            return (Criteria) this;
        }

        public Criteria andArtifactIDNotIn(List<String> values) {
            addCriterion("artifactID not in", values, "artifactID");
            return (Criteria) this;
        }

        public Criteria andArtifactIDBetween(String value1, String value2) {
            addCriterion("artifactID between", value1, value2, "artifactID");
            return (Criteria) this;
        }

        public Criteria andArtifactIDNotBetween(String value1, String value2) {
            addCriterion("artifactID not between", value1, value2, "artifactID");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("version like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("version not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andJarUrlIsNull() {
            addCriterion("jarUrl is null");
            return (Criteria) this;
        }

        public Criteria andJarUrlIsNotNull() {
            addCriterion("jarUrl is not null");
            return (Criteria) this;
        }

        public Criteria andJarUrlEqualTo(String value) {
            addCriterion("jarUrl =", value, "jarUrl");
            return (Criteria) this;
        }

        public Criteria andJarUrlNotEqualTo(String value) {
            addCriterion("jarUrl <>", value, "jarUrl");
            return (Criteria) this;
        }

        public Criteria andJarUrlGreaterThan(String value) {
            addCriterion("jarUrl >", value, "jarUrl");
            return (Criteria) this;
        }

        public Criteria andJarUrlGreaterThanOrEqualTo(String value) {
            addCriterion("jarUrl >=", value, "jarUrl");
            return (Criteria) this;
        }

        public Criteria andJarUrlLessThan(String value) {
            addCriterion("jarUrl <", value, "jarUrl");
            return (Criteria) this;
        }

        public Criteria andJarUrlLessThanOrEqualTo(String value) {
            addCriterion("jarUrl <=", value, "jarUrl");
            return (Criteria) this;
        }

        public Criteria andJarUrlLike(String value) {
            addCriterion("jarUrl like", value, "jarUrl");
            return (Criteria) this;
        }

        public Criteria andJarUrlNotLike(String value) {
            addCriterion("jarUrl not like", value, "jarUrl");
            return (Criteria) this;
        }

        public Criteria andJarUrlIn(List<String> values) {
            addCriterion("jarUrl in", values, "jarUrl");
            return (Criteria) this;
        }

        public Criteria andJarUrlNotIn(List<String> values) {
            addCriterion("jarUrl not in", values, "jarUrl");
            return (Criteria) this;
        }

        public Criteria andJarUrlBetween(String value1, String value2) {
            addCriterion("jarUrl between", value1, value2, "jarUrl");
            return (Criteria) this;
        }

        public Criteria andJarUrlNotBetween(String value1, String value2) {
            addCriterion("jarUrl not between", value1, value2, "jarUrl");
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
