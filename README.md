# elk-with-spring-boot

## FAQ

### Docker with ELK

NOTE: To run properly 

Linux
```shell
sysctl -w vm.max_map_count=262144
```

Windows

```cmd
 wsl -d docker-desktop
 sysctl -w vm.max_map_count=262144
```
#### Start Container
```shell
sudo docker run -p 5601:5601 -p 9200:9200 -p 5044:5044 -it --name elk sebp/elk
```