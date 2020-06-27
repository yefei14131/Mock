Mock
# 编译

### admin
mvn clean package -DskipTests -Padmin

### rest
mvn clean package -DskipTests -Prest

### grpc
mvn clean package -DskipTests -Pgrpc

### scheduler
mvn clean package -DskipTests -Pscheduler

### agent
mvn clean package -DskipTests -Pagent

agent编译结果目录：mock-agent/mock-agent

agent压缩包：mock-agent/agent-dist/mock-agent.zip   mock-agent/agent-dist/mock-agent.tar.gz
