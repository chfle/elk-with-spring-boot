# elk-with-spring-boot

## FAQ

### Docker with ELK

---
**NOTE**

To run properly 

Linux
```shell
sysctl -w vm.max_map_count=262144
```

Windows

```cmd
 wsl -d docker-desktop
 sysctl -w vm.max_map_count=262144
```
---
#### Start Container
```shell
sudo docker run -p 5601:5601 -p 9200:9200 -p 5044:5044 -it --net elk -d --memory=4096m -v /home/chris/Downloads/Downloads/ELK_example/logs:/mnt/logs --name elk sebp/elk
```

## How to access the log file from Logstash

1. Open a shell prompt in the container

```shell
sudo docker exec -it elk /bin/bash
```

2. Create a `logstash.conf` file 

```shell
vim /etc/logstash/conf.d/logstash.conf

# logstash.conf 
input {
  file {
    type => "log"
    path => "/mnt/logs/application.log"    # Make sure you provide the absolute path of the file here
  }
}

filter {
  if [message] =~ "\tat" {
    grok {
      match => ["message", "^(\tat)"]
      add_tag => ["stacktrace"]
    }
  }
 
}

output {
  stdout {
    codec => rubydebug
  }
 
  elasticsearch {
    hosts => ["localhost"]
  }
}
```

3. restart your elk container

```shell
sudo docker restart elk
```

### How to view your logs with kibana?

1. Check what indexes you have

```shell
http://localhost:9200/_cat/indices
```

2. Next, go to 
```shell
http://localhost:5601/app/management/kibana/indexPatterns
```
3. Enter a name and type your index name as `NAME*`

4. Optionally, in the next step, you can pick a field for filtering the data. 
You can choose `@timestamp` and then click on Create index pattern.
5. View your logs
```shell
http://localhost:5601/app/discover
```