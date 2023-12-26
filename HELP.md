# 帮助文档

### IDEA中遇到的问题

#### 1. Springboot启动失败
```
18:12:51.070 [main] ERROR org.springframework.boot.SpringApplication -- Application run failed
org.yaml.snakeyaml.scanner.ScannerException: while scanning for the next token
found character '@' that cannot start any token. (Do not use @ for indentation)
 in 'reader', line 70, column 12:
      version: @fnk-service.version@

# 解决方案
一定要选Profiles中的dev或者自己配置的环境
maven install 
```

