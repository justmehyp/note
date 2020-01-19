# location

Syntax: location [ = | ^~ | ~ | ~* ] url {...}  
Default: —  
Context: server, location  

location 可以定义为字符串前缀匹配，或正则匹配。  
正则匹配有 2 种模式， ~ 大小写敏感（case sensitive），～* 大小写不敏感(case insensitive).  
location 匹配一个请求的过程：
  1) 先进行完全匹配(=)，一旦匹配立即返回
  2) 再进行字符串前缀匹配(^~)，最长匹配的location被选中并返回
  3) 接着进行字符串前缀匹配(没有修饰符的)，最长匹配的location，先记录下来
  4) 然后正则匹配（~ | ~*），按配置文件中定义的顺序进行，一旦遇到匹配的location，选中并返回
  5) 如果正则匹配没有location能够匹配上，第 3) 步的结果被返回

注：
```
^~ 和没有修饰符的location 不能同时重复
location ^~ /a {
    echo "^~ /a";
}

location /a {
    echo "/a";
}

启动nginx会报错：
nginx: [emerg] duplicate location "/a" in /usr/local/nginx/conf/nginx.conf:121
想想也有道理，如果配置了 "^~" 带修饰符的前缀匹配，优先级比没有修饰符的前缀匹配高，没有修饰符的前缀匹配将永远没机会匹配到。
```

一些例外（some exceptions）:
  1) 对于大小写不敏感的系统，如 mac，location 匹配全是大小写不敏感的（略坑）
  2) 如果 前缀匹配的location定义中，以 / 结尾，那么假如实际请求URL没有以 / 结尾，nginx返回一个301永久重定向：
  ```
  配置：
  location  /user/ {
      proxy_pass http://www.baidu.com;
  }
  
  请求：
  > curl http://local:8080/user
  <html>
  <head><title>301 Moved Permanently</title></head>
  <body>
  <center><h1>301 Moved Permanently</h1></center>
  <hr><center>nginx/1.16.0</center>
  </body>
  </html>
  ```