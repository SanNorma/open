start cmd.exe /k "gradlew bootRun --args='--verbose:gc;--XX:+HeapDumpOnOutOfMemoryError;--XX:+UseG1GC;--XX:+PrintCommandLineFlags'"

cd c:\SOFT\kafka\
start cmd.exe /k "zookeeper-server-start.bat config\zookeeper.properties"

start cmd.exe /k "kafka-server-start.bat config\server.properties"

start cmd.exe /k "kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic tradeSystem --partition 1"

kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 2 --topic tradeSystem