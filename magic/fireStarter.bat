start cmd.exe /k "gradlew bootRun"

cd c:\SOFT\kafka\
start cmd.exe /k "zookeeper-server-start.bat config\zookeeper.properties"

start cmd.exe /k "kafka-server-start.bat config\server.properties"

start cmd.exe /k "kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic tradeSystem"

kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic tradeSystem